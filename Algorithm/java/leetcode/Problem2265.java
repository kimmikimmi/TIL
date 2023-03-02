public class Problem2265 {
    
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
    int ans = 0;
    public int averageOfSubtree(TreeNode root) {
        sumAndCnt(root);

        return ans;       
    }

    private int[] sumAndCnt(TreeNode node) {
        int cnt = 1;
        int sum = node.val;

        if (node.left != null) {
            int[] res = sumAndCnt(node.left);
            cnt += res[0];
            sum += res[1];
        }
        if (node.right != null) {
            int[] res = sumAndCnt(node.right);
            cnt += res[0];
            sum += res[1];
        }

        if (sum / cnt == node.val) ans++;

        return new int[]{cnt, sum};
    }
}