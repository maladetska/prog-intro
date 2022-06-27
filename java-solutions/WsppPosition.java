import myscanner.MyScanner;
import wordmatrix.MyPair;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class WsppPosition {
    public static void main(String[] args) throws Exception {

        Map<String, MyPair> words = new LinkedHashMap<>();

        MyScanner in = new MyScanner(new File(args[0]));
        StringBuilder stringBuilder = new StringBuilder();

        int countLine = 0;
        while (in.hasNextLine()) {
            countLine++;
            String line = in.nextLine() + " ";
            int wordNumber = 0;

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                if (Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'') {
                    stringBuilder.append(ch);
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

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            for (Map.Entry<String, MyPair> curr : words.entrySet()) {
                out.write(curr.getKey() + " " + curr.getValue().size());
                for (int i = 0; i < curr.getValue().size(); i++) {
                    out.write(" " + curr.getValue().getLine(i) + ":" + curr.getValue().getWord(i));
                }
                out.write('\n');
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
