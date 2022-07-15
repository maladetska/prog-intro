package scanner;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FastScanner implements AutoCloseable {
    private final Reader in;

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

    public FastScanner(File source) throws IOException {
        this.in = new InputStreamReader(new FileInputStream(source), StandardCharsets.UTF_8);
        init();
    }

    public FastScanner(File source, String charsetName) throws IOException {
        this.in = new InputStreamReader(new FileInputStream(source), charsetName);
        init();
    }

    public FastScanner(InputStream source) throws IOException {
        this.in = new InputStreamReader(source, StandardCharsets.UTF_8);
        init();
    }

    public FastScanner(InputStream source, String charsetName) throws IOException {
        this.in = new InputStreamReader(source, charsetName);
        init();
    }

    public FastScanner(String source) throws IOException {
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

    private void next() throws IOException {
        if (!eof) {
            pos += 1;
            checkEndOfBlock();
        }
    }

    public String nextLine() throws IOException {
        StringBuilder buff = new StringBuilder();
        while (block[pos] != '\n' && block[pos] != '\r') {
            buff.append(block[pos]);
            next();
        }
        next();

        return buff.toString();
    }

    public boolean hasNextLine() {
        return !eof;
    }

    private Integer nextInt = null;

    public Integer nextInt() throws NoSuchElementException {
        if (nextInt != null) {
            return nextInt;
        } else {
            throw new NoSuchElementException();
        }
    }

    public boolean hasNextInt() throws IOException {
        StringBuilder buff = new StringBuilder();
        while (Character.isWhitespace(block[pos]) && !eof) {
            next();
        }
        while ((Character.isDigit(block[pos]) || block[pos] == '-') && !eof) {
            buff.append(block[pos]);
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
