/**
 * Created by Kevin Kimaru Chege on 7/31/2017.
 */
public class PezDispenser {
    public static final int MAX_PEZ = 12;

    final private String characterName;
    private int pezCount;

    public PezDispenser(String characterName) {
        this.characterName = characterName;
        pezCount = 0;
    }

    public boolean isEmpty() {
        return pezCount == 0;
    }

    public boolean dispense() {
        boolean wasDispensed = false;
        if (!isEmpty()) {
            pezCount--;
            wasDispensed = true;
        }
        return wasDispensed;
    }


    public void fill() {
       fill(MAX_PEZ);
    }

    public void fill(int pezAmount) {
        int newAmount = pezCount += pezAmount;
        if (newAmount > MAX_PEZ) {
            throw new IllegalArgumentException("Too much PEZ!!");
        }
        pezCount = newAmount;
    }

    public String getCharacterName() {
        return characterName;
    }
}
