package search;

public class BinarySearch {

    // Pred: ∀i,j: i < j ==> a[i] >= a[j] && a.length >= 2 && a[0] == +inf && a[a.length - 1] == -inf
    // Post:  r = r' : a[r'] <= x && ∀ i < r' : a[i] > x
    public static int iterBinSearch(int[] a, int x) {
        // ∀i,j: i < j ==> a[i] >= a[j] && a.length >= 2 && a[0] == +inf && a[a.length - 1] == -inf
        int l = 0, r = a.length - 1;
        // ∀i,j: i < j ==> a[i] >= a[j] && l == 0 && r == a.length - 1 && r >= 1 && r - l >= 1;
        // a[l] == +inf && a[r] == -inf && a[l] > x > a[r]
        // a[l] > x >= a[r] && r - l >= 1
        while (r - l > 1) {
            // r - l > 1
            int m = (r + l) / 2;
            // m == (r + l) / 2 && l < m < r
            if (a[m] > x) {
                // l < m < r && a[m] > x && ∀i < m : a[i] > x
                l = m;
                // l == m && a[l] > x && r - l >= 1
            } else {
                // l < m < r && a[m] <= x && ∀i > m : a[i] <= x
                r = m;
                // r == m && a[r] <= x && r - l >= 1
            }
        }
        // a[l] > x >= a[r] && r - l == 1 ==> r : a[r] <= x && ∀ i < r : a[i] > x
        // r = r': a[r'] <= x && ∀ i < r' : a[i] > x
        return r;
    }

    // Pred: ∀i,j: i < j ==> a[i] >= a[j] && a.length >= 2 && a[0] == +inf && a[a.length - 1] == -inf
    // l >= 0 && r <= a.length - 1
    // a[l] > x >= a[r] && r - l >=  1
    // Post:  r = r' : a[r'] <= x && ∀ i < r' : a[i] > x
    public static int recBinSearch(int[] a, int x, int l, int r) {
        // ∀i,j: i < j ==> a[i] >= a[j] && a.length >= 2 && a[0] == +inf && a[a.length - 1] == -inf
        // l >= 0 && r <= a.length - 1
        // a[l] > x >= a[r] && r - l >= 1
        if (r - l == 1) {
            // a[l] > x >= a[r] && r - l == 1 ==> r : a[r] <= x && ∀ i < r : a[i] > x
            return r;
            // r = r': a[r'] <= x && ∀ i < r' : a[i] > x
        }
        // else: r - l > 1
        int m = (r + l) / 2;
        // m == (r + l) / 2 && l < m < r
        if (a[m] > x) {
            // l < m < r && a[m] > x && ∀i < m : a[i] > x
            return recBinSearch(a, x, m, r);
            // l' == m && r' == r &&  a[l'] > x && r' - l' >= 1
        } else {
            // l < m < r && a[m] <= x && ∀i > m : a[i] <= x
            return recBinSearch(a, x, l, m);
            // r' == m && l' == l && a[r'] <= x && r' - l' >= 1
        }
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int n = args.length - 1;
        int[] a = new int[n + 2];
        a[0] = Integer.MAX_VALUE;
        a[n + 1] = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        int i = iterBinSearch(a, x) - 1;
        int r = recBinSearch(a, x, 0, n + 1) - 1;
        if (i == r) {
            System.out.println(i);
        } else {
            System.out.println("-GAME-OVER-");
        }
    }
}