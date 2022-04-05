import java.io.*;
import java.util.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;


public class WordStatSortedLineIndex {
	
    public static void main(String[] args){
        try {
            String input = args[0];
            String output = args[1];
			
			try {
				File file = new File(input);
				ModScanner sc = new ModScanner(file);
				Map<String, IntList> numbers = new TreeMap<>();
				String str;
				int row = 1, numInRow = 0;
				IntList list;
				while (sc.hasNextWord()) {
					if (!(sc.hasNextWordInLine())) {
						row++;
						numInRow = 0;
					}
					numInRow++;
					str = sc.nextWord().toLowerCase();;
					if (numbers.containsKey(str)) {
						list = numbers.get(str);
						list.setFirst(list.getFirst() + 1);
					}  else {
						list = new IntList();
						list.addFirst(1);
					}
					list.addLast(row);
					list.addLast(numInRow);
					numbers.put(str, list);
				}
				sc.close();
				
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
                try {
					for (Map.Entry<String, IntList> element : numbers.entrySet()) {
                        writer.write(element.getKey() + " " + element.getValue().lineIndex()+ "\n");
                    }	
                } finally {
                    writer.close();
				}
				
			} catch (IOException e) {
				System.out.println("Input read error: " + e.getMessage());
			} 
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Input error: args are not found");
        }
    }
}
