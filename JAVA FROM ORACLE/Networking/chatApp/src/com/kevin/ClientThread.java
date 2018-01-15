package com.kevin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Kevin Kimaru Chege on 10/21/2017.
 */
public class ClientThread implements Runnable {
    Socket socket;
    Thread t;

    public ClientThread(Socket socket) {
        t = new Thread(this);
        this.socket = socket;
        t.start();
    }

    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String input;
            while((input = in.readLine()) != null) {
                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
