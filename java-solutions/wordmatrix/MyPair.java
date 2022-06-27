package wordmatrix;

import java.io.*;
import java.util.ArrayList;

public class MyPair {
	private ArrayList<Integer> line;
	private ArrayList<Integer> word;
	
	public MyPair() {
		this.line = new ArrayList<Integer>();
		this.word = new ArrayList<Integer>();
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
