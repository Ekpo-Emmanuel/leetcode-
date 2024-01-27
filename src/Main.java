// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
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


//     2. Add Two Numbers
static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    ListNode result = new ListNode(0);
    ListNode ptr = result;
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
}