package com.example.gymnasium.tools;
import java.util.Base64;

public class ImageConversion {
    public static String convertToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}

