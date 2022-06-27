public class SumLong {
    public static void main(String[] args) {
        long sum = 0;
        String currNum = "";
        for (String currArg : args) {
            for (int i = 0; i < currArg.length(); i++) {
                while (i < currArg.length() && !Character.isWhitespace(currArg.charAt(i))) {
                    currNum += currArg.substring(i, i + 1);
                    i++;
                }
                if (!currNum.isEmpty()) {
                    sum += Long.parseLong(currNum);
                }
                currNum = "";
            }
        }
        System.out.println(sum);
    }
}