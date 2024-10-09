import java.util.Scanner;

public class Participation {
    private QuestionBank questionBank;
    private int score;
    private int wrongAnswers;
    private long startTime;

    public Participation(QuestionBank questionBank) {
        this.questionBank = questionBank;
        this.score = 0;
        this.wrongAnswers = 0;
        this.startTime = System.currentTimeMillis();
    }

    // ...

    public void displayQuestion(Question question) {
        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + question.getOptions()[i]);
        }
    }

    public int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the correct option (or 'q' to quit): ");
        String input = scanner.next();
        if (input.equalsIgnoreCase("q")) {
            // Quit the quiz
            displayScore();
            System.exit(0);
        }
        return Integer.parseInt(input) - 1; // subtract 1 to get the 0-based index
    }

    public void updateScore(int correctOption, int userInput) {
        if (correctOption == userInput) {
            this.score++;
        } else {
            this.wrongAnswers++;
        }
    }

    public void startQuiz() {
        for (int i = 0; i < this.questionBank.getNumberOfQuestions(); i++) {
            Question question = this.questionBank.getQuestion(i);
            this.displayQuestion(question);
            int userInput = this.getUserInput();
            this.updateScore(question.getCorrectOption(), userInput);
        }
        this.displayScore();
    }

    public void displayScore() {
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime - this.startTime) / 1000.0;
        double percentage = ((double) this.score / this.questionBank.getNumberOfQuestions()) * 100;
        System.out.println("Your score is " + this.score + " out of " + this.questionBank.getNumberOfQuestions());
        System.out.println("Your percentage is " + String.format("%.2f", percentage) + "%");
        System.out.println("You took " + String.format("%.2f", timeTaken) + " seconds to complete the quiz.");
        System.out.println("You got " + this.wrongAnswers + " questions wrong.");
    }
}