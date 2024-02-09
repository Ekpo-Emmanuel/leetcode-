import java.util.*;
public class Main {

    // 1143. Longest Common Subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        if(len1 > len2) {
            return longestCommonSubsequence(text2, text1);
        }

        int[] dp = new int[len1 + 1];
        int prev;

        for(int j = 1; j <= len2; j++) {
            prev = dp[0];

            for(int i = 1; i <= len1; i++) {
                int temp = dp[i];

                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i] = prev + 1;
                } else {
                    dp[i] = Math.max(dp[i], dp[i-1]);
                }

                prev = temp;
            }
        }

        return dp[len1];
    }


    //2. Add Two Numbers
    static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode ptr = result;
        int carry = 0;

        while(l1!=null || l2!=null) {
            int sum = carry;

            if(l1!=null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if(l2!=null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            sum = sum % 10;

            ptr.next = new ListNode(sum);
            ptr = ptr.next;
        }

        if(carry == 1) {
            ptr.next = new ListNode(1);
            ptr = ptr.next;
        }

        return result.next;
    }

    //415. Add Strings
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();

        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        int carry = 0;

        while(p1 >= 0 || p2 >= 0) {
            int sum = carry;

            if(p1 >= 0) {
                sum += num1.charAt(p1) - '0';
                p1--;
            }

            if(p2 >= 0) {
                sum += num2.charAt(p2) - '0';
                p2--;
            }

            carry = sum / 10;
            sum = sum % 10;

            result.append(sum);
        }

        if (carry != 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    //989. Add to Array-Form of Integer
    // Solution 1. I converted K to array
    public List<Integer> addToArrayForm(int[] num, int k) {
        int p1 = num.length - 1;
        int[] num2 = numberToArray(k);
        int p2 = num2.length - 1;

        int carry = 0;

        ArrayList<Integer> result = new ArrayList<>();

        while(p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num[p1] - 0: 0;
            int x2 = p2 >= 0 ? num2[p2] - 0: 0;

            int sum = x1 + x2 + carry;

            carry = sum / 10;
            sum = sum % 10;

            result.add(sum);
            p1--;
            p2--;
        }

        if(carry != 0) {
            result.add(carry);
        }

        return reverse(result);
    }

    public static int[] numberToArray(int number) {
        String numberString = String.valueOf(number);

        int[] digitArray = new int[numberString.length()];

        for (int i = 0; i < numberString.length(); i++) {
            digitArray[i] = Character.getNumericValue(numberString.charAt(i));
        }

        return digitArray;
    }

    public static List reverse(ArrayList<Integer> list) {
        int size = list.size();
        int lastIndex = size - 1;

        for (int i = 0; i < size / 2; i++) {
            int temp = list.get(i);
            list.set(i, list.get(lastIndex - i));
            list.set(lastIndex - i, temp);
        }

        return list;
    }

    //solution 2
    public List<Integer> addToArrayForm1(int[] num, int k) {
        int p1 = num.length - 1;
        int carry = 0;
        ArrayList<Integer> result = new ArrayList<>();

        while(p1 >= 0 || k > 0 || carry > 0) {
            int x1 = p1 >= 0 ? num[p1] : 0;
            int x2 = k % 10;

            int sum = x1 + x2 + carry;
            result.add(sum % 10);

            carry = sum / 10;

            p1--;
            k /= 10;
        }

        Collections.reverse(result);
        return result;
    }

    //    26. Remove Duplicates from Sorted Array
    //    Solution 1
    public int removeDuplicates(int[] nums) {
         int length = nums.length;
         ArrayList<Integer> result = new ArrayList<>();
         result.add(nums[0]);

         if(nums.length <= 1)
             return length;

         int count = 1;

         for(int i = 1; i < length; i++) {
             if(nums[i] != nums[i-1]) {
                 result.add(nums[i]);
                 count++;
             }
         }

         for (int i = 0; i < count; i++) {
             nums[i] = result.get(i);
         }

         return count;
     }
    //solution2
    public int removeDuplicates2(int[] nums) {
        if(nums.length == 0) return 0;

        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]){
                nums[j++] = nums[i];
            }
        }

        return j;
    }

    //    2460. Apply Operations to an Array
    public int[] applyOperations(int[] nums) {
        if(nums.length <= 1) {
            return nums;
        }

        for (int i = 0; i < nums.length -1; i++) {
            if(nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i+1] = 0;
                i++;
            }
        }

        //move 0's to the end
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }

        // Fill remaining slots with zeros
        while (i < nums.length) {
            nums[i++] = 0;
        }
        return nums;
    }

    //    283. Move Zeroes
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i =0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        while(j < nums.length) {
            nums[j++] = 0;
        }
    }
    
    //    80. Remove Duplicates from Sorted Array II
    public int removeDuplicates1(int[] nums) {
        int i = 1;
        int j = 1;

        int count = 1;
        while (i < nums.length) {
            if(nums[i] == nums[i-1]) {
                count++;
            } else {
                count = 1;
            }

            if(count <= 2) {
                nums[j++] = nums[i];
            }

            i++;
        }

        return j;

    }
    //    solution 2
    public int removeDuplicates3(int[] nums) {
    int n = nums.length;
    if (n <= 1) {return n;}

    int j = 2;
    for(int i = 2; i < n; i++) {
        if (nums[i] != nums[j-1] || nums[i] != nums[j-2]) {
            nums[j++] = nums[i];
        }
    }

    return j;
}

    //189. Rotate Array
    public void rotate(int[] nums, int k) {
        if (nums.length <=1 ) return;
        k %= nums.length;
        reverse(nums, 0, nums.length -1);
        reverse(nums, 0, k -1);
        reverse(nums, k, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    //    53. Maximum Subarray
    public int maxSubArray(int[] nums) {
        int maxEnd = nums[0];
        int maxSoFar = nums[0];

        for(int i = 1; i < nums.length; i++) {
            maxEnd = Math.max(nums[i], maxEnd + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEnd);
        }

        return maxSoFar;
    }
}

