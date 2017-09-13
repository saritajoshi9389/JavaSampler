import java.util.*;


public class Test {

            public int[] twoSum(int[] nums, int target) {
                int firstNumber, secondNumber = 0;
                int[] result = new int[2];
                boolean flag = false;
                for(int i = 0; i < nums.length; i++){
                    firstNumber = nums[i];
                    secondNumber  = target - firstNumber;
                    for (int j = i+1; j < nums.length; j ++){
                        if(secondNumber == nums[j]) {
                            result[0] =i;
                            result[1] = j;
                            flag = true;
                            break;
                        }
                    }
                    if (flag == true){
                        break;
                    }
                }

                return result;

            }

    public static int[] twoSumCorrect(int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++){
            int complementNumber = target - nums[i];
            if(result.containsKey(complementNumber)){
                // Look back therefore first is complement insert and second is the new element say 7 so 2, 7
                return new int[]{result.get(complementNumber), i};
            }
            result.put(nums[i], i);
        }
        throw new IllegalArgumentException("no solution");

    }

    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

//    public class Solution extends VersionControl {
//        public int firstBadVersion(int n) {
//            int left = 1;
//            int right = n;
//            while(left < right){
//                int mid = left + (right - left)/2;
//                if(isBadVersion(mid)){
//                    right = mid;
//                }
//                else {
//                    left = mid + 1;
//                }
//            }
//            return left;
//
//        }
//    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;
        ListNode curr = dummyHead;
        while (p != null || q != null){
            int x = (p != null) ? p.val: 0;
            int y = (q != null) ? q.val: 0;
            int sum = x + y + carry;
            carry = sum/10;
            curr.next = new ListNode(sum% 10);
            curr = curr.next;
            if(p != null ) p = p.next;
            if(q != null ) q = q.next;
        }
        if(carry != 0)
        {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;

    }

    public static ListNode addTwoNumbersNonReversed(ListNode l1, ListNode l2) {
        int length1 = getLength(l1);
        int length2 = getLength(l1);
        // get length
        // compare length and pad zeroes to the front
        if (length1 < length2){
            l1 = padZeros(l1, length2 - length1);
        }
        else {
            l2 = padZeros(l2, length1 - length2);
        }
        // By now both the linked list are in correct format to be added.
        // start the addition and store the result in PartialSum

        PartialSum sum = addTwoNumbersNonReversedHelper(l1, l2);
        // the above sum is the final result. If carry add the carry node to the front, if not final result is the sum in partialsum
        if(sum.carry == 0){
            return sum.sum;

        }
        else{
            ListNode result = InsertBefore(sum.sum, sum.carry);
            return result;
        }

    }

    private static PartialSum addTwoNumbersNonReversedHelper(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            PartialSum sum = new PartialSum();
            return sum;

        }
        else{
            PartialSum sum = addTwoNumbersNonReversedHelper(l1.next, l2.next); // call the recursion, back wala first then
            int val = sum.carry + l1.val + l2.val;

            // Making the resultant Linked List
            ListNode completeResult = InsertBefore(sum.sum, val % 10);
            // update the partial sum on each digit addition
            sum.sum = completeResult;
            sum.carry = val/10;
            return sum;


        }
    }

    private static ListNode padZeros(ListNode l, int countZeros) {
        ListNode head = l;
        for(int i = 0; i < countZeros; i++){
            head =  InsertBefore(head, 0);
        }
        return head;
    }

    private static ListNode InsertBefore(ListNode head, int i) {
        ListNode temp = new ListNode(i);
        if (head != null) {
            temp.next = head;
        }
        return temp;
    }

    public static int getLength(ListNode l){
        ListNode temp = l;
        int length = 0;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    public static boolean isValid(String s) {
        HashMap<Character, Character> match = new HashMap<Character, Character>();
        match.put(')','(');
        match.put('}','{');
        match.put(']','[');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i ++){
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
                continue;
            }
            if (stack.size() == 0 || match.get(s.charAt(i)) != stack.pop()) {
                return false;
            }
        }
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }

    public static List<String> letterCombinations(String digits) {
        Map<Character, String> pair = new HashMap<>();
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> preres = new ArrayList<>();
        pair.put('2', "abc");
        pair.put('3', "def");
        pair.put('4', "ghi");
        pair.put('5', "jkl");
        pair.put('6', "mno");
        pair.put('7', "pqrs");
        pair.put('8', "tuv");
        pair.put('9', "wxyz");
        if(digits.length() == 0){
            return res;
        }
        else {
            res.add("");
            for (int i = 0; i < digits.length(); i++) {
                for (String str : res) {
                    String letters = pair.get(digits.charAt(i));
                    for (int j = 0; j < letters.length(); j++)
                        preres.add(str + letters.charAt(j));
                }
                res = preres;
                preres = new ArrayList<String>();
            }
            return res;
        }
    }

    public static List<String> GenerateParentheses(int n){
        ArrayList<String> result = new ArrayList<>();
        GenerateParenthesesHelper(result, "", n , n);
        return result;
    }

    private static void GenerateParenthesesHelper(ArrayList<String> result, String s, int left, int right) {
        if (left > right) return;
        if(left == 0 && right == 0){
            result.add(s);
            return;
        }
        if(left > 0) GenerateParenthesesHelper(result, s +"(", left -1, right);
        if(right > 0) GenerateParenthesesHelper(result, s +")", left, right -1);

    }

    public static void permute(int[] arr){
        permuteHelper(arr, 0);
    }

    private static void permuteHelper(int[] arr, int index) {
        if(index >= arr.length - 1){ //If we are at the last element - nothing left to permute
            //System.out.println(Arrays.toString(arr));
            //Print the array
            System.out.print("[");
            for(int i = 0; i < arr.length - 1; i++){
                System.out.print(arr[i] + ", ");
            }
            if(arr.length > 0)
                System.out.print(arr[arr.length - 1]);
            System.out.println("]");
            return;
        }

        for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]

            //Swap the elements at indices index and i
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;

            //Recurse on the sub array arr[index+1...end]
            permuteHelper(arr, index+1);

            //Swap the elements back
            t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
        }
    }

    public static int[] plusOne(int[] digits) {
            int carry = 1;
            int[] result = new int[digits.length];
            for(int i = digits.length -1 ; i >=0 ; i--){
                int val = digits[i] + carry;
                result[i] = val%10;
                carry = val /10;

            }
            if(carry == 1){
                result = new int[digits.length + 1];
                result[0] = 1;
            }
            return result;

    }

    class MinStack {
        public  Stack<Integer> normalStack = new Stack<Integer>();
        public Stack<Integer> minStack = new Stack<Integer>();

        /** initialize your data structure here. */
        public MinStack() {
            normalStack = new Stack<>();
            minStack = new Stack<>();

        }

        public void push(int x) {
            normalStack.push(x);
            if(minStack.isEmpty() || (x <= minStack.peek())){
                minStack.push(x);
            }

        }

        public void pop() {
            if(!normalStack.isEmpty()){
                int num = normalStack.pop();
                if(!minStack.isEmpty() && num == minStack.peek())
                    minStack.pop();
            }
        }

        public int top() {
            if(normalStack.isEmpty()) return -1;
            else
                return normalStack.peek();

        }

        public int getMin() {
            if(minStack.size()== 0) return -1;
            return minStack.peek();

        }
    }

//    static int findNode(Node start, int value){
//
//    }

    public static void main(String[] args) {
        int[] input = new int[]{2, 7, 11, 15};
        System.out.println("Result of best solution" + Arrays.toString(twoSumCorrect(input, 26)));
        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l1.next = l12;
        l12.next = l13;
        l13.next = null;
        ListNode cur1 = l1;
        while (cur1!= null){
            System.out.print(cur1.val + "\t");
            cur1 = cur1.next;
        }
        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;
        l23.next = null;
        ListNode cur2 = l2;
        System.out.println("\n");
        while (cur2!= null){
            System.out.print(cur2.val + "\t");
            cur2 = cur2.next;
        }
        System.out.println("\nSum two numbers LL in reversed order \n");
        ListNode result = addTwoNumbers(l1, l2);
        ListNode cur = result;
        while (cur!= null){
            System.out.print(cur.val + "\t");
            cur = cur.next;
        }

        System.out.println("\n Sum two numbers LL in non-reversed order \n");
        ListNode result1 = addTwoNumbersNonReversed(l1, l2);
        ListNode current = result1;
        while (current!= null){
            System.out.print(current.val + "\t");
            current = current.next;
        }

//        for(int a = 0 ; a < 5 ; a++){
//            if(a%2== 0){
//                System.out.println("\n hi and value is" + a);
//                continue;
//            }
//            System.out.println("dnn know!!!");
//        }
        System.out.println("\n String validation :: " + isValid("()"));
        System.out.println("\n  string pattern list :: " + letterCombinations("22"));
        System.out.println("\n Generate parentheses list :: " + GenerateParentheses(2));
        permute(new int[]{1,2,3});
        System.out.println("\n Oneplus :: ");
        int[] out = plusOne(new int[]{1,0,0});
        System.out.println(Arrays.toString(out));
        ArrayList<ArrayList<Integer>> resultPas = getPascalTriangle(3);
        System.out.println("Pascal Triangle :: \n" + resultPas.toString());
        System.out.println("Max profit::" + maxProfit(new int[]{ 7, 6, 4,3,1}));



    }
// Pascal triangle to return a single row
    private static ArrayList<ArrayList<Integer>> getPascalTriangle(int numOfRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(numOfRows < 0) return  res;
        ArrayList<Integer> pre = new ArrayList<>();  // holds the previous row info
        pre.add(1);
        res.add(pre); // first row done
        for(int row = 2 ; row <= numOfRows; row ++){
            ArrayList<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);
            for(int j = 0; j < pre.size() -1 ; j ++)
                currentRow.add(pre.get(j) + pre.get(j+1));

            currentRow.add(1);
            res.add(currentRow);
            pre = currentRow;
        }

    return  res;
    }

// Complete pascal triangle
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows <= 0) return result;
        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        result.add(pre);
        for(int i = 1; i < numRows; i++){
            List<Integer> current = new ArrayList<>();
            current.add(1);
            for(int j = 0; j < pre.size() -1; j++){
                current.add(pre.get(j) + pre.get(j+1));
            }
            current.add(1);
            result.add(current);
            pre =current;
        }
        return result;


    }

    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxprofit = 0;
        for(int i =0 ; i < prices.length; i++) {
            if(min > prices[i]) {
                min = prices[i];
            }
            else if(maxprofit < (prices[i] - min)){
                maxprofit = prices[i] - min;
            }


        }

        return maxprofit;

    }

    public int[] twoSumSorted(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length -1;
        while (low < high){
            int sum = numbers[low] + numbers[high];
            if (sum == target){
                return (new int[]{low+1, high+1});
            }
            else if (sum < target){
                low++;
            }
            else{
                high --;
            }
        }
        return (new int[]{-1, -1});

    }

    // Primes count
    public int countPrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        if(n == 0 || n == 1)
            return 0;
        if (n ==2) return 0;
        if(n == 3) return 1;
        primes.add(2);
        primes.add(3);

        for(int i = 4; i < n ; i ++){
            boolean isPrime = true;
            for(int j = 0 ; j < primes.size(); j ++){
                if(i % primes.get(j) == 0){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime){
                primes.add(i);
            }
        }

        return primes.size();

    }
// Super algo with no time limit exceeded always start with 2
    public int countPrimesBetter(int n) {
        if(n <= 2)
            return 0;
        boolean[] primes = new boolean[n];
        for (int i =2 ; i < n; i++){
            primes[i] = true;

        }

        for(int i = 2; i <=  Math.sqrt(n-1); i ++){
            if(primes[i]){
                for(int j = i +i; j < n ; j+= i){
                    primes[j] = false;
                }
            }
        }

        int count = 0;
        for(int i =2 ; i < n ; i++){
            if(primes[i]){
                count++;
            }
        }
        return count;
    }



}
