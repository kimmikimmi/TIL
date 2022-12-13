// Tree 를 이용한 집합의 처리 
// Tree 를 나타낼 때 보통은 부모 노드가 자식 노드를 가리키도록 하지만 
// 여기서는 자식 노드가 부모 노드를 가리키게 한다.
public class Union {
   
    private int[] rank;
    private int[] p;

    public Union(int size) {
        this.p = new int[size];
        this.rank = new int[size];
    }

    public void makeSet(int x) {
        p[x] = x;
        rank[x] = 0;
    }

    public void union(int x, int y) {
        int x1 = findSet(x);        
        int y1 = findSet(y);
        if (rank[x1] > rank[y1]) {
            p[y1] = x1;
        } else {
            p[x1] = y1;
            if (rank[x1] == rank[y1]) {
                rank[y1] += 1;
            }
        }
    }

    // return root node of the tree.
    public int findSet(int x) {
        if (p[x] != x) { 
            // 만나는 모든 노드가 직접 루트를 가르키도록 포인터를 바꾸어준다.
            p[x] = findSet(p[x]);
        }
        
        return p[x];
    }

    public boolean isSameSet(int x, int y) {
        return findSet(x) == findSet(y);
    }

    public static void main(String[] args) {
        Union u = new Union(100);

        u.makeSet(1);
        u.makeSet(2);

        System.out.println(u.isSameSet(1, 2));

        u.union(1, 2);
        u.makeSet(3);
        System.out.println(u.isSameSet(1, 2));
        System.out.println(u.isSameSet(1, 3));
    }
}
