import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class Reggie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your zipcode: ");
//        String zipCode = scanner.nextLine();
        String zipCode = "67543";
        if (zipCode.matches("^\\d{5}(-\\d{4})?$")) {
            System.out.printf("%s is a valid zip code.\n\n\n", zipCode);
        } else {
            System.out.printf("%s is NOT a valid zip code.\n\n\n", zipCode);
        }

        String skills = "Javascript, Java, Python, HTML, CSS and MySQL";
        for (String skill : skills.split("\\W+(and\\W+)?")) {
            System.out.printf("Skill: %s \n\n", skill);
        }

        String script = "Procastination is surely not the destination, should we talk about shiny things?";
        Pattern pattern = Pattern.compile("(\\w*(sh|ti|su)\\w*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(script);

        while (matcher.find()) {
            System.out.printf("%s is a shushy word because of %s. \n",
                    matcher.group(0),
                    matcher.group(1));
        }
    }
}
