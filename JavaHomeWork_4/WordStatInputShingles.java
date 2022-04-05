import java.io.*;
import java.util.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;


public class WordStatInputShingles {
    public static void main(String[] args){
        try {
            String input = args[0];
            String output = args[1];
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream(input), StandardCharsets.UTF_8));
                try {
                    LinkedHashMap<String, Integer> numbers = new LinkedHashMap<>();
                    while (true) {
                        int read = reader.read();
                        if (read < 0) {
                            break;
                        }
						
                        String[] str = new String[]{"","",""};
                        int i = 0;
                        while ( (Character.isLetter(read)) || (Character.getType(read) == Character.DASH_PUNCTUATION) || (read == '\'') ) {
                            read = Character.toLowerCase(read);
                            for (int j = i; j >= 0; j--) {
                                str[j] = str[j] + (char)read;
                            }
                            if (str[0].length() == 3) {
                                numbers.put(str[0], numbers.getOrDefault(str[0], 0) + 1);
                                str[0] = str[1].substring(0);
                                str[1] = str[2].substring(0);
                                str[2] = "";
                                //	System.out.println(numbers);
                            } else {
                                i++;
                            }
                            read = reader.read();
                        }
                    }
					
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
                    try {
                        for (String key: numbers.keySet()){
                            writer.write(key + " " + numbers.get(key) + "\n");
                        }

                    } finally {
                        writer.close();
                    }
				    	
                } finally {
                    reader.close();
                }
				
            } catch (IOException e) {
                System.out.println("Input read error: " + e.getMessage());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Input error: args are not found");
        }
    }
}