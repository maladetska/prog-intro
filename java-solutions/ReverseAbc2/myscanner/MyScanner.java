package myscanner;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets; 

public class MyScanner implements AutoCloseable {
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
    
    public MyScanner(File source) throws IOException {
		this.in = new InputStreamReader(
					new FileInputStream(source), StandardCharsets.UTF_8
				);
		init();
	}

	public MyScanner(File source, String charsetName) throws IOException {
		this.in = new InputStreamReader(
						new FileInputStream(source), charsetName
					);
		init();
	}

	public MyScanner(InputStream source) throws IOException {
		this.in = new InputStreamReader(source, StandardCharsets.UTF_8);
		init();
	}
	
	public MyScanner(InputStream source, String charsetName) throws IOException {
		this.in = new InputStreamReader(source, charsetName);
		init();
	}
	
	public MyScanner(String source) throws IOException {
		this.in = new StringReader(source);
		init();
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
	
	private void toNext() throws IOException {
		if (!eof) {
			pos += 1;
			checkEndOfBlock();
		}
	}
	
	public String nextLine() throws IOException {
		StringBuffer buff = new StringBuffer();
		
		while (block[pos] != '\n' && block[pos] != '\r' && block[pos] != -1) {
			buff.append(block[pos]);
			toNext();
		}
		
		toNext();
		
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
			toNext();
		}
		
		while ((Character.isDigit(block[pos]) || block[pos] == '-') && !eof) {
			buff.append(block[pos]);
			toNext();
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
