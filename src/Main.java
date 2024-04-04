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

    // 9. Palindrome Number
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int originalNum = x;
        int reversedNum = 0;

        while (x > 0) {
            int digit = x % 10;
            reversedNum = (reversedNum * 10) + digit;
            x /= 10;
        }

        return originalNum == reversedNum;
    }

    //3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);

            if(map.containsKey(current)) {
                start = Math.max(start, map.get(current) + 1);
            }
            max = Math.max(max, end - start + 1);
            map.put(current, end);
        }

        return max;
    }
    
    // 771. Jewels and Stones
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();

        for(char stone : stones.toCharArray()) {
            map.put(stone, map.getOrDefault(stone, 0) + 1);
        }

        for(char jewel : jewels.toCharArray()) {
            if(map.containsKey(jewel)) {
                count += map.get(jewel);
            }
        }

        return count;
    }


    // 543. Diameter of Binary Tree
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
     }
    class Solution {

        //Performing a DFS to traverse the list. Either Inorder, Preorder or Postorder
        public void inorder(TreeNode node) {
            if (node == null) return;
            else {
                inorder(node.left);
                System.out.print(node.val);
                inorder(node.right);
            }
        }

        //Find the maxDepth of the tree (height of tree)
        public int height (TreeNode node) {
            if (node == null) return 0;
            else {
                int lheight = height(node.left);
                int rheight = height(node.right);

                return Math.max(lheight, rheight) + 1;
            }
        }
        //main Function
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) return 0;

            //keeps track of the current left and right node
            int lheight = height(root.left);
            int rheight = height(root.right);

            int currDiameter = lheight + rheight;
            int lDiameter = diameterOfBinaryTree(root.left);
            int rDiameter = diameterOfBinaryTree(root.right);

            return Math.max(currDiameter, Math.max(lDiameter, rDiameter));
        }

        // 242. Valid Anagram
        public boolean isAnagram(String s, String t) {
            if(s.length() != t.length()) return false;
            HashMap<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if(!map.containsKey(c) || map.get(c) == 0) return false;
                map.put(c, map.get(c) -1 );
            }

            return true;
        }

        // 1929. Concatenation of Array
        public int[] getConcatenation(int[] nums) {
            int n = nums.length;
            int[] arr = new int[n*2];

            for(int i = 0; i < n; i++) {
                arr[i] = nums[i];
                arr[i+n] = nums[i];
            }

            return arr;
        }

        // 19. Remove Nth Node From End of List
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0,head);
            ListNode left = dummy;
            ListNode right = head;

            //moving right by n+1
            for(int i =0; i < n; i++) {
                if(right == null) return null;
                right = right.next;
            }

            //moving both pointers
            while(n > 0 && right != null) {
                left = left.next;
                right = right.next;
            }

            left.next = left.next.next;
            return dummy.next;

        }
        // 2095. Delete the Middle Node of a Linked List
        public ListNode deleteMiddle(ListNode head) {
            //getting the total items in list
            int count = 0;
            ListNode temp = head;

            while(temp != null) {
                count++;
                temp = temp.next;
            }

            //middle
            int middle = count / 2;

            //pointers
            ListNode dummy = new ListNode(0, head);
            ListNode left = dummy;
            ListNode right = head;

            //moving right by n+1
            for(int i = 0; i < middle; i++) {
                if(right == null) return null;
                left = left.next;
                right = right.next;
            }

            left.next = left.next.next;
            return dummy.next;
        }
    }
}

