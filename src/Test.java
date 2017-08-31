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

    }
}
