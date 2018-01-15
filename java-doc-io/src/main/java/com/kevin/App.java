package com.kevin;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Kevin Kimaru Chege on 8/17/2017.
 */
class InputStreamEnumerator implements Enumeration<FileInputStream> {
    private Enumeration<String> files;

    public InputStreamEnumerator(Vector<String> files) {
        this.files = files.elements();
    }

    public boolean hasMoreElements() {
        return files.hasMoreElements();
    }

    public FileInputStream nextElement() {
        try {
            return new FileInputStream(files.nextElement().toString());
        } catch (IOException e) {
            return null;
        }
    }
}

class OnlyExt implements FilenameFilter {
    String ext;

    OnlyExt(String ext) {
        this.ext = "." + ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(ext);
    }
}

class MyClass implements Serializable {
    String s;
    int i;
    double d;

    MyClass(String s, int i, double d) {
        this.s = s;
        this.i = i;
        this.d = d;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "s='" + s + '\'' +
                ", i=" + i +
                ", d=" + d +
                '}';
    }
}

public class App {
    public static void main(String[] args) {
//        intro();
//        filterfiles();
        fileinputstreams();

//        fileoutputstream();

//        bytearrayinputstream();

//        bytearrayoutputstream();

//        bufferedinputstreams();

//        pushbackinputstream();

//        sequenceinputstream();

//        dataoutputandinputstreams();

//        filereader();

//        filewriter();

//        chararrayreader();
//        chararraywriter();

//        bufferedreader();

//        pushbackreader();

//        consoles();

//        serialization();

    }

    private static void serialization() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serial"))) {
            MyClass obj1 = new MyClass("Hello", -7, 2.7e10);
            System.out.println("Object1: " + obj1);
            oos.writeObject(obj1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serial"))) {
            MyClass obj2 = (MyClass) ois.readObject();
            System.out.println("Object2: " + obj2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void consoles() {
        String str;
        Console c;

        c = System.console();

        if (c == null) {
            System.out.println("OOps no console");return;}

        str = c.readLine("Enter a password");
        c.printf("Here is your string: %s\n", str);
    }

    private static void pushbackreader() {
        String s = "if (a == 4) a = 0;\n";
        char buf[] = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        CharArrayReader in = new CharArrayReader(buf);
        int c;

        try (PushbackReader f = new PushbackReader(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '=':
                        if ((c = f.read()) == '=')
                            System.out.print(".eq.");
                        else {
                            System.out.print("<-");
                            f.unread(c);
                        }
                        break;
                    default:
                        System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private static void bufferedreader() {
        String s = "This is a &copy; copyright symbol but this is a $copy not.";
        char buf[] = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);

        CharArrayReader in = new CharArrayReader(buf);
        int c;
        boolean marked = false;

        try (BufferedReader f = new BufferedReader(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '&':
                        if (!marked) {
                            f.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                        break;
                    case ';':
                        if (marked) {
                            marked = false;
                            System.out.print("(c)");
                        } else System.out.print((char) c);
                        break;
                    case ' ':
                        if (marked) {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        } else System.out.print((char) c);
                        break;
                    default:
                        if (!marked) System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void chararraywriter() {
        CharArrayWriter f = new CharArrayWriter();
        String s = "This should end up in the array.";
        char buf[] = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);

        try {
            f.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Buffer as string:");
        System.out.println(f.toString());

        System.out.println("Into array");
        char c[] = f.toCharArray();
        for (int i = 0; i < c.length; i++) System.out.print(c[i]);

        System.out.println("\nTo a file writer");
        try (FileWriter f2 = new FileWriter("ctest.txt")) {
            f.writeTo(f2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("DOing a reset");
        f.reset();

        for (int i = 0; i < 3; i++) f.write('X');
        System.out.println(f.toString());
    }

    private static void chararrayreader() {
        String tmp = "abcdefghijklmnopqrstuvwxyz";
        char c[] = new char[tmp.length()];
        tmp.getChars(0, tmp.length(), c, 0);
        int i;

        try (CharArrayReader input1 = new CharArrayReader(c)) {
            System.out.println("Input 1 is:     ");
            while ((i = input1.read()) != -1) System.out.print((char) i);
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
        System.out.println();
        try (CharArrayReader input2 = new CharArrayReader(c, 0, 5)) {
            System.out.println("Input 1 is:     ");
            while ((i = input2.read()) != -1) System.out.print((char) i);
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
    }

    private static void filewriter() {
        String source = "Now is the time for all good men\n"
                + " to come to the aid of their country\n"
                + " and pay their due taxes.";
        char buffer[] = new char[source.length()];
        source.getChars(0, source.length(), buffer, 0);

        try (FileWriter f1 = new FileWriter("wfile1.txt");
             FileWriter f2 = new FileWriter("wfile2.txt");
             FileWriter f3 = new FileWriter("wfile3.txt")) {
            //write to first file
            for (int i = 0; i < buffer.length; i += 2) {
                f1.write(buffer[i]);
            }
            //to second file
            f2.write(buffer);

            //to third file
            f3.write(buffer, buffer.length - buffer.length / 4, buffer.length / 4);
        } catch (IOException e) {
            System.out.println("An I/O error occcurred" + e);
        }
    }

    private static void filereader() {
        try (FileReader f = new FileReader("C:\\Users\\Kevin Kimaru Chege\\IdeaProjects\\java-doc-io\\src\\main\\java\\com\\kevin\\App.java")) {
            int c;
            while ((c = f.read()) != -1) System.out.print((char) c);
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        }
    }

    private static void dataoutputandinputstreams() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("Test.dat"))) {
            dos.writeDouble(98.6);
            dos.writeInt(10000);
            dos.writeBoolean(true);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open output file");
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream("Test.dat"))) {
            double d = dis.readDouble();
            int i = dis.readInt();
            boolean b = dis.readBoolean();
            System.out.println("Here are the values: " + d + " " + i + " " + b);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open output file");
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        }
    }

    private static void sequenceinputstream() {
        int c;
        Vector<String> files = new Vector<String>();

        files.addElement("file1.txt");
        files.addElement("file2.txt");
        files.addElement("file3.txt");

        InputStreamEnumerator ise = new InputStreamEnumerator(files);

        try (InputStream stream = new SequenceInputStream(ise)) {
            while ((c = stream.read()) != -1) System.out.print((char) c);
        } catch (NullPointerException e) {
            System.out.println("Error Opening File." + e);
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
    }

    private static void pushbackinputstream() {
        String s = "if (a == 4) a = 0;\n";
        byte buf[] = s.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        int c;

        try (PushbackInputStream f = new PushbackInputStream(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '=':
                        if ((c = f.read()) == '=')
                            System.out.print(".eq.");
                        else {
                            System.out.print("<-");
                            f.unread(c);
                        }
                        break;
                    default:
                        System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private static void bufferedinputstreams() {
        String s = "This is a &copy; copyright symbol but this is a $copy not.";
        byte buf[] = s.getBytes();

        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        int c;
        boolean marked = false;

        try (BufferedInputStream f = new BufferedInputStream(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '&':
                        if (!marked) {
                            f.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                        break;
                    case ';':
                        if (marked) {
                            marked = false;
                            System.out.print("(c)");
                        } else System.out.print((char) c);
                        break;
                    case ' ':
                        if (marked) {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        } else System.out.print((char) c);
                        break;
                    default:
                        if (!marked) System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private static void bytearrayoutputstream() {
        ByteArrayOutputStream f = new ByteArrayOutputStream();
        String s = "This should end up in the array";
        byte[] buf = s.getBytes();

        try {
            f.write(buf);
        } catch (IOException e) {
            System.out.println("Error writing to buff");
            return;
        }

        System.out.println("Buffer as string");
        System.out.println(f.toString());
        System.out.println("Into array");
        byte[] b = f.toByteArray();
        for (int i = 0; i < b.length; i++) System.out.print((char) b[i]);
        System.out.println();

        System.out.println("To an output stream:..");
        try (FileOutputStream fos = new FileOutputStream("test.txt")) {
            f.writeTo(fos);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
            return;
        }

        System.out.println("Doing a reset:..");
        f.reset();
        for (int i = 0; i < 3; i++) f.write('X');
        System.out.println(f.toString());
    }

    private static void bytearrayinputstream() {
        String tmp = "abcdefghijklmnopqrstuvwxyz";
        byte[] b = tmp.getBytes();

        ByteArrayInputStream input1 = new ByteArrayInputStream(b);
        ByteArrayInputStream input2 = new ByteArrayInputStream(b, 0, 3);

        String tmp2 = "abc";
        byte[] b2 = tmp2.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(b2);

        for (int i = 0; i < 2; i++) {
            int c;
            while ((c = in.read()) != -1) {
                if (i == 0) {
                    System.out.print((char) c);
                } else {
                    System.out.print(Character.toUpperCase((char) c));
                }
            }
            System.out.println();
            in.reset();
        }
    }

    private static void fileoutputstream() {
        String source = "Now is the time for all good men" +
                "\nto come to the aid of their country\n" +
                "and pay their due taxes.";
        byte[] buf = source.getBytes();

        try (FileOutputStream f0 = new FileOutputStream("file1.txt");
             FileOutputStream f1 = new FileOutputStream("file2.txt");
             FileOutputStream f2 = new FileOutputStream("file3.txt")) {
            //wrrite to first file
            for (int i = 0; i < buf.length; i++) f0.write(buf[i]);

            //write to second file
            f1.write(buf);

            //write to third file
            f2.write(buf, buf.length - buf.length / 4, buf.length / 4);

        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    private static void fileinputstreams() {
        int size;

        try (FileInputStream f = new FileInputStream("C:\\Users\\Kevin Kimaru Chege\\IdeaProjects\\java-doc-io\\src\\main\\java\\com\\kevin\\App.java")) {
            System.out.println("Total available Bytes: " + (size = f.available()));

            int n = size / 40;
            System.out.println("First " + n + " bytes of the file one read() at a time:");
            for (int i = 0; i < n; i++) {
                System.out.print((char) f.read());
            }

            System.out.println("\n Still available: " + f.available());

            System.out.println("Reading the next " + n + " with one read(b[])");
            byte b[] = new byte[n];
            if (f.read(b) != n) System.out.println("Couldn't read " + n + "bytes.");
            System.out.println(new String(b, 0, n));
            System.out.println();

            System.out.println("Skipping half of remaining bytes with skip()");
            f.skip(size / 2);
            System.out.println("Still available: " + f.available());

            System.out.println("Reading " + n / 2 + " into the end of the array.");
            if (f.read(b, n / 2, n / 2) != n / 2) {
                System.out.println("Couldn't read " + n / 2 + "bytes");
            }
            System.out.println(new String(b, 0, b.length));
            System.out.println("\nStill Available: " + f.available());

        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }

    private static void filterfiles() {
        String fileName = "/Users/Kevin Kimaru Chege";
        File f = new File(fileName);
        FilenameFilter only = new OnlyExt("json");
        String s[] = f.list(only);

        for (int i = 0; i < s.length; i++) System.out.println(s[i]);
    }

    private static void intro() {
        File f1 = new File("/Users");
        System.out.println("Path: " + f1.getPath());
        System.out.println("File name: " + f1.getName());
        System.out.println("Abs path: " + f1.getAbsolutePath());
        System.out.println("Parent: " + f1.getParent());
        System.out.println(f1.exists() ? "exists" : "does not exist");
        System.out.println(f1.canWrite() ? "is writeable" : "is not writable");
        System.out.println(f1.canRead() ? "is readable" : "is not readable");
        System.out.println("is" + (f1.isDirectory() ? "" : "not") + " a directory");
        System.out.println(f1.isFile() ? "is normal file" : "might be a named pipe");
        System.out.println(f1.isAbsolute() ? "is absolute" : "is not absolute");
        System.out.println("File last modified: " + f1.lastModified());
        System.out.println("File size: " + f1.length() + " bytes");
        System.out.println("\n");

        if (f1.isDirectory()) {
            System.out.println("Directory of: " + f1.getName());
            String[] s = f1.list();

            for (int i = 0; i < s.length; i++) {
                File f = new File(f1.getName() + "/" + s[i]);
                if (f.isDirectory()) {
                    System.out.println(s[i] + " is a directory");
                } else {
                    System.out.println(s[i] + " is a file");
                }
            }
        } else {
            System.out.println(f1.getName() + " is not a directory");
        }
    }

}
