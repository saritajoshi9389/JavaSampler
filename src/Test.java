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
        System.out.println("First uni char :: " + firstUniqChar(""));
        System.out.println("Is ana :: " + isAnagram("acdb", "abcd"));
        System.out.println("Longest palindromic substring :: " + findLongestPalindromic("forgeeksskeegfor"));
        System.out.println("Longest palindromic substring :: " + findLongestPalindromic1("forgeeksskeegfor"));
        int[][] a = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println("Course scheduler in topological sort :: " + Arrays.toString(findOrder(4, a)));

        System.out.println("Course scheduler in topological sort  DFS:: " + Arrays.toString(findOrderDFS(4, a)));
        List<List<String>> s = new ArrayList<>();
        s = Arrays.asList(
        Arrays.asList("clean", "build"),
        Arrays.asList("metadata", "binary"),
        Arrays.asList("build", "link"),
        Arrays.asList("link", "binary"),
        Arrays.asList("clean", "metadata"),
        Arrays.asList("build", "resources"));
        System.out.println("Question intuit :: " + TopoWorkflow(s));

        List<List<String>> s1 = Arrays.asList(
                Arrays.asList("boil", "serve"),
                Arrays.asList("chop", "boil"),
                Arrays.asList("stir", "boil"),
                Arrays.asList("set table", "serve")
        );

        System.out.println("Question intuit :: " + TopoWorkflow(s1));

        System.out.println("add binary :: \n " + addBinary("11", "1"));
        System.out.println("Longest substring  :: "+ lengthOfLongestSubstring("abcaa"));
    }

    // o(n^ 2) -> both time and space
    private static String findLongestPalindromic(String str) {

        int len = str.length();
        boolean[][] table = new boolean[len][len];

        // for length
        int maxLength = 1;
        // all substring of just length 1
        for (int i = 0 ; i < len ; i++){
            table[i][i] =true;

        }
        // len 2
        int start = 0;
        for(int i = 0; i < len-1; i++){
            if(str.charAt(i) == str.charAt(i+1)){
                table[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }
        // more than 2
        // untill length   <=len
        for(int k = 3 ; k <= len ; k++){
            for(int i = 0 ; i < len -k +1; i++){

                int j  = i + k -1;
                if(str.charAt(i) == str.charAt(j) && table[i+1][j-1] == table[i][i]){
                    table[i][j] = true;
                    if(k > maxLength){
                        maxLength = k;
                        start = i;
                    }
                }


            }
        }

        return printString(str, start, start+maxLength -1);

    }
// o(n2 and no space , o(1))
    private static String findLongestPalindromic1(String str) {

        int len = str.length();
        int maxLength = 1; //max length of palindromic sub string
        int start = 0;
        int low, high;

        // alll here

       // we start from low i -1
        for(int i = 1 ; i < len ; i++){
            low = i -1;
            high = i;
            //even
            while(low >=0 && high < len && str.charAt(low) == str.charAt(high)){

                if(high - low + 1 > maxLength){
                    maxLength = high - low + 1;
                    start = low;
                }
                --low;
                ++high;

            }
            //odd
            low = i -1;
            high = i+1;

            while(low >= 0 && high < len && str.charAt(low) == str.charAt(high)){
                if(high - low + 1 > maxLength){
                    maxLength = high - low + 1;
                    start = low;
                }
            }

            --low;
            ++high;



        }

        return printString(str, start, start+maxLength -1);

    }


    private static String printString(String str, int low, int high){

        return(str.substring(low, high + 1));
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

//Yay works
    public static int firstUniqChar(String s) {
        int result = -1;
        HashMap<Character, Integer> maps = new HashMap<>();
        for(int i = 0 ; i < s.length(); i++){
            if(maps.containsKey(s.charAt(i))){
                maps.put(s.charAt(i), maps.get(s.charAt(i)) +1);
            }
            else{
                maps.put(s.charAt(i), 1);
            }
        }

        System.out.println("Hashmap" + maps.toString());

        for(int i =0 ; i < s.length(); i++){
            if(maps.get(s.charAt(i)) == 1) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
        return false;
    }
    int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
        counter[s.charAt(i) - 'a']++;
        counter[t.charAt(i) - 'a']--;
    }
        System.out.println(Arrays.toString(counter));
        for (int count : counter) {
        if (count != 0) {
            return false;
        }
    }
        return true;
}


// Questions on topological sort
// Learning Kahns algo with BFS
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        int[] inDegrees = new int[numCourses];
        buildGraph(numCourses, prerequisites, g, inDegrees);

        int[] res = new int[numCourses];
        int sortedLen = 0;
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < numCourses; ++i) {
            if (inDegrees[i] == 0) {
                q.add(i);
            }
        }
        while (q.size() > 0) {
            int curr = q.remove();
            for (int neigh: g.get(curr)) {
                inDegrees[neigh] -= 1;
                if (inDegrees[neigh] == 0) {
                    q.add(neigh);
                }
            }
            res[sortedLen] = curr;
            sortedLen += 1;
        }

        if (sortedLen != numCourses) {
            return new int[]{};
        }
        return res;
    }


    private static void buildGraph(int numCourses, int[][] prerequisites,
                            Map<Integer, Set<Integer>> g, int[] inDegrees) {
        for (int i = 0; i < numCourses; ++i) {
            g.put(i, new HashSet<Integer>());
        }
        for (int[] pre: prerequisites) {
            //System.out.println("check"+ g.get(pre[1]).add(pre[0]));
            if (g.get(pre[1]).add(pre[0])) {
                inDegrees[pre[0]] += 1;
            }
        }
        System.out.println("g"+ g);
        System.out.println("degree" +Arrays.toString(inDegrees));
    }


    // topological sort DFS

        public static int[] findOrderDFS(int numCourses, int[][] prerequisites) {
            Map<Integer, Set<Integer>> g = buildGraphDFS(numCourses, prerequisites);

            boolean[] inPath = new boolean[numCourses];


            boolean[] visited = new boolean[numCourses];

            System.out.println("visted" + Arrays.toString(visited));
            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < numCourses; ++i) {
                if (!visited[i] && !hasOrder(g, stack, inPath, visited, i)) {
                    return new int[]{};
                }
            }

            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; ++i) {
                res[i] = stack.removeFirst();
            }
            return res;
        }

        private static boolean hasOrder(Map<Integer, Set<Integer>> g,
                                 Deque<Integer> stack, boolean[] inPath, boolean[] visited, int i) {
            if (visited[i]) {
                return true;
            }
            visited[i] = true;
            inPath[i] = true;
            for (int neigh: g.get(i)) {
                if (inPath[neigh] || !hasOrder(g, stack, inPath, visited, neigh)) {
                    return false;
                }
            }
            inPath[i] = false;
            stack.addFirst(i);
            return true;
        }

        private static Map<Integer, Set<Integer>> buildGraphDFS(int numCourses, int[][] prerequisites) {
            Map<Integer, Set<Integer>> g = new HashMap<>();
            for (int i = 0; i < numCourses; ++i) {
                g.put(i, new HashSet<Integer>());
            }
            for (int[] dep: prerequisites) {
                g.get(dep[1]).add(dep[0]);
            }
            System.out.println(g);
            return g;
        }


        public static List<List <String>> TopoWorkflow(List<List<String>> s){

            Map<String, Set<String>> g = new HashMap<>();
            Map<String, Integer>  indegrees = new HashMap<>();

            for(int i = 0; i < s.size(); i ++){
                if(!indegrees.containsKey(s.get(i).get(0))){
                    indegrees.put(s.get(i).get(0), 0);
                }
                if(!indegrees.containsKey(s.get(i).get(1))){
                    indegrees.put(s.get(i).get(1), 0);
                }

            }
            System.out.println("unique words \n" +  indegrees);


            List<List<String>>  res = new ArrayList<>();
            List<String> tmp = new ArrayList<>();
            buildGraphWorkflow(s, g, indegrees);

            // BFS go with queue is must
            Queue<String> q = new ArrayDeque<>();
            for(String key: indegrees.keySet()){
                if(indegrees.get(key) == 0){
                    q.add(key);
                    tmp.add(key);
                }
            }
            res.add(tmp);
            while(!q.isEmpty()){
                String curr = q.remove();
                tmp = new ArrayList<>();
                for(String neigh: g.get(curr)){
                    indegrees.put(neigh, indegrees.get(neigh) -1);
                    if(indegrees.get(neigh) == 0){
                        q.add(neigh);
                        tmp.add(neigh);
                    }


                }
                if(tmp.size() != 0)
                    res.add(tmp);

            }

            return res;

        }

    private static void buildGraphWorkflow(List<List<String>> s, Map<String, Set<String>> g, Map<String, Integer> indegrees) {

        for(String key: indegrees.keySet()){
            if(!g.containsKey(key)){
                g.put(key, new HashSet<String>());
            }
        }
        System.out.println("g"+ g);

        for (List<String> pre: s) {
            if(g.get(pre.get(0)).add(pre.get(1))) {
                indegrees.put(pre.get(1), indegrees.get(pre.get(1)) + 1);
            }
        }
        System.out.println("\n" + g + "\n" + "indegrees" + indegrees);

    }

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i=a.length()-1;
        int j=b.length()-1;

        int carry = 0;

        while(i>=0 || j>=0){
            int sum=0;

            if(i>=0 && a.charAt(i)=='1'){
                sum++;
            }

            if(j>=0 && b.charAt(j)=='1'){
                sum++;
            }

            sum += carry;

            if(sum>=2){
                carry=1;
            }else{
                carry=0;
            }

            sb.insert(0,  (char) ((sum%2) + '0'));
            System.out.println((char) ((sum%2) + '0'));

            i--;
            j--;
        }

        if(carry==1)
            sb.insert(0, '1');

        return sb.toString();
    }


    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}
