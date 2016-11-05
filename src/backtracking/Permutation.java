package backtracking;

class Permutation {
    static boolean finished = false;
    static final int MAXCANDIDATES = 100;
    static final int NMAX = 100;

    public static void main(String[] args) {
        int[] a = new int[NMAX];
        backtrack(a, 0, 5);
    }

    static void backtrack(int[] a, int k, int n) {
        int[] candidates = new int[MAXCANDIDATES];
        int nCandidates;

        if (isSolution(a, k, n)) {
            processSolution(a, k, n);
        } else {
            k++;
            nCandidates = constructCandidates(a, k, n, candidates);
            for (int i = 0; i < nCandidates; i++) {
                a[k] = candidates[i];
                makeMove(a, k, n);
                backtrack(a, k, n);
                undoMove(a, k, n);

                if (finished) return;
            }
        }
    }

    private static boolean isSolution(int[] a, int k, int n) {
        return k == n;
    }

    private static int constructCandidates(int[] a, int k, int n, int[] candidates) {
        boolean[] inPermutation = new boolean[NMAX];

        // Find out which numbers are already in the permutation
        for (int i = 0; i < k; i++) {
            inPermutation[a[i]] = true;
        }

        int nCandidates = 0;
        // Construct the candidates ...
        for (int i = 1; i <= n; i++) {
            if(!inPermutation[i]) {
                candidates[nCandidates] = i;
                nCandidates++;
            }
        }
        return nCandidates;
    }

    private static void processSolution(int[] a, int k, int n) {
        for (int i = 1; i <= k; i++) {
            System.out.printf(" %d", a[i]);
        }
        System.out.println();
    }

    private static void undoMove(int[] a, int k, int n) {
        // do nothing
    }

    private static void makeMove(int[] a, int k, int n) {
        // do nothing
    }
}
