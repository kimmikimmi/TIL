public class Problem236 {
    
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> parent = new HashMap<>();
        int visited = 0; 
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while(visited < 2) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                parent.put(cur.left.val, cur);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                parent.put(cur.right.val, cur);
                queue.add(cur.right);
            }
            
            if(cur.val == p.val || cur.val == q.val) {
                visited++;
            }
        }

        while (p != null) {
            TreeNode ptr = q;
            while (ptr != null) {
                if (p.val == ptr.val) {
                    return ptr;
                };
                ptr = parent.get(ptr.val);
            }
            p = parent.get(p.val);
        }


        return null;
    }
}