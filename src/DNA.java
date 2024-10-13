/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [YOUR NAME HERE]
 *</p>
 */

public class DNA {
    private static final int A = 1;
    private static final int C = 2;
    private static final int G = 3;
    private static final int T = 4;


    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
//        String testSequence = "CAGATAGATAGATAGATAGAT",
//                testSTR = "AGAT";
//        return takeTwo(testSequence, testSTR);
        return takeTwo(sequence, STR);



//        return 0;
    }

    public static int takeFour(String sequence, String STR) {

        int hashSTR = hash(STR);


        return hashSTR;
    }

//    public static int index(String s, String find) {
//        s.indexOf()
//    }

    public static int hash(String in) {
        int out = 0;
        for (int i = 0; i < in.length(); i++) {
            out += getNumber(in.charAt(i)) * Math.pow(10, in.length()-1-i);
        }
        System.out.println(in);
        System.out.println(out);
        return out;
    }

    public static int getNumber(char c) {
        if (c == 'A')
            return A;
        if (c == 'C')
            return C;
        if (c == 'G')
            return G;
        if (c == 'T')
            return T;
        return 0;
    }


    // good thinking thing but abandoning it
    public static int takeThree(String sequence, String STR) {
        int maxStreak = sequence.length() / STR.length();
        String totalRepeats = "";
        for (int i = 0; i < maxStreak; i++) {
            totalRepeats += STR;
        }
        // binary search to find number of streaks that are maximized
        // wow this is jank
        // how to make dupe of strings
        return takeThreeRecurse(sequence, totalRepeats, maxStreak/2, STR.length());
    }

    public static int takeThreeRecurse(String sequence, String maxSTR, int numRepeats, int lenSTR) {
        // can't figure out base case
        //        if () {
//            return numRepeats;
//        }
        if (!sequence.contains(maxSTR.substring(maxSTR.length()-numRepeats*lenSTR))) {
            // means substring is too long, split in half
            return takeThreeRecurse(sequence, maxSTR, numRepeats/2, lenSTR);
        } else {
            // substring is not long enough, extend by half
            return takeThreeRecurse(sequence, maxSTR, numRepeats+(numRepeats/2), lenSTR);
        }
    }

    // unfinished and untested
    // Character by character approach
    public static int takeTwo(String sequence, String STR) {
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




    // tested and abandoned
    // indexOf approach
    public static int takeOne(String sequence, String STR) {
        int index = 0;
        String checkString = STR;
        int streak = 0;
        int maxStreak = streak;
        while (true) {
            index = sequence.indexOf(checkString);
            if (index == -1)
                break;
            sequence = sequence.substring(index+checkString.length());
            streak++;
            if (index+checkString.length()+1 >= sequence.length()) {
                maxStreak = Math.max(streak, maxStreak);
                break;
            }
//            System.out.println(checkString.charAt(0));
//            System.out.println(sequence.charAt(index+checkString.length()+1));
            if (sequence.charAt(0) != checkString.charAt(0)) {
                maxStreak = Math.max(streak, maxStreak);
                for (int i = 1; i < streak; i++) {
                    checkString += checkString;
                }
                streak = 0;
            }
        }
        return maxStreak;
    }
}
