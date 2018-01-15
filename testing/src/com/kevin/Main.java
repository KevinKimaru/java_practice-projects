package com.kevin;

import java.io.*;
import java.net.*;

public class Main  {
    private static final String text = "Happy Mashujaa\n";

    public static void main(String[] args) {
//        mashujaaDay();
        try {
//            downloadFile("http://localhost:8080/testingServlet/download", "F:\\DOWNLOADS", "android.pdf");
            downloadFile("https://infinite-mountain-99169.herokuapp.com/testingServlet/download", "F:\\DOWNLOADS", "android.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile(String fileUrl, String saveDir, String downloadFile) throws IOException {
        URL url = new URL(fileUrl + "?file=" + downloadFile);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = con.getHeaderField("Content-disposition");
            String contentType = con.getContentType();
            int contentLength = con.getContentLength();

            if (disposition != null) {
                //extract file name from header file
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length()-1);
                }
            } else {
                //extracts file name from URL
                fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());
            }

            System.out.println("Content-type = " + con);
            System.out.println("Content-disposition = " + disposition);
            System.out.println("Content-length = " + contentLength);
            System.out.println("fileName = " + fileName);

            //opens input stream from the http connection
            InputStream inputStream = con.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            //opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[contentLength];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        }

        else {
            System.out.println("No file to download.");
        }
        con.disconnect();
    }

    private static void mashujaaDay() {
        String filePath = System.getProperty("user.dir") + "\\resources\\Amina 2017 (Lyric Video).mp3";
        System.out.println(filePath);
        int count = 0;
        int count2 = 4;
        System.out.println(text);
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                for (int k = 0; k <= count; k++) {
                    System.out.print("-");
                }
                System.out.println(text);
                count++;
            }
            for (int j = 1; j <= 5; j++) {
                for (int k = count2; k >= 1; k--) {
                    System.out.print("-");
                }
                System.out.println(text);
                count2--;
            }
            count = 0;
            count2 = 4;
        }
    }
}

