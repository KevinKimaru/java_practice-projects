package com.kevin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin Kimaru Chege on 8/3/2017.
 */
public class Template {
    private String mCompiled;
    private List<String> mPlaceHolders;

    public Template(String text) {
        Pattern pattern = Pattern.compile("__([^__]+)__");
        Matcher matcher = pattern.matcher(text);
        mPlaceHolders = new ArrayList<>();
        while (matcher.find()) {
            String label = matcher.group(0);
            mPlaceHolders.add(label);
        }
        mCompiled = matcher.replaceAll("%s");

    }

    public String render(List<String> values) {
        return String.format(mCompiled, values.toArray());
    }
}
