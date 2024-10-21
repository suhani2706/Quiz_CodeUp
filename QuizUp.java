/**
 * The QuizUp class handles the main flow of the quiz application.
 * It initiates the quiz, starts a countdown timer, presents the questions, and evaluates the user's responses.
 * The class uses demo questions as a fallback if no custom questions are available and provides a time-limited quiz experience.
 * owner Abhilash Joshi.
 */

import java.util.Scanner;

public class QuizUp {
    private static Evaluator evaluation = new Evaluator();
    private static Scanner inputScanner = new Scanner(System.in);
    private static int[] userAnswers;
    private static QuizTimer quizTimer; 

/**
 * Initiates the quiz application by prompting the user to start the quiz,
 * checking if any custom questions are available, and using demo questions if not.
 * The method starts a 10-minute timer and presents the quiz questions to the user.
 * After the quiz is completed or time is up, it evaluates and shows the user's results.
 *
 * @return void This method does not return any value.
 */
    public static void initiateQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have 10 minutes to complete the quiz.");
        System.out.println("Enter 'start' to start the quiz.");
        String userChoice = "";

        while (!userChoice.equalsIgnoreCase("start")) {
            userChoice = inputScanner.nextLine();
        }

        if (Questions.totalQuestions == 0 || Questions.questionBank == null) {
            // Use default questions if no questions are available
            System.out.println("No custom questions available. Using demo questions...");
            Questions.questionBank = demoQuiz.demoQuestion;  
            Questions.totalQuestions = demoQuiz.demoQuestion.length;
        }

        userAnswers = new int[Questions.totalQuestions];
        quizTimer = new QuizTimer(10 * 60 * 1000); 
        quizTimer.startTimer(); 
        presentQuestions();
        evaluation.evaluateResponses(userAnswers, Questions.questionBank, Questions.totalQuestions);
        evaluation.Result();
    }
	
 /**
 * Presents each question to the user one by one and prompts for an answer.
 * Validates user input to ensure it's a number between 1 and 4.
 * Stops presenting questions if the timer runs out.
 * Updates the user's answers and prints the remaining time after each answer.
 *
 * @return void This method does not return any value.
 */

    private static void presentQuestions() {
        for (int i = 0; i < Questions.totalQuestions; i++) {
            if (quizTimer.isTimeUp()) break; 

            System.out.println("\nQuestion " + (i + 1) + ": " + Questions.questionBank[i][0][0]);
            System.out.println("Options:");
            for (int j = 1; j <= 4; j++) {
                System.out.println("Option " + j + ": " + Questions.questionBank[i][j][0]);
            }

            boolean validInput = false;
            String answer = "";
            while (!validInput && !quizTimer.isTimeUp()) {
                System.out.print("Enter your answer (1, 2, 3, 4): ");
                answer = inputScanner.nextLine();

                if (Questions.isValidInt(answer) && Integer.parseInt(answer) >= 1 && Integer.parseInt(answer) <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input, please enter a number between 1 and 4.");
                }
            }

            if (!quizTimer.isTimeUp()) {
                userAnswers[i] = Integer.parseInt(answer);
                printTime();
            }
        }

        if (!quizTimer.isTimeUp()) {
            System.out.println("Submit early? All questions answered. Ending quiz early.");
        }
    }

/**
 * Retrieves and prints the remaining time of the quiz in minutes and seconds.
 * The method formats the remaining time and outputs it to the console.
 *
 * @return void This method does not return any value.
 */
    private static void printTime() {
        long remainingTime = quizTimer.getRemainingTime();
        long minutes = (remainingTime / 1000) / 60;
        long seconds = (remainingTime / 1000) % 60;
        System.out.println("Time remaining: " + minutes + " minutes " + seconds + " seconds");
    }

    public static void main(String[] args) {
        initiateQuiz();
    }
}