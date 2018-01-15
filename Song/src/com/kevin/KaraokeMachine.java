package com.kevin;

import com.kevin.model.Song;
import com.kevin.model.SongBook;
import com.kevin.model.SongRequest;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class KaraokeMachine {
    private SongBook mSongBook;
    private BufferedReader mReader;
    private Queue<SongRequest> mSongQueue;
    private Map<String, String> mMenu;

    public KaraokeMachine(SongBook songBook) {
        mSongBook = songBook;
        //you can use input stream reader alone , however buffered reader is better since it will convert
        // the bytes into chars only when needed unlike input strem resder which will convert every time
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mSongQueue = new ArrayDeque<SongRequest>();
        mMenu = new HashMap<>();
        mMenu.put("add", "Add a new song to the song book.");
        mMenu.put("play", "Play next song in the queue.");
        mMenu.put("choose", "Choose a song to sing");
        mMenu.put("quit", "Give up. Exit the program.");
    }

    private String promptaction() throws IOException {
        System.out.printf("There are %d songs available and %d in the queue. Your options are: \n",
                mSongBook.getSongCount(), mSongQueue.size());
        for (Map.Entry<String, String> options : mMenu.entrySet()) {
            System.out.printf("\t->%s - %s \n", options.getKey(), options.getValue());
        }
        System.out.print("What do you want to do: \n");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run() {
        String choice = "";
        do {
            try {
                choice = promptaction();
                switch (choice) {
                    case "add":
                        Song song = promotNewSong();
                        mSongBook.addSong(song);
                        System.out.printf("%s added! \n\n", song);
                        break;
                    case "play":
                        playNext();
                        break;
                    case "choose":
                        String singerName = promptForSingerName();
                        String artist = promptForArtist();
                        Song artistSong = promptSongsForArtist(artist);
                        SongRequest songRequest = new SongRequest(singerName, artistSong);
                        if (mSongQueue.contains(songRequest)) {
                            System.out.printf("\n\n Whoops! %s already requested %s\n", singerName, artistSong);
                            break;
                        }
                        mSongQueue.add(songRequest);
                        System.out.printf("You chose: %s \n", artistSong);
                        break;
                    case "quit":
                        break;
                    default:
                        System.out.printf("Uknown Choice '%s'. Please try again.", choice);
                }
            } catch (IOException e) {
                System.out.println("Problem with input.");
                e.printStackTrace();
            }
        } while (!choice.equals("quit"));
    }

    private String promptForSingerName() throws IOException {
        System.out.print("Enter the singer's name: ");
        return mReader.readLine();
    }

    private Song promotNewSong() throws IOException {
        System.out.print("Enter the artist's name: ");
        String artist = mReader.readLine();
        System.out.print("Enter the title: ");
        String title = mReader.readLine();
        System.out.printf("Enter the video URL: ");
        String videoUrl = mReader.readLine();
        return new Song(artist, title, videoUrl);
    }

    private String promptForArtist() throws IOException {
        System.out.println("Available artists: ");
        List<String> artists = new ArrayList<>(mSongBook.getArtists());
        int index = promptForIndex(artists);
        return artists.get(index);
    }

    private Song promptSongsForArtist(String artist) throws IOException {
        List<Song> songs = mSongBook.getSongsForArtist(artist);
        List<String> songTitles = new ArrayList<>();
        for (Song song : songs) {
            songTitles.add(song.getTitle());
        }
        System.out.printf("Available songs for %s: %n", artist);
        int index = promptForIndex(songTitles);
        return songs.get(index);
    }

    private int promptForIndex(List<String> options) throws IOException {
        int counter = 1;
        for (String option : options) {
            System.out.printf("%d.) %s \n", counter, option);
            counter++;
        }
        System.out.print("Your choice: ");
        String optionAsString = mReader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
        return choice - 1;
    }

    private void playNext() {
        SongRequest songRequest = mSongQueue.poll();

        if (songRequest == null) {
            System.out.println("Sorry there are no songs in the queue. Use 'choose' from the menu to add some.");
        } else {
            Song song = songRequest.getSong();
            System.out.printf("\n\n\nReady %s? Open %s to hear %s by %s\n\n",
                    songRequest.getSingerName(),
                    song.getVideoUrl(),
                    song.getTitle(),
                    song.getArtist()
            );
        }
    }

}
