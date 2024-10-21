/**
 * The Evaluator class is responsible for evaluating user responses, calculating the quiz score,
 * and showing the results. It tracks the number of correct and incorrect answers for a quiz.
 * owner Abhilash JOshi.
 */
public class Evaluator  {
    private int totalCorrectAnswers = 0;
    private int totalIncorrectAnswers = 0;
	private static Questions quizDatabase = new Questions();
	
	 /**
     * Evaluates the user's answers by comparing them to the correct answers in the question bank.
     * It increments the count of correct and incorrect answers based on the user's responses.
     *
     * @param userAnswers An array of user-selected answers.
     * @param questionBank A 3D array representing the question bank with correct answers.
     * @param totalQuestions The total number of questions in the quiz.
     * @return void This method does not return any value.
     */
    public void evaluateResponses(int[] userAnswers, String[][][] questionBank, int totalQuestions) {
        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswers[i] == Integer.parseInt(questionBank[i][0][1])) {
                totalCorrectAnswers++;
            } else {
                totalIncorrectAnswers++;
            }
        }
    }
	
	 /**
     * Calculates the percentage of correct answers.
     *
     * @param totalQuestions The total number of questions in the quiz.
     * @return double The percentage of correct answers.
     */
    public double calculatePercentage(int totalQuestions) {
        return ((double) totalCorrectAnswers / totalQuestions) * 100;
    }
	
	 /**
     * Displays the results of the quiz, including the number of correct and incorrect answers
     * and the calculated score percentage.
     *
     * @return void This method does not return any value.
     */
    public void Result() {
        System.out.println("Your Results:");
        System.out.println("Correct Answers: " + totalCorrectAnswers);
        System.out.println("Incorrect Answers: " + totalIncorrectAnswers);
        double percentage = calculatePercentage(quizDatabase.totalQuestions);
        System.out.println("Your Quiz Score is: " + percentage + "%");
    }
	
	/**
     * Resets the results by clearing the count of correct and incorrect answers.
     *
     * @return void This method does not return any value.
     */
    public void resetResults() {
        totalCorrectAnswers = 0;
        totalIncorrectAnswers = 0;
    }
}