/* Main file where User will be given two options Attempt the Quiz or Create a Quiz 
*	
*/
package quiz_project;
import java.util.Scanner;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static QuestionManager questionManager = new QuestionManager();
	private static QuizManager quizManager = new QuizManager();
	private static boolean questionAvailable = false;
	
	private static String getUserInput() {
		System.out.println("Do you want to Attempt the Quiz or Create Quiz");
        System.out.println("Type 'attempt' to Attempt Quiz");
        System.out.println("Type 'create' to Create Quiz");
        System.out.println("Type 'exit' to Exit Program");

        String userInput = scanner.nextLine().toLowerCase();
        return userInput;
	}
	
	public static void main(String[] args) {
		
	     System.out.println("Welcome to Quiz Program");

	     while (true) {
	        String userInput = getUserInput();

            if (userInput.equals("create")) {
            	questionManager.populateQuestionBank();
            	questionAvailable = true;
                System.out.println("Questions created successfully.");
                
                System.out.println("Do you want to attempt the quiz now? (yes/no)");
                String attemptNow = scanner.nextLine().toLowerCase();
                if (attemptNow.equals("yes")) {
                    quizManager.startQuiz();
                    quizManager.result();
                }
            } else if (userInput.equals("attempt")) {
                if (!questionAvailable || QuestionManager.totalQuestions == 0) {
                    System.out.println("No questions are available");
                } else {
                    quizManager.startQuiz();
                    quizManager.result();
                }
            } else if (userInput.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid input- Please enter 'attempt', 'create', or 'exit' ");
            }
	        }

	        System.out.println("End");
	}
}
