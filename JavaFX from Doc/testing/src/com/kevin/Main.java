package com.kevin;

public class Main {

    public static void main(String[] args) {
	    String[] names = {"Kevin", "KImaru", "Chege", "Kabutha", "Samuel"};
	    int i = 0;
	    String namesStr = "";
	    while(i < names.length) {
	        namesStr = names[i] + " "+ namesStr;
	        i++;
        }
        System.out.print(namesStr);

    }
}
