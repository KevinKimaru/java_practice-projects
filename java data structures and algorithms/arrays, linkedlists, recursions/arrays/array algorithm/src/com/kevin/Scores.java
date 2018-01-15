package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/5/2017.
 */
public class Scores {
    public static final int maxEntries = 10;
    protected int numEntries;
    protected GameEntry[] entries;

    public Scores() {
        entries = new GameEntry[maxEntries];
        numEntries = 0;
    }

   //the long method
    public void add(GameEntry e, String s) {
        if (numEntries == 0) {
            entries[0] = e;
            numEntries++;
            System.out.printf("Score successfully added: %s -> %d", e.getName(), e.getScore());
            for (GameEntry ge : entries)
                if (ge != null) System.out.printf("\n\n%s -> %d\n", ge.getName(), ge.getScore());
            return;
        }

        int newScore = e.getScore();

        //is the new entry  e really a high score??
        if (numEntries == maxEntries) {//the array is full
            if (newScore <= entries[numEntries - 1].getScore())
                return;     //the new entry  e is not ahigh score  in this case
        } else        //the array is not full
            numEntries++;

        //locate the place where the new high score entry e belongs
        int i = numEntries - 1;
        for (; i >= 1; i--) {
            System.out.println("I was inside the loop. count = " + i);
            if (newScore > entries[i - 1].getScore()) {
                System.out.println("I was inside the loop if. count = " + i);
                System.out.println(" entries[i]" + i + "\t" + entries[i]);
                System.out.println(" entries[i-1]" + i + "\t" + entries[i - 1]);
                entries[i] = entries[i - 1];//move entry i one to the right
                entries[i - 1] = e;//add the new score to entries
                System.out.println(" entries[i-1]" + i + "\t" + entries[i - 1]);
                System.out.println(" entries[i]" + i + "\t" + entries[i]);
            } else {
                entries[i] = e;
                System.out.println("I was inside the loop else. count = " + i);
                break;
            }
        }
        System.out.printf("Score successfully added: %s -> %d\n", e.getName(), e.getScore());
        System.out.println("\n");
        for (GameEntry ge : entries)
            if (ge != null) System.out.printf("%s -> %d\t\t", ge.getName(), ge.getScore());
            else System.out.print(ge + "\t\t");
        System.out.println("\n\n\n");
    }

    /**
     * Attempt to add a new score to the collection(if it is high enough)
     * shorter method
     */
    public void add(GameEntry e) {
        int newScore = e.getScore();

        //is the new entry  e really a high score??
        if (numEntries == maxEntries) {//the array is full
            if (newScore <= entries[numEntries - 1].getScore())
                return;     //the new entry  e is not ahigh score  in this case
        } else        //the array is not full
            numEntries++;

        //locate the place where the new high score entry e belongs
        int i = numEntries - 1;
        for (; i >= 1 && (newScore > entries[i - 1].getScore()); i--)
            entries[i] = entries[i - 1];//move entry i one to the right
        entries[i] = e; //add the new score to entries
    }

    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if ((i < 0) || (i >= numEntries))
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        GameEntry temp = entries[i];
        for (int j = i; j < numEntries - 1; j++)
            entries[j] = entries[j + 1];
        entries[numEntries - 1] = null;
        numEntries--;
        return temp;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < numEntries; i++) {
            if (i > 0) s += ", ";
            s += entries[i];
        }
        return s + "]";
    }
}
