package com.kevin;

import sun.reflect.generics.tree.Tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class Treet implements Comparable<Treet>, Serializable{
    private static final long serialVersionUID = -3610608140786870602L;
    private String mAuthor;
    private String mDescription;
    private Date mCreationDate;

    public Treet(String author, String description, Date creationDate) {
        mAuthor = author;
        mDescription = description;
        mCreationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format("Treet: \"%s\" by %s on %s",mDescription, mAuthor, mCreationDate);
    }

    @Override
    public int compareTo(Treet other) {
        if (equals(other)) return 0;

        int dataCmp = mCreationDate.compareTo(other.mCreationDate);
        if (dataCmp == 0) return mDescription.compareTo(other.mDescription);
        return dataCmp;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDescription() {
        return mDescription;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public List<String>  getWords() {
        String[] words = mDescription.toLowerCase().split("[^\\w#@']+");
        return Arrays.asList(words);
    }

    public List<String> getMentions() {
        return getWordsPrefixedWith("@");
    }

    public List<String> getHashTags() {
        return getWordsPrefixedWith("#");
    }

    private List<String> getWordsPrefixedWith(String prefix) {
        List<String> results = new ArrayList<>();
        for (String word: getWords()){
            if (word.startsWith(prefix)) {
                results.add(word);
            }
        }
        return results;
    }

}
