package backtracking;


class Subset {
    static boolean finished = false;
    static final int MAXCANDIDATES = 100;
    static final int NMAX = 100;

    public static void main(String[] args) {
        boolean[] a = new boolean[NMAX];
        backtrack(a, 0, 3);
    }

    static void backtrack(boolean[] a, int k, int n) {
        boolean[] candidates = new boolean[MAXCANDIDATES];
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

    private static boolean isSolution(boolean[] a, int k, int n) {
        return k == n;
    }

    private static int constructCandidates(boolean[] a, int k, int n, boolean[] candidates) {
        candidates[0] = true;
        candidates[1] = false;
        return 2;
    }

    private static void processSolution(boolean[] a, int k, int n) {
        System.out.printf("{");
        for (int i = 1; i <= k; i++) {
            if (a[i]) {
                System.out.printf(" %d", i);
            }
        }
        System.out.printf(" }\n");
    }

    private static void undoMove(boolean[] a, int k, int n) {
        // do nothing
    }

    private static void makeMove(boolean[] a, int k, int n) {
        // do nothing
    }
}
