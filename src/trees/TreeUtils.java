package trees;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/13/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeUtils {


    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(10);
        root.left = create(5);
        root.right = create(20);
        DefaultMutableTreeNode n;
        root.left.right = create(100);
        root.left.right = create(30);

        DefaultMutableTreeNode node;

    }

    static TreeNode create(int a){
        return new TreeNode(a);
    }

    static class TreeNode<T extends Comparable> {

        protected TreeNode left;
        protected TreeNode right;
        protected Comparable<T>  value;

        public TreeNode(T value){
            this.value=value;
        }

        public boolean search(TreeNode n, T value){
            if(n.value.equals(value) || n==null){
                System.out.println("\nFound Value: " + n.value);
                return true;
            }else if(value.compareTo(n.value) > 0){
                search(n.left,value);
            }else {
                search(n.right,value);
            }
            return false;
        }

        public Comparable<T>  minValue(TreeNode n){
            while(n.left!=null){
                n=n.left;
            }
            return n.value;
        }

        public Comparable<T> maxValue(TreeNode n){
            while(n.right!=null){
                n=n.right;
            }
            return n.value;
        }

        public void insert(TreeNode n, T value){
            if(value.compareTo(n.value) < 0){
                if(n.left != null){
                    insert(n.left,value);
                }else{
                    n.left=new TreeNode(value);
                }
            }
            if(value.compareTo(n.value) > 0){
                if(n.right != null){
                    insert(n.right,value);
                }else{
                    n.right=new TreeNode(value);
                }
            }
        }

    }


}
