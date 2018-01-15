/**
 * Created by Kevin Kimaru Chege on 7/31/2017.
 */
public class Example {
    public static void main(String[] args) {
        System.out.println("We are making a new PEZ dispenser");
        System.out.printf("FUN FACT: There are %d PEZ allowed in every dispenser %n", PezDispenser.MAX_PEZ);

        PezDispenser dispenser = new PezDispenser("Kevin");

        System.out.printf("The dispenser is %s %n", dispenser.getCharacterName());

        if (dispenser.isEmpty()) System.out.println("Dispenser is empty");

        System.out.println("Filling the dispenser with delicious PEZ...");

        dispenser.fill();

        if (!dispenser.isEmpty()) System.out.println("Dispenser is full");

        while (dispenser.dispense()) {
            System.out.println("Chomp");
        }

        if (dispenser.isEmpty()) System.out.println("Ate all PEZ");

        dispenser.fill(4);
        dispenser.fill(2);
        while (dispenser.dispense()) {
            System.out.println("Chomp");
        }
        try {
            dispenser.fill(35);
        } catch (IllegalArgumentException iae) {
            System.out.printf("Error caught. The error is %s", iae.getMessage());
        }
    }
}
