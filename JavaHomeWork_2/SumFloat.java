public class SumFloat {

    public static void main(String[] args) {
        float sum = 0;
        for (int i = 0; i < args.length; i++) {
            int j = 0;
            String str = args[i] + " ";
            while (j < str.length()) {
                if (!(Character.isWhitespace(str.charAt(j)))) {
                    int first = j;
                    int k = 0;
                    while (!(Character.isWhitespace(str.charAt(j)))) {
                        j++;
                    }
                    sum += Float.parseFloat(str.substring(first + k, j));
                } else {
                    j++;
                }

            }
        }
        System.out.println(sum);
    }
}