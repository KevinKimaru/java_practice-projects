package com.kevin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Socket socket = new Socket("KEVIN-CHEGE", 4040);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)))
        {
            new ClientThread(socket);
            String sent;
            System.out.println("Please enter your name: ");
            sent = stdin.readLine();
            out.println(sent);
            while ((sent = stdin.readLine()) != null) {
                out.println(sent);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
