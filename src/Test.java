import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
            System.out.println(cur1.val);
            cur1 = cur1.next;
        }
        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;
        l23.next = null;
        ListNode cur2 = l2;
        while (cur2!= null){
            System.out.println(cur2.val);
            cur2 = cur2.next;
        }
        System.out.println("Sum two numbers LL \n");
        ListNode result = addTwoNumbers(l1, l2);
        ListNode cur = result;
        while (cur!= null){
            System.out.println(cur.val);
            cur = cur.next;
        }

    }
}
