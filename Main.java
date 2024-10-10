/**
 *The Main class is responsible for managing the flow of the quiz application.
 *It interacts with the user to either create a quiz or attempt one.
 *It uses QuestionManager to handle the creation of quiz questions and QuizManager to manage the quiz process (starting the quiz, showing results).
 *It also handles user input and provides a simple command-line interface for the quiz program.
 * Created on : 9 October 2024
 */
package sample_code;
import java.util.Scanner;

public class Main {
	  private static Scanner scanner = new Scanner(System.in);
	    private static QuestionManager questionManager = new QuestionManager();
	    private static QuizUp quizManager = new QuizUp();
	    private static boolean questionAvailable = false;

	    public static String getUserInput() {
	        System.out.println(CONSTANTS.WELCOME);
	        System.out.println(CONSTANTS.ATTEMPT);
	        System.out.println(CONSTANTS.CREATE);
	        System.out.println(CONSTANTS.EXIT);

	        String userInput = scanner.nextLine().toLowerCase();
	        return userInput;
	    }

	    public static void main(String[] args) {

	        System.out.println(CONSTANTS.QUIZ_PROGRAM);

	        while (true) {
	            String userInput = getUserInput();

	            if (userInput.equals("create")) {
	                questionManager.populateQuestionBank();
	                questionAvailable = true;
	                System.out.println(CONSTANTS.CREATE_QUESTION);

	                System.out.println(CONSTANTS.YES_OR_NO);
	                String attemptNow = scanner.nextLine().toLowerCase();
	                if (attemptNow.equals("yes")) {
	                    quizManager.initiateQuiz();
	                    quizManager.results();
	                }
	            } else if (userInput.equals("attempt")) {
	                if (!questionAvailable || QuestionManager.totalQuestions == 0) {
	                    System.out.println(CONSTANTS.NO_QUESTION);
	                } else {
	                    quizManager.initiateQuiz();
	                    quizManager.results();
	                }
	            } else if (userInput.equals("exit")) {
	                break;
	            } else {
	                System.out.println(Errors.INVALID_USER_INPUT);
	            }
	        }

	        System.out.println("End");
	    }
}
