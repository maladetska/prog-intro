import scanner.FastScanner;
import wordMatrix.MyPair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WsppPosition {
    public static void main(String[] args) throws IOException {
        Map<String, MyPair> words = new LinkedHashMap<>();
        FastScanner in = new FastScanner(new File(args[0]));
        StringBuilder stringBuilder = new StringBuilder();
        int countLine = 0;

        while (in.hasNextLine()) {
            countLine++;
            String line = in.nextLine() + " ";
            int wordNumber = 0;
            for (int i = 0; i < line.length(); i++) {
                char currChar = line.charAt(i);
                if (Character.isLetter(currChar) ||
                        Character.getType(currChar) == Character.DASH_PUNCTUATION ||
                        currChar == '\'') {
                    stringBuilder.append(currChar);
                } else if (!stringBuilder.toString().isEmpty()) {
                    String word = stringBuilder.toString().toLowerCase();
                    MyPair buff = new MyPair();
                    if (words.containsKey(word)) {
                        buff = words.get(word);
                    }
                    stringBuilder.setLength(0);
                    buff.append(countLine, ++wordNumber);
                    words.put(word, buff);
                }
            }
        }
        in.close();

        try (BufferedWriter out =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, MyPair> curr : words.entrySet()) {
                out.write(curr.getKey() + " " + curr.getValue().size());
                for (int i = 0; i < curr.getValue().size(); i++) {
                    out.write(" " + curr.getValue().getLine(i) + ":" + curr.getValue().getWord(i));
                }
                out.write('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}