import java.util.Scanner;

public class Reverse {
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

/************* SOLUTION WITH ArrayList *************/
/*
import java.util.ArrayList;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var array = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner scannerLine = new Scanner(line);

            array.add("\n");
            while (scannerLine.hasNextInt()) {
                array.add(scannerLine.nextInt());
            }
            scannerLine.close();
        }
        scanner.close();

        for (int i = array.size() - 1; i >= 0; i--)
            System.out.print(" " + array.get(i));
    }
}
*/