import java.io.*;
import java.util.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;


public class WordStatInput {
	
    public static void main(String[] args){
        try {
            String input = args[0];
            String output = args[1];
			try {
				File file = new File(input);
				ModScanner sc = new ModScanner(file);
				LinkedHashMap<String, Integer> numbers = new LinkedHashMap<>();
				String str;
				while (sc.hasNextWord()) {
					str = sc.nextWord().toLowerCase();
					numbers.put( str, numbers.getOrDefault( str, 0) + 1);
				}
				sc.close();
			
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
                try {
                    for (String key: numbers.keySet()){
                        writer.write(key + " " + numbers.get(key) + "\n");
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
