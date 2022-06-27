package myscanner;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets; 

public class MyScannerAbc2 implements AutoCloseable {
	private Reader in;
	
	private static final int BLOCK_CAPACITY = 1024;
	private char[] block;
	private int blockSize = 0;
	private int pos = 0;
	
	private boolean eof = false;
	
	private void init() throws IOException {
		if (!eof) {
			this.block = new char[BLOCK_CAPACITY];
			this.blockSize = in.read(this.block);
		}
	}
    
    private void checkEndOfBlock() throws IOException {
		if (pos == blockSize) {
			pos = 0;
			init();
			if (blockSize == -1) {
				eof = true;
			}
		}
	}
	private void next() throws IOException {
		if (!eof) {
			pos += 1;
			checkEndOfBlock();
		}
	}
	
	private char fromLetterToDigit(char letter) throws IOException {
		return (char)(letter - 49);
	}
	
    
    public MyScannerAbc2(File source) throws IOException {
		this.in = new InputStreamReader(
					new FileInputStream(source), StandardCharsets.UTF_8
				);
		init();
	}

	public MyScannerAbc2(File source, String charsetName) throws IOException {
		this.in = new InputStreamReader(
						new FileInputStream(source), charsetName
					);
		init();
	}

	public MyScannerAbc2(InputStream source) throws IOException {
		this.in = new InputStreamReader(source, StandardCharsets.UTF_8);
		init();
	}
	
	public MyScannerAbc2(InputStream source, String charsetName) throws IOException {
		this.in = new InputStreamReader(source, charsetName);
		init();
	}
	
	public MyScannerAbc2(String source) throws IOException {
		this.in = new StringReader(source);
		init();
	}
	
	public String nextLine() throws IOException {
		StringBuffer buff = new StringBuffer();
		
		while (block[pos] != '\n') {
			buff.append(block[pos]);
			next();
		}
		
		next();
		
		return buff.toString();
	}
	
	public boolean hasNextLine() throws IOException {
		return !eof;
	}
	
	private Integer nextInt = null;
	
	public Integer nextInt() throws NoSuchElementException{
		if (nextInt != null) {
			return nextInt;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public boolean hasNextInt() throws IOException{
		StringBuffer buff = new StringBuffer();
		
		while (Character.isWhitespace(block[pos]) && !eof) {
			next();
		}
		
		while ((Character.isDigit(fromLetterToDigit(block[pos])) || block[pos] == '-') && !eof) {
			if (block[pos] == '-') {
				buff.append('-');
			} else {
				buff.append(fromLetterToDigit(block[pos]));				
			}
			next();
		}
		
		try {
			nextInt = Integer.parseInt(buff.toString());
			return true;
		} catch (NumberFormatException e) {
			nextInt = null;
			return false;
		}
	}
	
	@Override
    public void close() throws IOException {
		in.close();
	}
}
