/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taking.faces.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.servlet.http.Part;

/**
 *
 * @author Zayar Thant
 */
public class ImageUtil {

    public static boolean isValidImage(Part part) {
        if (null != part) {
            return part.getContentType().equals("image/jpeg") || part.getContentType().equals("image/png") || part.getContentType().equals("image/gif");
        }
        return false;
    }

    public static boolean uploadImage(Part part, String fileName) {
        if (part != null) {

            try (InputStream inputStream = part.getInputStream(); FileOutputStream outputStream = new FileOutputStream(fileName)) {

                int bytesRead;
                final byte[] chunck = new byte[1024];
                while ((bytesRead = inputStream.read(chunck)) != -1) {
                    outputStream.write(chunck, 0, bytesRead);
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public static String generateImageName(String s) {
        Random rand = new Random();
        return rand.nextInt() + rand.nextInt() + s;
    }
}
