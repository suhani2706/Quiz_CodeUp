import java.util.Scanner;

public class QuizUp {
    private static Questions quizDatabase = new Questions();
	private static EvaluationManager evaluationManager = new EvaluationManager();
	private static getTotalQuestion questionCopier = new getTotalQuestion();
    private static demoQuiz demoQuiz = new demoQuiz();
    private static Scanner inputScanner = new Scanner(System.in);
    private static int[] userAnswers;
    private static boolean isTimeUp = false;
    private static long remainingTime = 10 * 60 * 1000;


    public static void initiateQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have 10 minutes to complete the quiz.");
        System.out.println("Enter 'start' to start the quiz.");
		String userChoice = "";
		while(!userChoice.equalsIgnoreCase("Start")){
        userChoice = inputScanner.nextLine();
		}
		if (quizDatabase.totalQuestions == 0) {
			quizDatabase.questionBank = new String[demoQuiz.demoQuestion.length][][];
			for (int i = 0; i < demoQuiz.demoQuestion.length; i++) {
			quizDatabase.questionBank[i] = new String[demoQuiz.demoQuestion[i].length][];
				for (int j = 0; j < demoQuiz.demoQuestion[i].length; j++) {
				quizDatabase.questionBank[i][j] = new String[demoQuiz.demoQuestion[i][j].length];
				}
		}
		getTotalQuestion.getQuestion(quizDatabase.questionBank, demoQuiz.demoQuestion);
		quizDatabase.totalQuestions = demoQuiz.demoQuestion.length;
		}
		userAnswers = new int[quizDatabase.totalQuestions];
		startCountdownTimer();
		presentQuestions();
		evaluationManager.evaluateResponses(userAnswers, quizDatabase.questionBank, quizDatabase.totalQuestions);
		evaluationManager.showResults();
    }

    private static void startCountdownTimer() {
        Thread countdownThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (!isTimeUp && remainingTime > 0) {
                try {
                    Thread.sleep(1000);
                    remainingTime = 10 * 60 * 1000 - (System.currentTimeMillis() - startTime);
                    if (remainingTime <= 0 ) {
                        isTimeUp = true;
                        System.out.println("\nTime is up! The quiz has ended.");
                        evaluationManager.showResults();
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
        for (int i = 0; i < quizDatabase.totalQuestions; i++) {
            if (isTimeUp) break;
            System.out.println("\nQuestion " + (i + 1) + ": " + quizDatabase.questionBank[i][0][0]);
            System.out.println("Options:");
            for (int j = 1; j <= 4; j++) {
                System.out.println("Option " + j + ": " + quizDatabase.questionBank[i][j][0]);
            }

            boolean validInput = false;
            String answer = "";

            while (!validInput && !isTimeUp) {
                System.out.print("Enter your answer (1, 2, 3, 4): ");
                answer = inputScanner.nextLine();

                if (quizDatabase.isValidInt(answer) && Integer.parseInt(answer) >= 1 && Integer.parseInt(answer) <= 4) {
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
			System.out.println("submit early");
            isTimeUp = true; 
            System.out.println("\nAll questions answered. Ending quiz early.");
        }
    }

    private static void printRemainingTime() {
        long minutes = (remainingTime / 1000) / 60;
        long seconds = (remainingTime / 1000) % 60;
        System.out.println("Time remaining: " + minutes + " minutes " + seconds + " seconds");
    }
	
	public static void main(String[] args){
		initiateQuiz();
	}

    
}
