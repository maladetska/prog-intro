import java.util.Scanner;

public class ReverseOdd2 {
    static int currPos = 0;

    static int[] arrayOfLines;
    static int countOfLines;

    static int[] arrayOfNumbers;
    static int countOfNumbers;

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
            System.out.print(arrayOfNumbers[--currPos] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        arrayOfNumbers = new int[15];
        arrayOfLines = new int[15];
        countOfLines = 0;

        while (scanner.hasNextLine()) {
            Scanner scannerLine = new Scanner(scanner.nextLine());

            if (sizeCheck(arrayOfLines, countOfLines)) {
                arrayOfLines = resizeArray(arrayOfLines);
            }

            countOfNumbers = 0;
            while (scannerLine.hasNextInt()) {
                if (sizeCheck(arrayOfNumbers, currPos)) {
                    arrayOfNumbers = resizeArray(arrayOfNumbers);
                }

                int number = scannerLine.nextInt();
                if ((countOfLines + countOfNumbers) % 2 != 0) {
                    arrayOfNumbers[currPos++] = number;
                    arrayOfLines[countOfLines]++;
                }
                countOfNumbers++;
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