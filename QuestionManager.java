import java.util.Scanner;

public class QuestionManager {
    static int totalQuestions;
    static String[][][] questionBank;
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

    public static void questionBank() {
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
            boolean isUniqueQuestion;
            do {
                isUniqueQuestion = true;
                System.out.println("Enter question " + (i + 1) + ": ");
                question = scan.nextLine();
                System.out.println("Your entered Question is: \n" + question);
                System.out.println("To edit question press 'YES' else any key to continue:");
                choice = scan.nextLine();
                    for (int m = 0; m < i; m++) {
                        if (questionBank[m][0][0].equals(question)) {
                            System.out.println("Question already exists. Please enter a unique question.");
                            isUniqueQuestion = false;
                            break;
                        }
                    }
            } while (!isUniqueQuestion || choice.equalsIgnoreCase("yes"));
            questionBank[i][0][0] = question;
            for (int j = 1; j <= 4; j++) {
                String option;
                boolean isUniqueOption;
                do {
                    isUniqueOption = true;
                    System.out.print("Enter Option " + j + ": ");
                    option = scan.nextLine();
                    System.out.println("Your entered option is: \n" + option);
                    System.out.println("To edit option press 'YES' else any key to continue:");
                    choice = scan.nextLine();
                    for (int k = 1; k < j; k++) {
                        if (questionBank[i][k][0].equals(option)) {
                            System.out.println("Option already exists. Please enter a unique option.");
                            isUniqueOption = false;
                            break;
                        }
                    }
                } while (!isUniqueOption || choice.equalsIgnoreCase("yes"));
                questionBank[i][j][0] = option;
            }
            System.out.println("Enter the correct answer (1, 2, 3, 4): ");
            while (true) {
                String answer = scan.nextLine();
                if (!isValidInt(answer) || Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > 4) {
                    System.out.println("Invalid input, please enter a number between 1 and 4.");
                } else {
                    questionBank[i][0][1] = answer;
                    break;
                }
            }
        }
    }

    public static void showQuestions() {
        for (int i = 0; i < totalQuestions; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questionBank[i][0][0]);
            System.out.println("Options:");
            for (int j = 1; j <= 4; j++) {
                System.out.println("Option " + j + ": " + questionBank[i][j][0]);
            }
            System.out.println("Correct Answer: " + questionBank[i][0][1]);
        }
    }

    public static void main(String[] args) {
        questionBank();
        showQuestions();
    }
}
