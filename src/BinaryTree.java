import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by saritajoshi on 9/8/17.
 */
public class BinaryTree {

    NewNode root;


    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        NewNode root = new NewNode(1);
        root.left = new NewNode(2);
        root.right = new NewNode(3);
        root.left.left = new NewNode(4);
        root.left.right = new NewNode(5);
        root.right.left = new NewNode(6);
        boolean result = bt.isHeap(root);
        if(result){
            System.out.println("Min Heap!!");
        }
        else{
            System.out.println("Nope!");
        }
        int arr[] = new int[]{11, 2, 3, 4, 5, 6};
        boolean result1 = bt.isHeap(arr, 0);
        if(result1) {
            System.out.println("Min Heap!!");
        }
        else{
            System.out.println("Nope!");
        }

        BinaryTree bst = new BinaryTree();
        NewNode n1 = new NewNode(-2147483648);
        n1.left = new NewNode(-2147483648);
        System.out.println(bst.isValidBST(n1));

    }

    public boolean isValidBST(NewNode root) {

        return isValidBSTHelper(root, Double.NEGATIVE_INFINITY, Double.MAX_VALUE);

    }

    public boolean isValidBSTHelper(NewNode root, double min, double max){
        if (root == null) return true;
        if(root.data < min || root.data > max) return false;
        else
            return (isValidBSTHelper(root.left, min, root.data -1) && isValidBSTHelper(root.right, root.data + 1, max));
    }

    private boolean isHeap(int[] arr, int index) {
        int length = arr.length;
        for(int i = 0; i <= length/2; i++){
            if((2*index + 1) < length){
                if(arr[index] > arr[2*index + 1])
                    return false;
            }
            if((2*index + 2) < length){
                if(arr[index] > arr[2*index + 2])
                    return false;
            }

        }
        return true;
    }

    private boolean isHeap(NewNode root) {
        if(root == null) return true;
        else{
            int count_all = get_number_nodes(root);
            if(isComplete(root, 0, count_all) && isHeapUtil(root)){
                return true;
            }
            return false;
        }

    }

    private boolean isHeapUtil(NewNode root) {
        if(root.left == null && root.right == null) return true;
        if(root.right == null){
            return (root.data <= root.left.data);
        }
        else{
            if(root.data <= root.left.data && root.data <= root.right.data){
                return isHeapUtil(root.left) && isHeapUtil(root.right);
            }
            else
                return false;
        }
    }

    private boolean isComplete(NewNode root, int i, int count_all) {
        if(root == null) return true;
        if(i >= count_all) return false;
        return (isComplete(root.left, 2*i +1, count_all) && isComplete(root.right, 2*i+ 2, count_all));
    }

    private int get_number_nodes(NewNode root) {
        if(root == null) return 0;
        else
            return (1 + get_number_nodes(root.left) + get_number_nodes(root.right));
    }

    /*
    Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     */
    public List<List<Integer>> levelOrder(NewNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>(); // this is output
        if(root == null) return  result; // base case

        Queue<NewNode> current = new LinkedList<>();  // current node
        Queue <NewNode> next = new LinkedList<>();   // track next level
        List<Integer> nodelist = new ArrayList<Integer>(); // list of node.val

        current.add(root); // start iteration

        while(!current.isEmpty()){
            NewNode curr = current.remove(); // BFS

            if(curr.left != null) next.add(curr.left); // search left
            if(curr.right != null) next.add(curr.right); // search right
            nodelist.add(curr.data); // add the node to list of integers

            if(current.isEmpty()){  // all elements in the current is visited and empty list
                current = next;  // next is the new current
                next = new LinkedList<>();  // next should be blank now
                result.add(nodelist); // add the list od nodes to final result
                nodelist = new ArrayList<>(); // empty the list of nodes for next level
            }


        }

        return result;

    }
    /*
    Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     */
    public List<Integer> rightSideView(NewNode root) {
        List<Integer> result = new ArrayList<>();
        if( root == null) return result;
        Queue<NewNode> q = new LinkedList<>();
        q.add(root);
        while(q.size() >0){
            int size = q.size();
            for(int i = 0 ; i < size; i++){
                NewNode curr = q.remove();
                if(i == 0){
                    result.add(curr.data);
                }

                if(curr.right != null){
                    q.add(curr.right);
                }
                if(curr.left != null){
                    q.add(curr.left);
                }

            }


        }
        return result;
    }
    /*
    LCA in a BST
     */
    public NewNode lowestCommonAncestor(NewNode root, NewNode p, NewNode q) {
        NewNode temp = root;
        if(temp.data > p.data && temp.data < q.data){ return temp;} //either this
        else if(temp.data > p.data && temp.data > q.data) {return lowestCommonAncestor(temp.left, p, q);} //or
        else if(temp.data < p.data && temp.data < q.data) {return lowestCommonAncestor(temp.right, p, q);} //or

        return root;  // LCA is always the root node so return that and thatz y created a temp for traversing
    }
    /*
       LCA in a Binary Tree ///////////// No comparison of data simple check left and right
       Check this result in debugger
        */
    public NewNode lowestCommonAncestorBT(NewNode root, NewNode p, NewNode q) {
        if(root == null) return null; // root empty -> stop

        if(root == p || root == q) return root; // only one node, return the node stop

        NewNode leftOne = lowestCommonAncestor(root.left, p, q);

        NewNode rightOne = lowestCommonAncestor(root.right, p, q);

        if(leftOne  != null && rightOne != null) return root; // both non-null
        else if(leftOne == null && rightOne == null) return null; // both null
        else{
            return (leftOne == null? rightOne : leftOne); // one null, just move in other direction
        }

    }
}
