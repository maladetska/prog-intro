import scanner.FastScannerAbc2;

import java.io.IOException;

public class ReverseAbc2 {
    private static final int SIZE = 15;

    static int[] arrayOfLines;
    static int countOfLines;

    static int[] arrayOfNumbers;
    static int countOfNumbers = 0;

    public static int[] resizeArray(int[] array) {
        int size = array.length;
        int[] newArray = new int[size * 2];
        System.arraycopy(array, 0, newArray, 0, size);

        return newArray;
    }

    public static boolean sizeCheck(int[] array, int count) {
        return count >= array.length;
    }

    public static void printNumber(int indexLine) {
        for (int i = 0; i < arrayOfLines[indexLine]; i++) {
            System.out.print(arrayOfNumbers[--countOfNumbers] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        FastScannerAbc2 scanner = new FastScannerAbc2(System.in);

        arrayOfNumbers = new int[SIZE];
        arrayOfLines = new int[SIZE];
        countOfLines = 0;

        while (scanner.hasNextLine()) {
            FastScannerAbc2 scannerLine = new FastScannerAbc2(scanner.nextLine());

            if (sizeCheck(arrayOfLines, countOfLines)) {
                arrayOfLines = resizeArray(arrayOfLines);
            }

            while (scannerLine.hasNextInt()) {
                if (sizeCheck(arrayOfNumbers, countOfNumbers)) {
                    arrayOfNumbers = resizeArray(arrayOfNumbers);
                }

                arrayOfNumbers[countOfNumbers++] = scannerLine.nextInt();
                arrayOfLines[countOfLines]++;
            }
            scannerLine.close();
            countOfLines++;
        }
        scanner.close();

        for (int i = countOfLines - 1; i > 0; i--) {
            printNumber(i);
            System.out.print("\n ");
        }
        printNumber(0);
    }
}
