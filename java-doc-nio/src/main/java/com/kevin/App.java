package com.kevin;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Kevin Kimaru Chege on 8/19/2017.
 */
public class App {
    public static void main(String[] args) {
//        channels_reading_using_allocate();
//        channels_reading_using_map();

//        channels_writing_using_allocate();
//        channels_writing_using_map();

//        copying_files();

//        using_inputstreams_with_nio();
//        using_outputstreams_with_nio();

//        paths_and_filesystems_operations_intro();
//        paths_and_filesystems_operations_list_directory_contents();
//        paths_and_filesystems_operations_list_directory_contents_using_filter();
        paths_and_filesystems_operations_list_directory_walkfiletree();
    }

    private static void paths_and_filesystems_operations_list_directory_walkfiletree() {
        String dirname = "/Python27";
        System.out.println("Directory tree starting with " + dirname + ":\n");
        try {
            Files.walkFileTree(Paths.get(dirname), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void paths_and_filesystems_operations_list_directory_contents_using_filter() {
        String dirname = "/Python27";
        // Create a filter that returns true only for writable files.
        DirectoryStream.Filter<Path> how = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                if (Files.isRegularFile(entry)) return true;
                return false;
            }
        };
        // Obtain and manage a directory stream of writable files.
        try (DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirname), how)) {
            System.out.println("Directory name: " + dirname);
            for (Path entry : dirstrm) {
                BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attrs.isDirectory()) System.out.print("<DIR>");
                else System.out.print("     ");
                System.out.println(entry.getName(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void paths_and_filesystems_operations_list_directory_contents() {
        String dirname = "/Python27";

        // Obtain and manage a directory stream within a try block.
        try (DirectoryStream<Path> dirstr = Files.newDirectoryStream(Paths.get(dirname), "[p]*.[exe, txt]*")) {
            System.out.println("Directory of" + dirname);

            // Because DirectoryStream implements Iterable, we
            // can use a "foreach" loop to display the directory.
            for (Path entry : dirstr) {
                BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attrs.isDirectory()) System.out.print("<DIR>");
                else System.out.print("    ");
                System.out.println(entry.getName(1));
            }
        } catch (InvalidPathException e) {
            System.out.println("Path Error " + e);
        } catch (NotDirectoryException e) {
            System.out.println(dirname + " is not a directory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void paths_and_filesystems_operations_intro() {
        Path filepath = Paths.get("test.txt");
        System.out.println("File Name: " + filepath.getName(0));
        System.out.println("Path: " + filepath);
        System.out.println("Absolute Path: " + filepath.toAbsolutePath());
        System.out.println("Parent: " + filepath.getParent());

        if (Files.exists(filepath)) System.out.println("File exists");
        else System.out.println("File does not exist");

        try {
            if (Files.isHidden(filepath)) System.out.println("File is hidden");
            else System.out.println("File is not hidden");
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }

        Files.isWritable(filepath);
        System.out.println("File is writable");


        Files.isReadable(filepath);
        System.out.println("File is readable");

        try {
            BasicFileAttributes attribs = Files.readAttributes(filepath, BasicFileAttributes.class);

            if (attribs.isDirectory()) System.out.println("The file is a directory");
            else System.out.println("The file is not a directory");

            if (attribs.isRegularFile()) System.out.println("The file is a normal file");
            else System.out.println("The file is not a normal file");

            if (attribs.isSymbolicLink()) System.out.println("The file is a symbolic link");
            else System.out.println("The file is not a symbolic link");

            System.out.println("File last modified: " + attribs.lastModifiedTime());
            System.out.println("File size: " + attribs.size() + " Bytes");
        } catch (IOException e) {
            System.out.println("Error reading attributes: " + e);
        }
    }

    private static void using_outputstreams_with_nio() {
        try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(Paths.get("test6.txt")))) {
            for (int i = 0; i < 26; i++) os.write((byte) ('A' + i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void using_inputstreams_with_nio() {
        int i;
        try (InputStream is = Files.newInputStream(Paths.get("test.txt"))) {
            do {
                i = is.read();
                if (i != -1) System.out.print((char) i);
            } while (i != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copying_files() {
        Path src = Paths.get("test4.txt");
        Path target = Paths.get("test5.txt");

        try {
            Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void channels_writing_using_map() {
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Paths.get("test3.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE)) {
            // Then, map the file into a buffer.
            MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_WRITE, 0, 26);

            // Write some bytes to the buffer.
            for (int i = 0; i < 26; i++) mBuf.put((byte) ('A' + i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void channels_writing_using_allocate() {
        // Obtain a channel to a file within a try-with-resources block.
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Paths.get("test2.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE)) {
            // Create a buffer.
            ByteBuffer mBuf = ByteBuffer.allocate(26);

            // Write some bytes to the buffer.
            for (int i = 0; i < 26; i++) mBuf.put((byte) ('A' + i));

            // Reset the buffer so that it can be written.
            mBuf.rewind();

            //write the buffer to the output file
            fChan.write(mBuf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void channels_reading_using_map() {
        // Obtain a channel to a file within a try-with-resources block.
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Paths.get("test.txt"))) {
            // Get the size of the file.
            long fSize = fChan.size();
            // Now, map the file into a buffer.
            MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_ONLY, 0, fSize);
            // Read and display bytes from buffer
            for (int i = 0; i < fSize; i++) System.out.print((char) mBuf.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void channels_reading_using_allocate() {
        int count;
//        Path filePath = null;

        //first obtain a path to the file
//        filePath = Paths.get("test.txt");

        // Here, the channel is opened on the Path returned by Paths.get().
        // There is no need for the filepath variable.
        try (SeekableByteChannel fChan = Files.newByteChannel(Paths.get("test.txt"))) {
            // Allocate a buffer.
            ByteBuffer mBuf = ByteBuffer.allocate(128);

            do {
                //Read a buffer
                count = fChan.read(mBuf);
                // Stop when end of file is reached.
                if (count != -1) {
                    // Rewind the buffer so that it can be read.
                    mBuf.rewind();
                    // Read bytes from the buffer and show
                    // them on the screen as characters.
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) mBuf.get());
                    }
                }
            } while (count != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
