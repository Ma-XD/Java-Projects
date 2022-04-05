import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;

public class ReverseMin {

    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        LinkedList<int[]> data = new LinkedList<>();
        int maxSizeOfRow = 0;

        while (sc.hasNextLine()) {
            Scanner scanRow = new Scanner(sc.nextLine());
            int[] array = new int[]{};
            int i = 0;
   
            while (scanRow.hasNextInt()) {
                if (i == array.length){
                    int[] temp = array.clone();
                    array = new  int[temp.length + 1];
                    System.arraycopy(temp, 0, array, 0 , temp.length);
                }
                array[i] = scanRow.nextInt();
                i++;
            }
            i = 0;
            if (maxSizeOfRow < array.length) {
                maxSizeOfRow = array.length;
            }
            data.addLast(array);
        }

        int[] minInRow = new int[data.size()];
        int[] minInCol = new int[maxSizeOfRow];
        for (int i = 0; i < data.size(); i++) {
            minInRow[i] = Integer.MAX_VALUE;;
        }
        for (int i = 0; i < maxSizeOfRow; i++) {
            minInCol[i] = Integer.MAX_VALUE;;
        }

        int i = 0, j = 0;
        for (int[] row : data) {
            for (int a : row){
                if (minInRow[i] > a){
                    minInRow[i] = a;
                }
                if (minInCol[j] > a){
                    minInCol[j] = a;
                }
                j++;
            }
            j = 0;
            i++;
        }

        i = 0;
        j = 0;
        for (int[] row : data) {
            for (int a : row){
                System.out.print(Math.min(minInCol[j], minInRow[i]) + " ");
                j++;
            }
            System.out.println();
            j = 0;
            i++;
        }
    }
}
