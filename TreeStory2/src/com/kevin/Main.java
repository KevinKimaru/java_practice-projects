package com.kevin;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String story = "Thanks __name__ for helping me out. You are really a __adjective__ __noun__ and I owe you a __noun__.";
        Template template = new Template(story);
        List<String> fakeResults = Arrays.asList("friend", "talented", "java programmer", "high five");

        String results = template.render(fakeResults);
        System.out.printf("Your TreeStory:\n\n%s", results);
    }
}
