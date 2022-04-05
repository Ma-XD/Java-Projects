import  java.util.LinkedList;
import java.lang.StringBuilder;
import java.io.*;

public class ReverseAbc {
 
	public static void main(String[] args) throws IOException {
        Scanner  sc = new Scanner(System.in);
        LinkedList<String> data = new LinkedList<>();
		StringBuilder letter = new StringBuilder();
		StringBuilder str = new StringBuilder();
		
        while (sc.hasNextLine()) {
            if (sc.hasNextInLine()) {
                while (sc.hasNextInLine()) {
					letter.append(sc.next());
                    str.append(" ").append(letter.reverse());
					letter = new StringBuilder();
                }
            }
            data.addFirst(str.reverse().toString());
            str = new StringBuilder();
            sc.goToNextLine();
        }
		for (String s : data) {
            System.out.println(s);
        }
        sc.close();
    }
}