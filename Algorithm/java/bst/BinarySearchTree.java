
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

    public TreeNode rInsert(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        if (val < root.value) {
            root.left = rInsert(root.left, val);
        } else if (val > root.value){
            root.right = rInsert(root.right, val);
        }

        return root; 
    }

    public void insert(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);

        if (root == null) {
            root = node;
        } else {
            TreeNode p = null; 
            TreeNode tmp = root;
            while (tmp != null) {
                p = tmp;
                if (val < tmp.value) {
                    tmp = tmp.left;
                } else if(val > tmp.value) {
                    tmp = tmp.right;
                } else {
                    throw new IllegalArgumentException();
                }
            }

            if (val < p.value) {
                p.left = node;
            } else {
                p.right = node;
            }
        }
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