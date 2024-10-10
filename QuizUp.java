/**
 * The QuizUp class handles the management of quiz questions, including
 * adding new questions, displaying existing questions, and retrieving specific questions.
 * It also validates user inputs to ensure the integrity of the questions and answers.
 * Name - Abhilash Joshi
 * Created On : 9 October 2024
 */
package sample_code;
import java.util.Scanner;

public class QuizUp {
    private static QuestionManager questionManager = new QuestionManager();
    private static Scanner inputScanner = new Scanner(System.in);
    private static int totalCorrectAnswers = 0;
    private static int totalIncorrectAnswers = 0;
    private static int[] userAnswers;
    private static boolean isTimeUp = false;
    private static long remainingTime = 10 * 60 * 1000; // 10 minutes in milliseconds

    public static void initiateQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have 10 minutes to complete the quiz.");
        System.out.println("Press 'YES' to start the quiz.");
        String userChoice = inputScanner.nextLine();
        if (userChoice.equalsIgnoreCase("yes")) {
            userAnswers = new int[questionManager.totalQuestions];
            startCountdownTimer();
            presentQuestions();
            evaluateResponses();
            results();
        }
    }

    private static void startCountdownTimer() {
        Thread countdownThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (!isTimeUp && remainingTime > 0) {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                    remainingTime = 10 * 60 * 1000 - (System.currentTimeMillis() - startTime);
                    if (remainingTime <= 0) {
                        isTimeUp = true;
                        System.out.println("\nTime is up! The quiz has ended.");
                        results();
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Countdown timer interrupted.");
                }
            }
        });
        countdownThread.start();
    }

    private static void presentQuestions() {
        for (int i = 0; i < questionManager.totalQuestions; i++) {
            if (isTimeUp) break;
            System.out.println("\nQuestion " + (i + 1) + ": " + questionManager.questionBank[i][0][0]);
            System.out.println("Options:");
            for (int j = 1; j <= 4; j++) {
                System.out.println("Option " + j + ": " + questionManager.questionBank[i][j][0]);
            }

            boolean validInput = false;
            String answer = "";

            while (!validInput && !isTimeUp) {
                System.out.print("Enter your answer (1, 2, 3, 4): ");
                answer = inputScanner.nextLine();

                if (QuestionManager.isValidInt(answer) && Integer.parseInt(answer) >= 1 && Integer.parseInt(answer) <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input, please enter a number between 1 and 4.");
                }
            }

            if (!isTimeUp) {
                userAnswers[i] = Integer.parseInt(answer);
                printRemainingTime();
            }
        }
        
       
        if (!isTimeUp) {
            isTimeUp = true;
            System.out.println("\nAll questions answered. Ending quiz early.");
        }
    }

    private static void printRemainingTime() {
        long minutes = (remainingTime / 1000) / 60;
        long seconds = (remainingTime / 1000) % 60;
        System.out.println("Time remaining: " + minutes + " minutes " + seconds + " seconds");
    }

    private static void evaluateResponses() {
        for (int i = 0; i < questionManager.totalQuestions; i++) {
            if (userAnswers[i] == Integer.parseInt(questionManager.questionBank[i][0][1])) {
                totalCorrectAnswers++;
            } else {
                totalIncorrectAnswers++;
            }
        }
    }

    public static void results() {
        double percentage = ((double) totalCorrectAnswers / questionManager.totalQuestions) * 100;
        System.out.println("Your Results:");
        System.out.println("Correct Answers: " + totalCorrectAnswers);
        System.out.println("Incorrect Answers: " + totalIncorrectAnswers);
        System.out.println("Your Quiz Score is: " + percentage + "%");
    }


}
