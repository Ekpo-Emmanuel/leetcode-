// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        
    }

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
}