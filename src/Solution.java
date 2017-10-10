import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static int solution(int[] array, int n) {
        if (array.length != n)
            throw new RuntimeException("Wrong input!");

        // Amount of each unique item
        Map<Integer, Integer> numberFrequencies = new HashMap<>();
        for (int i : array) {
            numberFrequencies.put(i, numberFrequencies.getOrDefault(i, 0) + 1);
        }

        // Total amount of unique items
        int uniquesCount = numberFrequencies.size();

        int minLengthRecord = n;
        int startIndex = 0;
        int endIndex = 0;
        int frequenciesInWindow = 0;
        int[] window = new int[n];

        // Nested while loop is limited by start, which is always less than n
        while (endIndex < n) {
            int endVal = array[endIndex];

            if (window[endVal] == 0) {
                // increment for every new unique item in window
                frequenciesInWindow++;
            }
            window[endVal]++;

            if (frequenciesInWindow == uniquesCount) {
                int startVal = array[startIndex];
                while (window[startVal] > 1) {
                    window[startVal]--;
                    startIndex++;
                    startVal = array[startIndex];
                }
                System.out.println("start" +  startIndex + "end" + endIndex);
                minLengthRecord = Math.min(minLengthRecord, endIndex - startIndex + 1);
                System.out.println("min val" + minLengthRecord);
            }
            endIndex++;
        }
        System.out.printf("Starting index : %d\n", startIndex);
        System.out.println("hi" + minLengthRecord);
        return minLengthRecord;
    }

    static int subArr(int[] arr) {
        Map<Integer, Integer> q = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            if (!q.containsKey(value)) {
                q.put(value, 1);
            } else {
                q.put(value, q.get(value) + 1);
            }
        }
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            left = i;
            int cur = arr[i];
            int count = q.get(cur);
            if (count > 1) {
                q.put(cur, count - 1);
            } else break;
        }
        int right = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            right = i;
            int cur = arr[i];
            int count = q.get(cur);
            if (count > 1) {
                q.put(cur, count - 1);
            } else break;
        }
        return right - left + 1;
    }

    private static int[] generateIntArray(int size) {
        if (size > 100000) throw new UnsupportedOperationException("Array size is too big!");
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }

        return array;
    }

    public static void main(String[] args) {
        for (int i = 19; i < 20; i++) {
                int[] array = new int[]{7, 3, 7, 3, 1,3,4,1};
                //int[] array = generateIntArray(i);
                StringBuilder sb = new StringBuilder();

                for (int a : array) {
                    sb.append(a).append(" ");
                }

            System.out.printf("Array : length : %d; %s\n", array.length, sb.toString());
            System.out.printf("Solution sol : %s\n", solution(array, array.length));
            //System.out.printf("Solution sub : %s\n", subArr(array));

        }
    }

}