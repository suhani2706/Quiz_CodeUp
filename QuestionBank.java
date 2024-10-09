import java.util.Scanner;

public class QuestionBank {
    public static int totalQuestions;
    public static int quizQuestionsCount;
    public static String[][][] questionBank;
    public static String[][][] quizQuestions;
    static Scanner scan = new Scanner(System.in);

    public static boolean isValidInt(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void populateQuestionBank() {
        System.out.print("Enter the total number of questions: ");
        String noOfQuestion;
        while (true) {
            noOfQuestion = scan.nextLine();
            if (!isValidInt(noOfQuestion)) {
                System.out.println("Invalid input");
                System.out.print("Enter the total number of questions: ");
            } else {
                break;
            }
        }
        totalQuestions = Integer.parseInt(noOfQuestion);
        questionBank = new String[totalQuestions][5][2];
        for (int i = 0; i < totalQuestions; i++) {
            String question;
            String choice;
            boolean exists;
            do {
                exists = false;
                System.out.println("Enter question " + (i + 1) + ": ");
                question = scan.nextLine();

                for (int m = 0; m < i; m++) {
                    if (questionBank[m][0][0] != null && questionBank[m][0][0].equals(question)) {
                        System.out.println("Question already exists. Please enter a unique question.");
                        exists = true;
                        break;
                    }
                }
                if (question.isEmpty()) {
                    System.out.println("Invalid input! Enter question again.");
                }
            } while (question.isEmpty() || exists);
            questionBank[i][0][0] = question;
            for (int j = 1; j <= 4; j++) {
                String option;
                boolean isUniqueOption;
                do {
                    isUniqueOption = true;
                    System.out.print("Enter Option " + j + ": ");
                    option = scan.nextLine();
                    for (int k = 1; k < j; k++) {
                        if (questionBank[i][k][0] != null && questionBank[i][k][0].equals(option)) {
                            System.out.println("Option already exists. Please enter a unique option.");
                            isUniqueOption = false;
                            break;
                        }
                    }
                    if (option.isEmpty()) {
                        System.out.println("Invalid input! Enter option again.");
                    }
                } while (!isUniqueOption || option.isEmpty());
                questionBank[i][j][0] = option;
            }
            System.out.println("Enter the correct answer option (1, 2, 3, 4): ");
            while (true) {
                String answer = scan.nextLine();
                if (!isValidInt(answer) || Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > 4) {
                    System.out.println("Invalid input, please enter a number between 1 and 4.");
                } else {
                    questionBank[i][0][1] = answer;
                    break;
                }
            }

            do {
                System.out.println("\nQuestion Summary:");
                System.out.println("Question: " + questionBank[i][0][0]);
                System.out.println("Options:");
                for (int j = 1; j <= 4; j++) {
                    System.out.println("Option " + j + ": " + questionBank[i][j][0]);
                }
                System.out.println("Correct Answer: " + questionBank[i][0][1]);
                System.out.println("Do you want to edit the question, options, or answer? (YES/NO): ");
                choice = scan.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    System.out.println("Re-enter question: ");
                    question = scan.nextLine();
                    questionBank[i][0][0] = question;
                    for (int j = 1; j <= 4; j++) {
                        System.out.print("Re-enter Option " + j + ": ");
                        String newOption = scan.nextLine();
                        questionBank[i][j][0] = newOption;
                    }
                    System.out.println("Re-enter the correct answer (1, 2, 3, 4): ");
                    while (true) {
                        String newAnswer = scan.nextLine();
                        if (!isValidInt(newAnswer) || Integer.parseInt(newAnswer) < 1 || Integer.parseInt(newAnswer) > 4) {
                            System.out.println("Invalid input, please enter a number between 1 and 4.");
                        } else {
                            questionBank[i][0][1] = newAnswer;
                            break;
                        }
                    }
                }
            } while (choice.equalsIgnoreCase("yes"));
        }
    }

    private static void showQuestions() {
        for (int i = 0; i < totalQuestions; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questionBank[i][0][0]);
            System.out.println("Options:");
            for (int j = 1; j <= 4; j++) {
                System.out.println("Option " + j + ": " + questionBank[i][j][0]);
            }
            System.out.println("Correct Answer: " + questionBank[i][0][1]);
        }
    }


    private static void selectQuestion() {
        System.out.print("Enter the number of questions for quiz: ");
        String noOfQuestion;
        int questionChoice;
        while (true) {
            noOfQuestion = scan.nextLine();
            if (!isValidInt(noOfQuestion)) {
                System.out.println("Invalid input");
                System.out.print("Enter the number of questions for quiz: ");
            } else {
                break;
            }
        }
        quizQuestionsCount = Integer.parseInt(noOfQuestion);
        boolean[] selectedQuestions = new boolean[totalQuestions];
        System.out.println("List of questions is: ");
        showQuestions();
        quizQuestions = new String[quizQuestionsCount][5][2];
        for (int i = 0; i < quizQuestionsCount; i++) {
            while (true) {
                System.out.print("Select Question " + (i + 1) + " for Quiz. Enter (Question Number): ");
                String questionChoiceInput = scan.nextLine();
                if (!isValidInt(questionChoiceInput)) {
                    System.out.println("Invalid input! Enter a valid question number.");
                    continue;
                }
                questionChoice = Integer.parseInt(questionChoiceInput) - 1;
                if (questionChoice < 0 || questionChoice >= totalQuestions) {
                    System.out.println("Invalid question number! Choose a number between 1 and " + totalQuestions);
                } else if (selectedQuestions[questionChoice]) {
                    System.out.println("This question has already been selected. Please choose another question.");
                } else {
                    selectedQuestions[questionChoice] = true;
                    break;
                }
            }
            quizQuestions[i][0][0] = questionBank[questionChoice][0][0];
            for (int j = 1; j <= 4; j++) {
                quizQuestions[i][j][0] = questionBank[questionChoice][j][0];
            }
            quizQuestions[i][0][1] = questionBank[questionChoice][0][1];
        }
    }

    private static void showSelectedQuestions() {
        for (int i = 0; i < quizQuestionsCount; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + quizQuestions[i][0][0]);
            System.out.println("Options:");
            for (int j = 1; j <= 4; j++) {
                System.out.println("Option " + j + ": " + quizQuestions[i][j][0]);
            }
            System.out.println("Correct Answer: " + quizQuestions[i][0][1]);
        }
    }
}