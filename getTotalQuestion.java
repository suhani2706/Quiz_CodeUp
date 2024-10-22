/**
 * The getTotalQuestion class provides a method to transfer quiz questions 
 * from one question array to another. This can be useful for managing 
 * different sets of quiz questions in a quiz system.
 *
 * Method to copy quiz questions from the source array to the destination array.
 *
 * @param quizQuestion The destination array where questions will be stored.
* @param getQuestion The source array containing the quiz questions to be copied.
 * owner Ajay
 */

public class getTotalQuestion {
    public static void getQuestion(String[][][] quizQuestion, String[][][] getQuestion) {
        for (int i = 0; i < getQuestion.length; i++) {
            quizQuestion[i][0][0] = getQuestion[i][0][0];
            for (int j = 1; j <= 4; j++) {
                quizQuestion[i][j][0] = getQuestion[i][j][0]; 
            }
            quizQuestion[i][0][1] = getQuestion[i][0][1]; 
        }
    }
}
