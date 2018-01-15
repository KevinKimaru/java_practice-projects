package com.kevin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final  List<ServerThread> threads= new ArrayList<>();
    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(4040)) {
            while(true) {
                new ServerThread(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
