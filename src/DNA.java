/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Sohum Berry
 *</p>
 */

public class DNA {
    private static int[] VALUES = new int[256];
    private static final int A = 0;
    private static final int C = 1;
    private static final int G = 2;
    private static final int T = 3;
    private static final int R = 4;
    private static final int Q = 506683;

    public static int STRCount(String sequence, String STR) {
        return takeFour(sequence, STR);
    }

    // Solution with hashing
    public static int takeFour(String sequence, String STR) {
        // Fill map with appropriate variable and their values
        VALUES['A'] = A;
        VALUES['a'] = A;
        VALUES['C'] = C;
        VALUES['c'] = C;
        VALUES['G'] = G;
        VALUES['g'] = G;
        VALUES['T'] = T;
        VALUES['t'] = T;

        int streak = 0;
        int maxStreak = 0;
        int index = STR.length();

        // Calculate the radix to the power of the length of the STR-1
        long radixPow = 1;
        for (int i = 1; i <= index - 1; i++) {
            // Use a for loop instead of Math.pow() to ensure no overflow
            radixPow = (R * radixPow) % Q;
        }
        // Create initial hashes of the first STR length characters of the sequence and the STR
        long seqHash = hash(sequence, index);
        long patHash = hash(STR, index);

        for (int i = index; i < sequence.length(); i++) {
            // Drop the first character of the hash
            seqHash = (seqHash + Q - (radixPow * getNumber(sequence.charAt(i - index)) % Q)) % Q;
            // Add the next character to the hash
            seqHash = (seqHash * R + getNumber(sequence.charAt(i))) % Q;
            if (patHash == seqHash) {
                // Increment the streak
                streak++;
                // Continue calculating the hash one by one to catch up to the index
                for (int j = 1; j < STR.length(); j++) {
                    seqHash = (seqHash + Q - (radixPow * getNumber(sequence.charAt(i + j - index)) % Q)) % Q;
                    seqHash = (seqHash * R + getNumber(sequence.charAt(i + j))) % Q;
                }
                // Jump to the next character after the streak
                i += STR.length() - 1;
            } else if (streak > 0) {
                // The streak is now broken, so reset the streak and update the maximum streak
                maxStreak = Math.max(streak, maxStreak);
                streak = 0;
            }
        }
        // Return the greater value between the maxStreak and the actual streak+1.
        // This would happen if the streak was never broken and maxStreak never gets assigned.
        // Add one to account for the final STR that wasn't counted towards the streak.
        return Math.max(maxStreak, streak+1);
    }

    public static long hash(String in, int length) {
        // Create a hash using Horner's method
        long out = 0;
        for (int i = 0; i < length; i++) {
            out = (R * out + getNumber(in.charAt(i))) % Q;
        }
        return out;
    }

    public static int getNumber(char c) {
        // Use a map to turn a character into its appropriate value in radix of 4
        return VALUES[c];
    }

    // Character by character approach, works pretty well for the most part
    public static int characterByCharacter(String sequence, String STR) {
        int maxStreak = 0;
        int streak = 0;
        // Iterate through every character
        for (int i = 0; i < sequence.length(); i++) {
            for (int j = 0; j < STR.length(); j++) {
                // If the characters of the sequence and STR don't match, break to continue to next window
                if (i+j >= sequence.length() || sequence.charAt(i+j) != STR.charAt(j)) {
                    maxStreak = Math.max(maxStreak, streak);
                    streak = 0;
                    break;
                } else if (j == STR.length()-1) {
                    // If the STR is on the last character, and they have all matched, increment the streak
                    // Also jump the i value to go check for future potential streaks
                    streak++;
                    i+=STR.length()-1;
                }
            }
        }
        return maxStreak;
    }
}
