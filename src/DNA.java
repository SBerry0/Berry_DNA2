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

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        return takeOne(sequence, STR);



//        return 0;
    }

    public static int takeFour(String sequence, String STR) {

    }

    public static int hash(String in) {

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
            char c = sequence.charAt(i);
            for (int j = 0; j < STR.length(); j++) {
                if (sequence.charAt(i+j) != STR.charAt(j)) {
                    maxStreak = Math.max(maxStreak, streak);
                    streak = 0;
                    break;
                } else if (j == STR.length()-1) {
                    streak++;
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
