
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

    public TreeNode insert(TreeNode root, int val) {
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
        return root;
    }

    public TreeNode delete(TreeNode root, int v) {
        if (root == null) {
            return root;
        }
        // Find out the node to be deleted.
        if (root.value == v) {
            if (root.right == null && root.left == null) {
                root = null;   
            // case 2: has single child
            } else if (root.right == null) {
                root = root.left;
            } else if (root.left == null) {
                root = root.right;
            // case 3: has two children
            } else {
                // 1. find left most child's value.  
                // 2. put the valut into root, 
                // 3. remove the node.
                root.value = findLargestValueFromLeft(root.left);
                root.left = delete(root.left, root.value);
            }
        } else if (root.value > v) {
            root.left = delete(root.left, v);
        } else {
            root.right = delete(root.right, v);
        }

        return root;
    }

    private int findLargestValueFromLeft(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.value;
    }

    public static void main(String[] args) {
        TreeNode root = null;
 
        BinarySearchTree bst = new BinarySearchTree();
        root = bst.insert(root, 2);
        root = bst.insert(root, 3);
        root = bst.insert(root, 1);

        assert bst.search(root, 1) != null; 
        assert bst.search(root, 2) != null; 
        assert bst.search(root, 3) != null; 

        bst.delete(root, 2);
        assert bst.search(root, 2) == null; 
    }
}