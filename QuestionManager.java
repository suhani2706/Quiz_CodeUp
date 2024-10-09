package quiz_project;
import java.util.Scanner;

public class QuestionManager {
    public static int totalQuestions;
    public static String[][][] questionBank;
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

   public static void populateQuestionBank() {
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
            do {
                System.out.println("Enter question " + (i + 1) + ": ");
                question = scan.nextLine();
                boolean exists = false;
                for (int m = 0; m < i; m++) {
                    if (questionBank[m][0][0] != null && questionBank[m][0][0].equals(question)) {
                        System.out.println("Question already exists. Please enter a unique question.");
                        exists = true;
                        break;
                    }
                }
                if (exists) continue;
                if (question.isEmpty()) {
                    System.out.println("Invalid input! Enter question again.");
                }
            } while (question.isEmpty());

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
}