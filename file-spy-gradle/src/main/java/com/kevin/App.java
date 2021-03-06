package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 8/3/2017.
 */

import org.apache.tika.Tika;

import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class App {
    private static final String FILE_TYPE = "text/csv";
    private static final String DIR_TO_WATCH = "/Users/Kevin Kimaru Chege/Desktop/yui/";

    public static void main(String[] args) throws Exception {
        Path dir = Paths.get(DIR_TO_WATCH);
        Tika tika = new Tika();
        WatchService watchService = FileSystems.getDefault().newWatchService();
        dir.register(watchService, ENTRY_CREATE);

        WatchKey key;
        do {
            key = watchService.take();
            key.pollEvents().stream().filter(e -> {
                Path filename = (Path) e.context();
                String type = tika.detect(filename.toString());
                return FILE_TYPE.equals(type);
            }).forEach(e -> System.out.printf("File found %s\n", e.context()));
        } while (key.reset());
    }
}
