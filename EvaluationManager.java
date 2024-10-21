public class EvaluationManager {
    private int totalCorrectAnswers = 0;
    private int totalIncorrectAnswers = 0;
	private static Questions quizDatabase = new Questions();

    public void evaluateResponses(int[] userAnswers, String[][][] questionBank, int totalQuestions) {
        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswers[i] == Integer.parseInt(questionBank[i][0][1])) {
                totalCorrectAnswers++;
            } else {
                totalIncorrectAnswers++;
            }
        }
    }

    
    public double calculatePercentage(int totalQuestions) {
        return ((double) totalCorrectAnswers / totalQuestions) * 100;
    }

    public void showResults() {
        System.out.println("Your Results:");
        System.out.println("Correct Answers: " + totalCorrectAnswers);
        System.out.println("Incorrect Answers: " + totalIncorrectAnswers);
        double percentage = calculatePercentage(quizDatabase.totalQuestions);
        System.out.println("Your Quiz Score is: " + percentage + "%");
    }
}
