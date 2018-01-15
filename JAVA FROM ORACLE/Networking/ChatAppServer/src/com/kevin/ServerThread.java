package com.kevin;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.kevin.Main.threads;

/**
 * Created by Kevin Kimaru Chege on 10/21/2017.
 */
public class ServerThread implements Runnable {
    Thread t;
    Socket socket = null;
    BufferedReader in;
    PrintWriter out;
    String name;

    public ServerThread(Socket socket) {
        t = new Thread(this);
        this.socket = socket;
        t.start();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String input;
            name = in.readLine();
            threads.add(this);
            System.out.printf("Thread  no %d added\n", threads.size());
            for (ServerThread thread : threads) {
                Socket socket = thread.getSocket();
                if (socket != null) {
                    try {
                        PrintWriter outAll = new PrintWriter(socket.getOutputStream(), true);
                        outAll.printf("%s has joined the chat\n", name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            while (true) {
                input = in.readLine();
                for (ServerThread thread : threads) {
                    Socket socket = thread.getSocket();
                    if (socket != null) {
                        try {
                            PrintWriter outAll = new PrintWriter(socket.getOutputStream(), true);
                            outAll.printf("%s: %s\n", getName(), input);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            threads.remove(this);
            for (ServerThread thread : threads) {
                Socket socket = thread.getSocket();
                if (socket != null) {
                    try {
                        PrintWriter outAll = new PrintWriter(socket.getOutputStream(), true);
                        outAll.printf("%s has left the chat\n", getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.printf("Threads reduced to %d\n", threads.size());
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Socket getSocket() {
        return this.socket;
    }

    public synchronized String getName() {
        return this.name;
    }
}
