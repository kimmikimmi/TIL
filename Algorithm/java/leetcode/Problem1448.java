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
class Problem1448 {
    public int goodNodes(TreeNode root) {
      Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
      int cnt = 0;
      TreeNode curNode = root;
      int curMax = root.val;
      queue.add(new Pair<>(curNode, curMax));

      while (!queue.isEmpty()) {
        Pair<TreeNode, Integer> e = queue.poll();
        curNode = e.getKey();
        curMax = e.getValue() ;
        if (curNode.val >= curMax) {
          cnt++;
          curMax = curNode.val;
        }

        if (curNode.left != null) {
          queue.add(new Pair<>(curNode.left, curMax));
        } 
        if (curNode.right != null) {
          queue.add(new Pair<>(curNode.right, curMax));
        } 
      }

      return cnt;
    }
}