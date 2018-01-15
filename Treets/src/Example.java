import com.kevin.Treet;
import com.kevin.Treets;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class Example {
    public static void main(String[] args) {
//        Treet treet = new Treet("Kevin", "Hello World @Robert #treet", new Date(23452464374586L));
//        Treet secondTreet = new Treet("Robert", "Java is cool @Craig #treet", new Date(23452154286L));
//        Treet thirdTreet = new Treet("Robert", "Java is cool  @Robert", new Date(234521348986L));
//        Treet fouthTreet = new Treet("Vanquda", "Java is cool  @Robert", new Date(2165756586L));
//        Treet fifthTreet = new Treet("Robert", "Java is cool @Craig", new Date(2341568794156L));
//        Treet sixthTreet = new Treet("Rose", "Java is cool #treet", new Date(2342552686L));
//        Treet seventhTreet = new Treet("Violet", "Java is cool #treet", new Date(23145866586L));
//        Treet eigthTreet = new Treet("Violet", "Java is cool #java", new Date(214544356586L));
//        Treet ninethTreet = new Treet("Peris", "Java is cool #C+", new Date(23426356586L));
//        Treet tenthTreet = new Treet("Nancy", "Java is cool #C+", new Date(234534586586L));
//        Treet eleventhTreet = new Treet("Eliud", "Java is cool #treet", new Date(2436346356586L));
//        Treet twelveTreet = new Treet("Eric", "Java is cool @Eric", new Date(225625456266L));
//        Treet thirteenTreet = new Treet("Nikita", "Java is cool @Craig", new Date(2345256456L));
//        Treet fourteenTreet = new Treet("Peris", "Java is cool", new Date(234256526526L));
//        System.out.printf("This is a new Treet %S%n", treet);
//
//        System.out.println("The words are: ");
//        for (String word : treet.getWords()) {
//            System.out.println(word);
//        }
//
//        Treet[] treets = {treet, secondTreet, thirdTreet, fouthTreet, fifthTreet, sixthTreet, seventhTreet, eigthTreet,
//                ninethTreet, tenthTreet, twelveTreet, thirteenTreet, fourteenTreet};
//        Arrays.sort(treets);
//
//        for (Treet exampleTreet : treets){
//            System.out.println(exampleTreet);
//        }
//
//        Treets.save(treets);

        Treet[] reloadedTreets = Treets.load();
        System.out.printf("There are %d treets.\r\n", reloadedTreets.length);

        Set<String> allHashTags = new HashSet<>();
        Set<String> allMentions = new TreeSet<>();
        for (Treet treeet: reloadedTreets) {
            allHashTags.addAll(treeet.getHashTags());
            allMentions.addAll(treeet.getMentions());
        }
        System.out.printf("Hash tags: %s \r\n", allHashTags);
        System.out.printf("Mentions: %s \r\n", allMentions);

        Map<String, Integer> hashTagCounts = new HashMap<>();
        for (Treet treeet: reloadedTreets) {
            for (String hashTag: treeet.getHashTags()) {
                Integer count = hashTagCounts.get(hashTag);
                if (count == null) {
                    count = 0;
                }
                count ++;
                hashTagCounts.put(hashTag, count);
            }
        }
        System.out.printf("Hash tag counts: %S\n\n\n", hashTagCounts);

        Map<String, List<Treet>> treetsByAuthor = new HashMap<>();
        for (Treet treeet: reloadedTreets) {
            List<Treet> authoredTreets = treetsByAuthor.get(treeet.getAuthor());
            if (authoredTreets  == null) {
                authoredTreets = new ArrayList<>();
                treetsByAuthor.put(treeet.getAuthor(), authoredTreets);
            }
            authoredTreets.add(treeet);
        }

        System.out.printf("Treets by author: %s \r\n", treetsByAuthor);
        System.out.printf("Treets by robert: %s \r\n", treetsByAuthor.get("Robert"));
    }
}
