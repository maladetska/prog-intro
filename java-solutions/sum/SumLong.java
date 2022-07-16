public class SumLong {
    public static void main(String[] args) {
        long sum = 0;
        for (String currArg : args) {
            for (int currInd = 0; currInd < currArg.length(); currInd++) {
                int firstInd = currInd;
                while (currInd < currArg.length() && !Character.isWhitespace(currArg.charAt(currInd))) {
                    currInd++;
                }
                if (firstInd != currInd) {
                    sum += Long.parseLong(currArg.substring(firstInd, currInd));
                }
            }
        }
        System.out.println(sum);
    }
}