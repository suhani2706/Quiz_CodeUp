package quiz_project;
import java.util.Scanner;

public class QuizManager {
    private static QuestionManager questionManager = new QuestionManager(); 
    private static Scanner scan = new Scanner(System.in);
    private static int correctAnswer = 0;
    private static int incorrectAnswer = 0;
    private static int[] userAnswers;  

    public static void startQuiz() {
        System.out.println("Welcome to the quiz!");
        System.out.println("Press 'YES' to start the quiz.");
        String choice = scan.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            userAnswers = new int[questionManager.totalQuestions];
            askQuestions();
            checkAnswer(); 
        } 
    }

    private static void askQuestions() {
        for (int i = 0; i < questionManager.totalQuestions; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questionManager.questionBank[i][0][0]);
            System.out.println("Options:");
            for (int j = 1; j <= 4; j++) {
                System.out.println("Option " + j + ": " + questionManager.questionBank[i][j][0]);
            }
			
			boolean validInput = false;
            String answer = "";
			
            while (!validInput) {
                System.out.print("Enter your answer (1, 2, 3, 4): ");
                answer = scan.nextLine();
				
                if (QuestionManager.isValidInt(answer) && Integer.parseInt(answer) >= 1 && Integer.parseInt(answer) <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input, please enter a number between 1 and 4.");
                }
            }
			
            userAnswers[i] = Integer.parseInt(answer);
        }
    }

    private static void checkAnswer() {
        for (int i = 0; i < questionManager.totalQuestions; i++) {
            if (userAnswers[i] == Integer.parseInt(questionManager.questionBank[i][0][1])) {
                correctAnswer++;
            } else {
                incorrectAnswer++;
            }
        }
    }

    public static void result() {
        double percentage = ((double) correctAnswer / questionManager.totalQuestions) * 100;
        System.out.println("Your Result:");
        System.out.println("Correct Answers: " + correctAnswer);
        System.out.println("Incorrect Answers: " + incorrectAnswer);
        System.out.println("Your Quiz Percentage is: " + percentage + "%");
    }

}