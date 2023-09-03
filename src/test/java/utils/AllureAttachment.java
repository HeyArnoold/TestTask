package utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.download;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static utils.Constants.FAILED_FILE_ATTACH;

public class AllureAttachment {

    public static void attachFile(String name, String type, String fileExtension, File file) {
        try {
            Allure.getLifecycle().addAttachment(name, type, fileExtension, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(FAILED_FILE_ATTACH);
        }
    }

    public static void attachImage(String name, File image) {
        try {
            Allure.getLifecycle().addAttachment(name, "image/jpg", "jpg", new FileInputStream(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(FAILED_FILE_ATTACH);
        }
    }

    public static void attachVideoSelenoid(String sessionId) {
        String videoUrl = String.format("%s%s.mp4", "http://192.168.0.111:4444/video/", sessionId);
        await()
                .atMost(10, SECONDS)
                .pollInterval(100, MILLISECONDS)
                .with()
                .alias(String.format("Ждем пока в selenoid построиться видео. URL: %s", videoUrl))
                .untilAsserted(() -> {
                    File videoFile = downloadFileIfExist(videoUrl);
                    assertNotNull(videoFile);
                    attachFile("video.mp4", "video/mp4", "mp4", videoFile);
                });
    }

    private static File downloadFileIfExist(String url) {
        File file;
        try {
            file = download(url);
        } catch (Exception e) {
            return null;
        }
        return file;
    }
}
