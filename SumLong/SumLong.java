public class SumLong {
    public static void main(String[] args) {
        long sum = 0;
        for (String currArg: args) { 
            for (int i = 0; i < currArg.length(); i++) {
		int ctrlInd = i;
		while (i < currArg.length() && !Character.isWhitespace(currArg.charAt(i))) {
		    i++;
		}
		String currNum = currArg.substring(ctrlInd, i);
		if (!currNum.isEmpty()) {
		    sum += Long.parseLong(currNum);
		}
	    }
	}
	System.out.println(sum);
    }
}
