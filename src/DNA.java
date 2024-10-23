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
//    private static final int A = 1;
//    private static final int C = 2;
//    private static final int G = 3;
//    private static final int T = 4;
    private static final int R = 4;
    private static final int Q = 506683;


    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        String testSequence = "CAGATAGATAGATAGATAGAT",
                testSTR = "AGAT";
//        return takeFour(testSequence, testSTR);
        return takeFour(sequence, STR);
//        return takeTwo(sequence, STR);

    }

    // Solution with hashing
    public static int takeFour(String sequence, String STR) {
        // Fill map with
        VALUES['A'] = 0;
        VALUES['a'] = 0;
        VALUES['C'] = 1;
        VALUES['c'] = 1;
        VALUES['G'] = 2;
        VALUES['g'] = 2;
        VALUES['T'] = 3;
        VALUES['t'] = 3;

        int streak = 0;
        int maxStreak = 0;
        int index = STR.length();

        long radixPow = 1;
        for (int i = 1; i <= index - 1; i++) {
            radixPow = (R * radixPow) % Q;
        }
//        System.out.println(radixPow);
//        System.out.println(STR);
        int seqLen = sequence.length();
        long seqHash = hash(sequence, index);
        long patHash = hash(STR, index);

        for (int i = index; i < seqLen; i++) {
            seqHash = (seqHash + Q - (radixPow * getNumber(sequence.charAt(i - index)) % Q)) % Q;
            seqHash = (seqHash * R + getNumber(sequence.charAt(i))) % Q;
            if (patHash == seqHash) {
                // Increment the streak
                streak++;
                // Jump to the next character after the streak
                i += STR.length() - 1;
                // Continue calculating the hash one by one to catch up to the index
                for (int j = 1; j < STR.length(); j++) {
                    seqHash = (seqHash + Q - (radixPow * getNumber(sequence.charAt(i + j - index)) % Q)) % Q;
                    seqHash = (seqHash * R + getNumber(sequence.charAt(i + j))) % Q;
                }
            } else if (streak > 0) {
                // The streak is now broken, so reset the streak and update the maximum streak
                maxStreak = Math.max(streak, maxStreak);
                streak = 0;
            }
        }

        return maxStreak;
    }

    public static long hash(String in, int length) {
        long out = 0;
        for (int i = 0; i < length; i++) {
//            out = (long) (out + (Math.pow(R, in.length() - i-1) * getNumber(in.charAt(i))+1) % Q);
            out = (R * out + getNumber(in.charAt(i))) % Q;

//            out += getNumber(in.charAt(i)) * Math.pow(10, in.length()-1-i);
        }
        return out;
    }

    public static int getNumber(char c) {
//        if (VALUES[c] == 0 && c != 'A') {
//            System.out.println("Oh dear, we have a: " + c);
//        }
        return VALUES[c];
    }

    // works pretty well (for the most part)
    // Character by character approach
    public static int characterByCharacter(String sequence, String STR) {
        int maxStreak = 0;
        int streak = 0;
        for (int i = 0; i < sequence.length(); i++) {
            for (int j = 0; j < STR.length(); j++) {
                if (i+j >= sequence.length() || sequence.charAt(i+j) != STR.charAt(j)) {
                    maxStreak = Math.max(maxStreak, streak);
                    streak = 0;
                    break;
                } else if (j == STR.length()-1) {
                    streak++;
                    i+=STR.length()-1;
                }
            }
        }
        return maxStreak;
    }
}
