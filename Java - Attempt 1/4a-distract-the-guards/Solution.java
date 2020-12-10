import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * Solves how many guards will be left over after we make our pairings. Uses Edmonds' Blossom Algorithm to find
     * Maximum Matching of a graph containing edges between guards who can potentially pair up.
     *
     * @param banana_list A list of guards and how many bananas each one has.
     * @return How many guards will be without a distraction.
     */
    public static int solution(int[] banana_list) {
        int length = banana_list.length;
        List[] guards = new List[length];
        for (int i = 0; i < length; i++) {
            guards[i] = new ArrayList<>();
        }
        for(int i = 0; i < length; i++){
            for(int j = i; j < length; j++){
                if(loop(banana_list[i], banana_list[j])){
                    guards[i].add(j); guards[j].add(i);
                }
            }
        }
        return length - BlossomAlgorithm.maxMatching(guards);
    }

    /**
     * Determines whether or not two guards will enter an infinite loop with each other.
     * Mod represents the sum of the two guards bananas, I'm treating this as a modular arithmetic problem, since
     * the guards can be represented as Guard A has X bananas, and Guard B has Mod - X bananas. We can look at each
     * wager as both Guards A and B doubling their values. So Guard A gets 2X, Guard B gets Mod - 2X. And because it's
     * modular arithmetic, it always stays within the confines of the their total. Using this knowledge, we can check
     * to see if they will ever have the same number of bananas in 3 steps.
     * 1. If they have the same number of bananas to start with there's no reason to pair them.
     * 2. If their sum total of bananas is odd, they'll loop forever.
     * 3. If their sum total is even, they'll loop forever so long as the smaller of the two guard's banana counts (or
     * the bigger guard represented as mod - x) will never be divisible by mod no matter how many times we multiply it
     * by 2. We can check this by going about this a bit backwards. We keep shifting the bits of mod right until there
     * are no more trailing zeros (effectively removing the highest power of 2 factor). Then check if it divides the
     * smaller guard amount. If it doesn, they won't loop. If it doesn't, they will loop.
     * An example of this is:
     * Guard 1 has 10, Guard 2 has 90. Mod will equal 100. 100 / 2 = 50, 50 / 2 = 25. You can't divide 10/25, so we
     * expect these two guards to loop forever. [10, 90] -> [20, 80] -> [40, 60] -> [80, 20] -> [60, 40] -> etc.
     * However if Guard 1 has 25 and Guard 2 has 75, 25/25 is 1. So 25%25 = 0. We expect this to not loop.
     * [25, 75] -> [50, 50] And the guards get bored and stop.
     *
     * @return true if the two guards loop forever.
     */
    public static boolean loop(int guard1, int guard2) {
        if (guard1 == guard2)
            return false;
        int mod = guard1 + guard2;
        if (mod % 2 != 0)
            return true;
        mod = (mod >> Integer.numberOfTrailingZeros(mod));
        int smallerGuard = (guard1 < guard2) ? guard1 : guard2;
        return (smallerGuard % mod != 0);
    }

    /**
     * References used:
     * https://en.wikipedia.org/wiki/Blossom_algorithm
     * https://www-m9.ma.tum.de/graph-algorithms/matchings-blossom-algorithm/index_en.html
     * https://github.com/indy256/codelibrary/blob/master/java/graphs/matchings/MaxMatchingEdmonds.java
     * https://codeforces.com/blog/entry/49402
     */
     static class BlossomAlgorithm {

        /**
         * Method returns maximum number of guards that can be placed into pairs.
         * @param graph An array of Integer Lists, each index of the array representing a guard, and the List in each
         *              index of the array contains a list of what other guards (identified by their index in the array)
         *              can be paired up successfully with the guard represented by that index. For example, if we have
         *              a graph representing the guards that can be drawn out as such:
         *
         *                 [0]
         *                  |
         *                 [1]
         *                /   \
         *              [2]---[3]
         *
         *              Representing that guard 1 can loop with guards 0, 2, and 3, guard 0 can loop with guard 1 only,
         *              and so on, then we would set up the array as follows:
         *              [{1}] [{0,2,3}] [{1,3}] [{1,2}]
         *              Thus creating an adjacency list that we can perform the Blossom Algorithm on.
         * @return Number of guards distracted
         */
        static public int maxMatching(List<Integer>[] graph) {
            int length = graph.length;
            int[] match = new int[length];
            Arrays.fill(match, -1);
            int[] parent = new int[length];
            for (int i = 0; i < length; ++i) {
                if (match[i] == -1) {
                    int u = findPath(graph, match, parent, i);
                    while (u != -1) {
                        int v = parent[u];
                        int w = match[v];
                        match[v] = u;
                        match[u] = v;
                        u = w;
                    }
                }
            }
            int matches = 0;
            for (int i = 0; i < length; ++i)
                if (match[i] != -1)
                    ++matches;
            //Normally, we would want to return matches / 2 so that we would get the number of pairings made, but in
            //this case, we care more about how many guards are paired up, so we can just return the number of matches
            //as is.
            return matches;
        }

        static int leastCommonAncestor(int[] match, int[] parent, int u, int v) {
            boolean[] usedInPath = new boolean[match.length];
            while (true) {
                usedInPath[u] = true;
                if (match[u] == -1) break;
                u = parent[match[u]];
            }
            while (true) {
                if (usedInPath[v]) return v;
                v = parent[match[v]];
            }
        }

        static void markPath(int[] match, boolean[] blossom, int[] parent, int u, int v, int children) {
            for (; u != v; u = parent[match[u]]) {
                blossom[u] = blossom[match[u]] = true;
                parent[u] = children;
                children = match[u];
            }
        }

        static int findPath(List<Integer>[] graph, int[] match, int[] parent, int root) {
            int n = graph.length;
            boolean[] used = new boolean[n];
            Arrays.fill(parent, -1);
            int[] base = new int[n];
            for (int i = 0; i < n; ++i)
                base[i] = i;

            used[root] = true;
            int qh = 0;
            int qt = 0;
            int[] q = new int[n];
            q[qt++] = root;
            while (qh < qt) {
                int v = q[qh++];

                for (int to : graph[v]) {
                    if (base[v] == base[to] || match[v] == to) continue;
                    if (to == root || match[to] != -1 && parent[match[to]] != -1) {
                        int currentBase = leastCommonAncestor(match, parent, v, to);
                        boolean[] blossom = new boolean[n];
                        markPath(match, blossom, parent, v, currentBase, to);
                        markPath(match, blossom, parent, to, currentBase, v);
                        for (int i = 0; i < n; ++i)
                            if (blossom[base[i]]) {
                                base[i] = currentBase;
                                if (!used[i]) {
                                    used[i] = true;
                                    q[qt++] = i;
                                }
                            }
                    } else if (parent[to] == -1) {
                        parent[to] = v;
                        if (match[to] == -1)
                            return to;
                        to = match[to];
                        used[to] = true;
                        q[qt++] = to;
                    }
                }
            }
            return -1;
        }
    }
}
