package com.kevin.composure;

import com.kevin.composure.filters.Html;
import com.kevin.composure.filters.Markdown;
import com.kevin.composure.utils.Renderer;

import java.util.function.Function;

public class App {

    public static void main(String[] args) {
        String htmlInput = "<p>Using strong tags <strong>allows you to over promote</strong> and " +
                "<a href=\"http://www.myspace.com/tom\">Links should open in new browser</a></p>";

//    //Strips the bold
//    String result = Html.boldCleaner(htmlInput);
//    // adds rel = external
//    result = Html.externalizeLinks(result);

//      String result = Renderer.render(htmlInput, text -> {
//          String html = Html.boldCleaner(text);
//          return Html.externalizeLinks(html);
//      });

//      //<String, String> Takes in a string and returns a string
//      Function<String, String> boldCleaner = Html::boldCleaner;
//      String result = Renderer.render(htmlInput, boldCleaner.andThen(Html::externalizeLinks));


//      Function<String, String> boldCleaner = Html::boldCleaner;
//      Function<String, String> sanitize = boldCleaner.andThen(Html::externalizeLinks);
//      String result = Renderer.render(htmlInput, sanitize);

        String markdownInput = "Using strong tags **allows you to over promote** and " +
                "[Links should open in new browser](http://www.myspace.com/tom)";
        Function<String, String> boldCleaner = Html::boldCleaner;
        Function<String, String> sanitize = boldCleaner.andThen(Html::externalizeLinks);
        Function<String, String> markedDownSanitizer = sanitize.compose(Markdown::render);
        String result = Renderer.render(markdownInput, markedDownSanitizer);


        System.out.println(result);
    }
}
