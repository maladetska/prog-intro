import scanner.FastScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
    public static void main(String[] args) throws IOException {
        Map<String, ArrayList<Integer>> words = new LinkedHashMap<>();
        FastScanner in = new FastScanner(new File(args[0]));
        StringBuilder stringBuilder = new StringBuilder();
        int wordIndex = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine() + " ";
            for (int i = 0; i < line.length(); i++) {
                char currChar = line.charAt(i);
                if (Character.isLetter(currChar) ||
                        Character.getType(currChar) == Character.DASH_PUNCTUATION ||
                        currChar == '\'') {
                    stringBuilder.append(currChar);
                } else if (!stringBuilder.toString().isEmpty()) {
                    String word = stringBuilder.toString().toLowerCase();
                    ArrayList<Integer> buff = new ArrayList<>();
                    if (words.containsKey(word)) {
                        buff = words.get(word);
                    }
                    stringBuilder.setLength(0);
                    buff.add(++wordIndex);
                    words.put(word, buff);
                }
            }
        }
        in.close();

        try (BufferedWriter out =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, ArrayList<Integer>> curr : words.entrySet()) {
                out.write(curr.getKey() + " " + curr.getValue().size());
                for (int i = 0; i < curr.getValue().size(); i++) {
                    out.write(" " + curr.getValue().get(i));
                }
                out.write('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}