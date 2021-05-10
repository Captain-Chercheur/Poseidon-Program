
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;


public class databaseVisualizer {

    public static void databaseVisualizer() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Page page = objectMapper.readValue(new File("sampleJSONFile.json"), Page.class);

            printParsedObject(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printParsedObject(Page page) {
        printPageInfo(page.getPageInfo());
        System.out.println();
        printPosts(page.getPosts());
    }

    private static void printPageInfo(PageInfo pageInfo) {
        System.out.println("Page Info;");
        System.out.println("**********");
        System.out.println("\tPage Name : " + pageInfo.getPageName());
        System.out.println("\tPage Pic  : " + pageInfo.getPagePic());
    }

    private static void printPosts(Post[] posts) {
        System.out.println("Page Posts;");
        System.out.println("**********");
        for(Post post : posts) {
            printPost(post);
        }
    }

    private static void printPost(Post post) {
        System.out.println("\tPost Id                   : " + post.getPost_id());
        System.out.println("\tActor Id                  : " + post.getActor_id());
        System.out.println("\tPic Of Person Who Posted  : " + post.getPicOfPersonWhoPosted());
        System.out.println("\tName Of Person Who Posted : " + post.getNameOfPersonWhoPosted());
        System.out.println("\tMessage                   : " + post.getMessage());
        System.out.println("\tLikes Count               : " + post.getLikesCount());
        System.out.println("\tComments                  : " + Arrays.toString(post.getComments()));
        System.out.println("\tTime Of Post              : " + post.getTimeOfPost());
    }

}