package wordMatrix;

import java.util.ArrayList;

public class MyPair {
    private final ArrayList<Integer> line;
    private final ArrayList<Integer> word;

    public MyPair() {
        this.line = new ArrayList<>();
        this.word = new ArrayList<>();
    }

    public int getLine(int ind) {
        return line.get(ind);
    }

    public int getWord(int ind) {
        return word.get(ind);
    }

    public void append(int theLine, int theWord) {
        line.add(theLine);
        word.add(theWord);
    }

    public int size() {
        return word.size();
    }
}
