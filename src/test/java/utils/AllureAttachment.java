package utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
}
