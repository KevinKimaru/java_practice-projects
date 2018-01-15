package com.kevin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class app {

    public static int serverPort = 998;
    public static int clientPort = 999;
    public static int buffer_size = 1024;
    public static DatagramSocket ds;
    public static byte buffer[] = new byte[buffer_size];

    public static void TheServer() throws Exception {
        int pos = 0;

        while (true) {
            int c = System.in.read();
            switch (c) {
                case -1:
                    System.out.println("Server quits");
                    ds.close();
                    return;
                case '\r':
                    break;
                case '\n':
                    ds.send(new DatagramPacket(buffer, pos, InetAddress.getLocalHost(), clientPort));
                    pos = 0;
                    break;
                default:
                    buffer[pos++] = (byte) c;
            }
        }
    }

    public static void TheClient() throws Exception {
        while (true ) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            ds.receive(p);
            System.out.println(new String(p.getData(), 0, p.getLength()));
        }
    }

    public static void main(String[] args) throws Exception {
//        intro_inetaddress();

//        clientsockets_intro();

//        urls();

//        urlconnection();
//        httpurlconnection();

        if (args.length == -1) {
            ds = new DatagramSocket(serverPort);
            TheServer();
        } else {
            ds = new DatagramSocket(clientPort);
            TheClient();
        }
    }

    private static void datagrams() {

    }

    private static void httpurlconnection() {
        try {
            URL con = new URL("http://www.google.com");

            HttpURLConnection hpCon = (HttpURLConnection) con.openConnection();

            // Display request method.
            System.out.println("Request method is: " + hpCon.getRequestMethod());

            //Dislay response code
            System.out.println("Response code is: " + hpCon.getResponseCode());

            //Display response message
            System.out.println("Response message is: " + hpCon.getResponseMessage());

            // Get a list of the header fields and a set of the header keys.
            Map<String, List<String>> hdrMap = hpCon.getHeaderFields();
            Set<String> hdrField = hdrMap.keySet();
            System.out.println("\nHere is the header:");

            //Display all header keys and values
            for (String k: hdrField) System.out.println("Key: " + k + " value: " + hdrMap.get(k));

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void urlconnection() {
        try {
            int c;
            URL hp = new URL("http://www.google.com");

            URLConnection hpCon = hp.openConnection();

            //get Date
            long d = hpCon.getDate();
            if (d == 0) System.out.println("No date information.");
            else System.out.println("Date: " + new Date(d));

            //get content type
            System.out.println("Content-type: " + hpCon.getContentType());

            //get expiration date
            d = hpCon.getExpiration();
            if (d == 0) System.out.println("No expiration information");
            else System.out.println("Expires: " + new Date(d));

            //get last modified date
            d = hpCon.getLastModified();
            if (d == 0) System.out.println("No last-modidied information");
            else System.out.println("Last-modified: " + new Date(d));

            //get content length
            long len = hpCon.getContentLengthLong();
            if (len == -1) System.out.println("Content length unavailable.");
            else System.out.println("Content-Length: " + len);

            if (len != 0) {
                System.out.println("=== Content ===");
                InputStream in = hpCon.getInputStream();
                while ((c = in.read())!=-1) System.out.println((char) c);
                in.close();
            }

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void urls() {
        try {
            URL hp = new URL("http://www.HerbSchildt.com/WhatsNew");
            System.out.println("Protocol: " + hp.getProtocol());
            System.out.println("Port: " + hp.getPort());
            System.out.println("Host: " + hp.getHost());
            System.out.println("File: " + hp.getFile());
            System.out.println("External Form: " + hp.toExternalForm());
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
    }

    private static void clientsockets_intro() {
        int c;
        // Create a socket connected to internic.net, port 43. Manage this socket with a try-with-resources block.
        try (Socket s = new Socket("whois.internic.net", 43)) {
            // Obtain input and output streams.
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            //request string
            String request = "MHProfessional.com";
            //convert to bytes
            byte buf[] = request.getBytes();

            //send request
            out.write(buf);

            //read and display response
            while ((c = in.read()) != -1) {
                System.out.println((char) c);
            }

            //socket is now closed
        } catch (UnknownHostException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private static void intro_inetaddress() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);


            address = InetAddress.getByName("www.google.com");
            System.out.println(address);

            InetAddress[] sw = InetAddress.getAllByName("www.google.com");
            for (int i = 0; i < sw.length; i++) System.out.println(sw[i]);
        } catch (UnknownHostException e) {
            System.out.println("Error occured: " + e);
        }
    }
}
