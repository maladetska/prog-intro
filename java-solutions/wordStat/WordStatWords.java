import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;
import java.util.Map;

public class WordStatWords {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> words = new TreeMap<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
        String line = in.readLine();
        while (line != null) {
            int firstInd = -1;
            for (int i = 0; i < line.length(); i++) {
                char currChar = line.charAt(i);
                if (Character.isLetter(currChar) ||
                        Character.getType(currChar) == Character.DASH_PUNCTUATION ||
                        currChar == '\'') {
                    if (firstInd == -1) {
                        firstInd = i;
                    }
                } else if (firstInd != -1) {
                    words.merge(line.substring(firstInd, i).toLowerCase(), 1, Integer::sum);
                    firstInd = -1;
                }
            }
            if (firstInd != -1) {
                words.merge(line.substring(firstInd).toLowerCase(), 1, Integer::sum);
            }
            line = in.readLine();
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (String keys : words.keySet()) {
                out.write(keys + " " + words.get(keys) + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
