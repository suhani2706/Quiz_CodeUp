/**
 * The QuestionManager class handles the management of quiz questions, including
 * adding new questions, displaying existing questions, and retrieving specific questions.
 * It also validates user inputs to ensure the integrity of the questions and answers.
 * Created On : 9 October 2024
 */

package quiz_project;
import java.util.Scanner;

public class QuizManager {
    private static QuestionManager questionManager = new QuestionManager();
    private static Scanner scan = new Scanner(System.in);
    private static int correctAnswer = 0;
    private static int incorrectAnswer = 0;
    private static int[] userAnswers;


    /*This method starts the quiz.
        Return type : void
    */
    public static void startQuiz() {
        System.out.println(CONSTANTS.WELCOME_TO_QUIZ);
        System.out.println(CONSTANTS.PRESS_YES);
        String choice = scan.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            userAnswers = new int[questionManager.totalQuestions];
            askQuestions();
            checkAnswer();
        }
    }

    /*This method cask the questions when user starts to play
        Return type : void
    */
    private static void askQuestions() {
        for (int i = 0; i < questionManager.totalQuestions; i++) {
            System.out.println(CONSTANTS.INPUT_OPTION + (i + 1) + ": " + questionManager.questionBank[i][0][0]);
            System.out.println(CONSTANTS.OPTIONS);
            for (int j = 1; j <= 4; j++) {
                System.out.println(CONSTANTS.OPTION + j + ": " + questionManager.questionBank[i][j][0]);
            }

            boolean validInput = false;
            String answer = "";

            while (!validInput) {
                System.out.print(CONSTANTS.CORRECT_ANSWERS);
                answer = scan.nextLine();

                if (QuestionManager.isValidInt(answer) && Integer.parseInt(answer) >= 1 && Integer.parseInt(answer) <= 4) {
                    validInput = true;
                } else {
                    System.out.println(Errors.INVALID_INPUT);
                }
            }

            userAnswers[i] = Integer.parseInt(answer);
        }
    }

    /*This method checks the answer that user entered.
        Return type : void
    */
    private static void checkAnswer() {
        for (int i = 0; i < questionManager.totalQuestions; i++) {
            if (userAnswers[i] == Integer.parseInt(questionManager.questionBank[i][0][1])) {
                correctAnswer++;
            } else {
                incorrectAnswer++;
            }
        }
    }

    /*This method shows the result of the quiz.
        Return type : void
    */
    public static void result() {
        double percentage = ((double) correctAnswer / questionManager.totalQuestions) * 100;
        System.out.println(CONSTANTS.RESULT);
        System.out.println(CONSTANTS.TOTAL_CORRECT_ANSWERS + correctAnswer);
        System.out.println(CONSTANTS.TOTAL_INCORRECT_ANSWERS + incorrectAnswer);
        System.out.println(CONSTANTS.PERCENTAGE + percentage + "%");
    }

}
