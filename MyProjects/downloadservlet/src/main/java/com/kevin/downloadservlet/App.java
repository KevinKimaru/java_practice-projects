package com.kevin.downloadservlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Kevin Kimaru Chege on 11/2/2017.
 */
@WebServlet("/download")
public class App extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String filePath = System.getProperty("user.dir") + "\\resources\\Amina 2017 (Lyric Video).mp3";
        String filePath = getServletContext().getRealPath("\\resources\\Amina 2017 (Lyric Video).mp3");
        System.out.println("File Path: " + filePath);
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        //obtain servlet context
        ServletContext context = getServletContext();

        //gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            //set binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        //modify response
        resp.setContentType(mimeType);
        resp.setContentLength((int) file.length());

        //forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());

        resp.setHeader(headerKey, headerValue);

        //obtain response's outputstream
        OutputStream out = resp.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        fis.close();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
