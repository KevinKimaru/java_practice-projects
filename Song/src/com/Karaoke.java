package com;

import com.kevin.KaraokeMachine;
import com.kevin.model.Song;
import com.kevin.model.SongBook;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class Karaoke {
    public static void main(String[] args) {
        SongBook songBook = new SongBook();
        songBook.importFrom("songs.txt");
        KaraokeMachine machine = new KaraokeMachine(songBook);
        machine.run();
        System.out.println("Saving book...");
        songBook.exportTo("songs.txt");
    }
}
