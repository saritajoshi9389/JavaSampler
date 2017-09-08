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
}
