public class Problem979 {
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
    public int distributeCoins(TreeNode root) {
       Map<TreeNode, Integer> w = new HashMap<>();
       Map<TreeNode, Integer> c = new HashMap<>();

       computeWeight(root, w); 
       computeCost(root, w, c);

       return c.get(root);
    }

    private void computeWeight(TreeNode node, Map<TreeNode, Integer> w) {
        if (node == null) return;

        computeWeight(node.left, w);
        computeWeight(node.right, w);
        int weight = node.val - 1;
        if (node.left != null) 
            weight += w.get(node.left);
        if (node.right != null)
            weight += w.get(node.right);
        w.put(node, weight);
    }


    private void computeCost(TreeNode node, Map<TreeNode, Integer> w, Map<TreeNode, Integer> c) {
        if (node == null) return;
        computeCost(node.left, w, c);
        computeCost(node.right, w, c);
        int cost = Math.abs(w.get(node));
        if (node.left != null) 
            cost += c.get(node.left);
        if (node.right != null)
            cost += c.get(node.right);
        c.put(node, cost);
    }
}