import java.io.*;
import java.util.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;


public class WordStatIndex {
	
    public static void main(String[] args){
        try {
            String input = args[0];
            String output = args[1];
			
			try {
				File file = new File(input);
				ModScanner sc = new ModScanner(file);
				LinkedHashMap<String, IntList> numbers = new LinkedHashMap<>();
				String str;
				IntList list;
				
				int i = 0;
				while (sc.hasNextWord()) {
					i++;
					str = sc.nextWord().toLowerCase();					
					if (numbers.containsKey(str)) {
						list = numbers.get(str);
						list.setFirst(list.getFirst() + 1);
					}  else {
						list = new IntList();
						list.addFirst(1);
					}
					list.addLast(i);
					numbers.put(str, list);
				}
				sc.close();
				
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
                try {
					for (Map.Entry<String, IntList> element : numbers.entrySet()) {
						writer.write(element.getKey() + " " + element.getValue().toString()+ "\n");
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
