package search;

public class BinarySearchMin {
    // Pred: отсортированный строго по возрастанию массив целых чисел с циклическим сдвигом хотя бы на 1 и минимум с двумя элементами
    // Post a[r] : ∀ i != r : a[i] > a[r]
    public static int iterBinSearch(int[] a) {
        // отсортированный строго по возрастанию массив целых чисел с циклическим сдвигом хотя бы на 1 и минимум с двумя элементами ==> a[0] > a[a.length - 1]
        int l = 0, r = a.length - 1;
        // l = 0 && r = a.length - 1 && r - l >= 1
        // a[l] > a[r]
        // ∃!k 0 <= k < a.length - 1: a[k] > a[k + 1] ==> l <= k < r
        while (r - l > 1) {
            // r - l > 1
            int m = (r + l) / 2;
            // m == (r + l) / 2 && r - l > 1 ==> l < m < r
            if (a[l] < a[m]) {
                // a[l] < a[m] && ∃!k l <= k < r: a[k] > a[k + 1] ==> m <= k < r
                // a[l] > a[r] ==> a[m] > a[r] && левая часть массива отсортирована
                l = m;
                // l' = m && l < m < r ==> r - l' >= 1 && a[l'] > a[r]
            } else {
                // a[l] >= a[m] && строгое возрастание ==> a[l] > a[m] &&
                // ∃!k l <= k < r: a[k] > a[k + 1] ==> l <= k < m && a[m] < a[r] && правая часть массива отсортирована
                r = m;
                // r' = m && l < m < r ==> r' - l >= 1 && a[l] > a[r']
            }
        }
        // r - l == 1 && a[l] > a[r] ==> l == k && r == k + 1 && все элементы справа от r и слева от l отсортированы && a[0] > a[a.length - 1] ==>
        // все справа от r меньше, чем все элементы слева, а a[r] меньше всех справа ==> r и есть минимальный элемент
        return a[r];
    }

    // Pred: отсортированный строго по возрастанию массив целых чисел с циклическим сдвигом хотя бы на 1 и минимум с двумя элементами
    // 0 <= l < r < a.length и циклический сдвиг между l и r
    // Post a[r] : ∀ i != r : a[i] > a[r]
    public static int recBinSearch(int[] a, int l, int r) {
        // ∃!k 0 <= k < a.length - 1: a[k] > a[k + 1] ==> l <= k < r
        //отсортированный строго по возрастанию массив целых чисел с циклическим сдвигом хотя бы на 1 и минимум с двумя элементами ==> a[l] > a[r]
        // 0 <= l < r < a.length ==> r - l >= 1
        if (r - l == 1) {
            // r - l == 1 && a[l] > a[r] ==> l == k && r == k + 1 && все элементы справа от r и слева от l отсортированы && a[0] > a[a.length - 1] ==>
            // все справа от r меньше, чем все элементы слева, а a[r] меньше всех справа ==> r и есть минимальный элемент
            return a[r];
        }
        // else : r - l != 1
        // r - l >= 1 ==> r - l > 1
        int m = (r + l) / 2;
        // m == (r + l) / 2 && r - l > 1 ==> l < m < r
        if (a[m] > a[l]) {
            // a[l] < a[m] && ∃!k l <= k < r: a[k] > a[k + 1] ==> m <= k < r
            // a[l] > a[r] ==> a[m] > a[r] && левая часть массива отсортирована
            // l' = m && l < m < r ==> r - l' >= 1 && a[l'] > a[r]
            return recBinSearch(a, m, r);
        } else {
            // a[l] >= a[m] && строгое возрастание ==> a[l] > a[m] &&
            // ∃!k l <= k < r: a[k] > a[k + 1] ==> l <= k < m && a[m] < a[r] && правая часть массива отсортирована
            // r' = m && l < m < r ==> r' - l >= 1 && a[l] > a[r']
            return recBinSearch(a, l, m);
        }
    }

    public static void main(String[] args) {
        int n = args.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        if (a[0] <= a[a.length - 1]) {
            // нет циклического сдвига, так как массив отсортирован или массив состоит из одного элемента
            System.out.println(a[0]);
        } else {
            int i = iterBinSearch(a);
            int r = recBinSearch(a, 0, n - 1);
            //System.out.println(i + " " + r);
            if (i == r) {
                System.out.println(i);
            } else {
                System.out.println("-GAME-OVER-");
            }
        }
    }
}
