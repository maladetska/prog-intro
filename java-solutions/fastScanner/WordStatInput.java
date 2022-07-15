import scanner.FastScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInput {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> words = new LinkedHashMap<>();
        FastScanner in = new FastScanner(new File(args[0]));
        while (in.hasNextLine()) {
            String line = in.nextLine() + " ";
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
        }

        try (BufferedWriter out =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (String keys : words.keySet()) {
                out.write(keys + " " + words.get(keys) + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}