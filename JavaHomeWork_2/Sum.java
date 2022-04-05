public class Sum {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            int j = 0;
            String str = args[i] + " ";
            while (j < str.length()) {
                if (!(Character.isWhitespace(str.charAt(j)))) {
                    int first = j;
                    int k = 0;
                    if ((str.charAt(j) == '-') || (str.charAt(j) == '+')) {
                        j++;
                        k++;
                    }
                    while (!(Character.isWhitespace(str.charAt(j)))) {
                        j++;
                    }
                    if ((str.charAt(first) == '-')) {
                        sum -= Integer.parseInt(str.substring(first + k, j));
                    } 
                    else {
                        sum += Integer.parseInt(str.substring(first + k, j));
                    }
                }
                else {
                    j++;
                }

            }
        }
        System.out.println(sum);
    }
}