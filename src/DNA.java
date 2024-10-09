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
