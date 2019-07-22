import java.io.*;
import java.util.*;


class AmritaGraphCopy {

    InputReader in;
    PrintWriter out;
    int n, m;
    boolean isAbsent[];
    double eps=(double)1e-6;
    long mod=(int)1e9+7;
    ArrayList<Integer> st=new ArrayList<>();
    HashSet<Integer> hs=new HashSet<>();
    int ans[]=new int[100005];

    AmritaGraphCopy() throws IOException {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String args[]) {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new AmritaGraphCopy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public void solve() {
        int n=in.nextInt();
        List<Integer>[] tree=new List[n];
        for(int i=0;i<n;i++){
            tree[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<n-1;i++){
            int u=in.nextInt()-1;
            int v=in.nextInt()-1;
            tree[u].add(v);
            tree[v].add(u);
        }
        ArrayList<ArrayList<Pair>> arr=new ArrayList<ArrayList<Pair>>();
        for(int i=0;i<n;i++){
            arr.add(new ArrayList<Pair>());
        }
        int q=in.nextInt();
        for(int i=0;i<q;i++){
            int node=in.nextInt()-1;
            int dist=in.nextInt();
            arr.get(node).add(new Pair(dist,i));
        }
        dfs(tree, arr, 0);
        for(int i=0;i<q;i++){
            out.println(ans[i]+1);
        }
    }
    static class Pair implements Comparable<Pair> {
        int u;
        int v;

        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public int hashCode() {
            int hu = (int) (u ^ (u >>> 32));
            int hv = (int) (v ^ (v >>> 32));
            return 31 * hu + hv;
        }

        public boolean equals(Object o) {
            Pair other = (Pair) o;
            return u == other.u && v == other.v;
        }

        public int compareTo(Pair other) {
            return Long.compare(u, other.u) != 0 ? Long.compare(u, other.u) : Long.compare(v, other.v);
        }

        public String toString() {
            return "[u=" + u + ", v=" + v + "]";
        }
    }
    void dfs(List<Integer>[] tree, ArrayList<ArrayList<Pair>> arr, int x){
        st.add(x);
        hs.add(x);
        for(Pair p:arr.get(x)){
            //System.out.println(x);
            //debug(st);
            int pos=Math.max(st.size()-p.u-1,0);
            ans[p.v]=st.get(pos);
        }
        for(int nei:tree[x]){
            if(!hs.contains(nei))
            {
                dfs(tree,arr,nei);
            }
        }
        st.remove(st.size()-1);
    }
    class Lca {

        int[] depth;
        int[] dfs_order;
        int cnt;
        int[] first;
        int[] minPos;
        int n;

        void dfs(List<Integer>[] tree, int u, int d) {
            depth[u] = d;
            dfs_order[cnt++] = u;
            for (int v : tree[u])
                if (depth[v] == -1) {
                    dfs(tree, v, d + 1);
                    dfs_order[cnt++] = u;
                }
        }

        void buildTree(int node, int left, int right) {
            if (left == right) {
                minPos[node] = dfs_order[left];
                return;
            }
            int mid = (left + right) >> 1;
            buildTree(2 * node + 1, left, mid);
            buildTree(2 * node + 2, mid + 1, right);
            minPos[node] = depth[minPos[2 * node + 1]] < depth[minPos[2 * node + 2]] ? minPos[2 * node + 1] : minPos[2 * node + 2];
        }

        public Lca(List<Integer>[] tree, int root) {
            int nodes = tree.length;
            depth = new int[nodes];
            Arrays.fill(depth, -1);

            n = 2 * nodes - 1;
            dfs_order = new int[n];
            cnt = 0;
            dfs(tree, root, 0);

            minPos = new int[4 * n];
            buildTree(0, 0, n - 1);

            first = new int[nodes];
            Arrays.fill(first, -1);
            for (int i = 0; i < dfs_order.length; i++)
                if (first[dfs_order[i]] == -1)
                    first[dfs_order[i]] = i;
        }

        public int lca(int a, int b) {
            return minPos(Math.min(first[a], first[b]), Math.max(first[a], first[b]), 0, 0, n - 1);
        }

        int minPos(int a, int b, int node, int left, int right) {
            if (a == left && right == b)
                return minPos[node];
            int mid = (left + right) >> 1;
            if (a <= mid && b > mid) {
                int p1 = minPos(a, Math.min(b, mid), 2 * node + 1, left, mid);
                int p2 = minPos(Math.max(a, mid + 1), b, 2 * node + 2, mid + 1, right);
                return depth[p1] < depth[p2] ? p1 : p2;
            } else if (a <= mid) {
                return minPos(a, Math.min(b, mid), 2 * node + 1, left, mid);
            } else if (b > mid) {
                return minPos(Math.max(a, mid + 1), b, 2 * node + 2, mid + 1, right);
            } else {
                throw new RuntimeException();
            }
        }
    }
    public static void debug(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream inputstream) {
            reader = new BufferedReader(new InputStreamReader(inputstream));
            tokenizer = null;
        }

        public String nextLine(){
            String fullLine=null;
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    fullLine=reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return fullLine;
            }
            return fullLine;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public long nextLong() {
            return Long.parseLong(next());
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}     