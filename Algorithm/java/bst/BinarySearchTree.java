
class BinarySearchTree {
    
    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int v) {
            value = v;
        }
    }   

    public TreeNode search(TreeNode root, int val) {
        if (root == null || root.value == val) {
            return root;
        }

        if (root.value > val) {
            return search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        if (val < root.value) {
            root.left = insert(root.left, val);
        } else if (val > root.value){
            root.right = insert(root.right, val);
        }

        return root; 
    }

    public static void main(String[] args) {
        TreeNode root = null;
 
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(root, 2);
        bst.insert(root, 3);
        bst.insert(root, 1);

        assert bst.search(root, 1) != null; 
        assert bst.search(root, 2) != null; 
        assert bst.search(root, 3) != null; 
        assert bst.search(root, 4) == null; 
    }
}