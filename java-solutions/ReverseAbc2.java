import myscanner.MyScannerAbc2;

public class ReverseAbc2 {
    public static int[] checkSize(int[] array, int size) throws Exception {
        int length = array.length;
        if (size >= length) {
            int[] newArray = new int[length * 2];
            System.arraycopy(array, 0, newArray, 0, length);

            return newArray;
        } else {
            return array;
        }
    }

    public static void main(String[] args) throws Exception {
        MyScannerAbc2 scanner = new MyScannerAbc2(System.in);

        int[] arrLines = new int[10];
        int[] arrNums = new int[10];

        int countLines = 0;
        int countNums = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            MyScannerAbc2 scannerLine = new MyScannerAbc2(line);

            arrNums = checkSize(arrNums, countNums);
            arrLines = checkSize(arrLines, countLines);

            while (scannerLine.hasNextInt()) {
                arrNums = checkSize(arrNums, countNums);

                arrNums[countNums++] = scannerLine.nextInt();
                arrLines[countLines]++;

            }

            scannerLine.close();
            countLines++;
        }
        scanner.close();

        for (int i = countLines - 1; i >= 0; i--) {
            for (int j = 0; j < arrLines[i]; j++) {
                System.out.print(arrNums[--countNums] + " ");
            }
            if (i != 0) {
                System.out.print("\n ");
            }
        }
    }
}
