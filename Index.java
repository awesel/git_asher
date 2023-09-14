import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Index {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Index newIndex = new Index();
        newIndex.init();
        newIndex.add("testIndex.txt");
        newIndex.delete("testIndex.txt");
    }

    public void init() throws IOException {
        File newFile = new File("./objectsindex");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }

        File folder = new File("./objects");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void add(String fileName) throws NoSuchAlgorithmException, IOException {
        Blob blubber = new Blob(fileName);
        String sha = blubber.getShaName();
        if (existsAlready(fileName, sha)) {
            return;
        }
        delete(fileName);
        FileWriter fileWriter = new FileWriter("index", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(fileName + " : " + sha + '\n');
        bufferedWriter.close();
        fileWriter.close();

    }

    public void delete(String fileName) throws IOException, NoSuchAlgorithmException {
        File ogFile = new File("index");
        File temp = new File("temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(ogFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

        String line;
        while (br.ready()) {
            line = br.readLine();

            // splits at the space, your welcome
            String[] name = line.split("\\s+");

            if (!name[0].equals(fileName)) {
                bw.write(line);
                bw.write('\n');
            } else {
                System.out.println("ran");
                File deleteFile = new File("./objects/" + name[2] + ".zip");
                deleteFile.delete();
            }

        }

        ogFile.delete();
        temp.renameTo(ogFile);
        br.close();
        bw.close();

    }

    public boolean existsAlready(String fileName, String hash) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("index"));

        String line;
        while (br.ready()) {
            line = br.readLine();
            String[] name = line.split("\\s+");
            if (name[0].equals(fileName) && name[2].equals(hash)) {
                br.close();
                return true;
            }

        }
        br.close();
        return false;

    }
}