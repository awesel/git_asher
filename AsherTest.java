import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsherTest {

    @Test
    public void testMakeBlob() throws IOException, FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory: " + currentDirectory);
        System.out.println("AHHHHHH");

        Blob blob = new Blob("test.txt");
        String expectedSha = "cf5218b1f770dcaf2df718c0232ae5bf8f619ba6";

        blob.makeBlob();

        String toObjectsFolderPath = "./objects/" + expectedSha;
        File thing = new File(toObjectsFolderPath);
        String expectedContent = readFile(thing);
        String actualContent = readFile(new File("test.txt"));

        assertEquals(expectedContent, actualContent);
    }

    public static String readFile(File file) {
        String currentString = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                char nextChar = (char) reader.read();
                currentString += nextChar;

            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("nah");
        }
        return currentString;

    }

    public static void main(String args[]) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory: " + currentDirectory);
    }

}
