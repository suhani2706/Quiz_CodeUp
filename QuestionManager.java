/**
 * The QuestionManager class handles the creation and management of quiz questions.
 * It allows users to input a specified number of questions, each with four options and a correct answer.
 *
 * Created ON : 9 October 2024
 */

package sample_code;
import java.util.Scanner;

public class QuestionManager {
	 public static int totalQuestions;
	    public static int quizQuestionsCount;
	    public static String[][][] questionBank;
	    public static String[][][] quizQuestions;
	    static Scanner scan = new Scanner(System.in);

	    /*This method checks the valid input of user.
	        Return type : boolean
	    */
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

	    /*This method creates the question bank by asking the user questions and its options.
	        Return type : void
	    */
	    public static void populateQuestionBank() {
	        System.out.print(CONSTANTS.TOTAL_QUESTIONS);
	        String noOfQuestion;
	        while (true) {
	            noOfQuestion = scan.nextLine();
	            if (!isValidInt(noOfQuestion)) {
	                System.out.println(Errors.INVALID);
	                System.out.print(CONSTANTS.TOTAL_QUESTIONS);
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
	                System.out.println(CONSTANTS.ENTER_QUESTIONS + (i + 1) + ": ");
	                question = scan.nextLine();

	                for (int m = 0; m < i; m++) {
	                    if (questionBank[m][0][0] != null && questionBank[m][0][0].equals(question)) {
	                        System.out.println(CONSTANTS.QUESTION_SAME);
	                        exists = true;
	                        break;
	                    }
	                }
	                if (question.isEmpty()) {
	                    System.out.println(Errors.INVALID_QUESTION);
	                }
	            } while (question.isEmpty() || exists);
	            questionBank[i][0][0] = question;
	            for (int j = 1; j <= 4; j++) {
	                String option;
	                boolean isUniqueOption;
	                do {
	                    isUniqueOption = true;
	                    System.out.print(CONSTANTS.ENTER_OPTION + j + ": ");
	                    option = scan.nextLine();
	                    for (int k = 1; k < j; k++) {
	                        if (questionBank[i][k][0] != null && questionBank[i][k][0].equals(option)) {
	                            System.out.println(CONSTANTS.OPTION_EXISTS);
	                            isUniqueOption = false;
	                            break;
	                        }
	                    }
	                    if (option.isEmpty()) {
	                        System.out.println(Errors.INVALID_OPTIONS);
	                    }
	                } while (!isUniqueOption || option.isEmpty());
	                questionBank[i][j][0] = option;
	            }
	            System.out.println(CONSTANTS.CORRECT_ANSWERS);
	            while (true) {
	                String answer = scan.nextLine();
	                if (!isValidInt(answer) || Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > 4) {
	                    System.out.println(Errors.INVALID_INPUT);
	                } else {
	                    questionBank[i][0][1] = answer;
	                    break;
	                }
	            }

	            do {
	                System.out.println(CONSTANTS.QUESTION_SUMMARY);
	                System.out.println(CONSTANTS.QUESTION + questionBank[i][0][0]);
	                System.out.println(CONSTANTS.OPTIONS);
	                for (int j = 1; j <= 4; j++) {
	                    System.out.println(CONSTANTS.OPTION + j + ": " + questionBank[i][j][0]);
	                }
	                System.out.println(CONSTANTS.CORRECT_ANSWER + questionBank[i][0][1]);
	                System.out.println(CONSTANTS.EDIT);
	                choice = scan.nextLine();
	                if (choice.equalsIgnoreCase("yes")) {
	                    System.out.println(CONSTANTS.RE_ENTER_QUESTION);
	                    question = scan.nextLine();
	                    questionBank[i][0][0] = question;
	                    for (int j = 1; j <= 4; j++) {
	                        System.out.print(CONSTANTS.RE_ENTER_OPTION + j + ": ");
	                        String newOption = scan.nextLine();
	                        questionBank[i][j][0] = newOption;
	                    }
	                    System.out.println(CONSTANTS.RE_ENTER_CORRECT_ANSWERS);
	                    while (true) {
	                        String newAnswer = scan.nextLine();
	                        if (!isValidInt(newAnswer) || Integer.parseInt(newAnswer) < 1 || Integer.parseInt(newAnswer) > 4) {
	                            System.out.println(Errors.INVALID_INPUT);
	                        } else {
	                            questionBank[i][0][1] = newAnswer;
	                            break;
	                        }
	                    }
	                }
	            } while (choice.equalsIgnoreCase("yes"));
	        }
	    }

	    /*This method show questions on console
	        Return type : void
	    */
	    private static void showQuestions() {
	        for (int i = 0; i < totalQuestions; i++) {
	            System.out.println(CONSTANTS.INPUT_OPTION + (i + 1) + ": " + questionBank[i][0][0]);
	            System.out.println(CONSTANTS.OPTIONS);
	            for (int j = 1; j <= 4; j++) {
	                System.out.println(CONSTANTS.OPTION+ j + ": " + questionBank[i][j][0]);
	            }
	            System.out.println(CONSTANTS.CORRECT_ANSWER + questionBank[i][0][1]);
	        }
	    }

	    /*This method selects the questions
	        Return type : void
	    */
	    private static void selectQuestion() {
	        System.out.print(CONSTANTS.NO_OF_QUESTIONS);
	        String noOfQuestion;
	        int questionChoice;
	        while (true) {
	            noOfQuestion = scan.nextLine();
	            if (!isValidInt(noOfQuestion)) {
	                System.out.println(Errors.INVALID);
	                System.out.print(CONSTANTS.NO_OF_QUESTIONS);
	            } else {
	                break;
	            }
	        }
	        quizQuestionsCount = Integer.parseInt(noOfQuestion);
	        boolean[] selectedQuestions = new boolean[totalQuestions];
	        System.out.println(CONSTANTS.LIST_OF_QUESTIONS);
	        showQuestions();
	        quizQuestions = new String[quizQuestionsCount][5][2];
	        for (int i = 0; i < quizQuestionsCount; i++) {
	            while (true) {
	                System.out.print(CONSTANTS.SELECT_QUESTION + (i + 1) + CONSTANTS.ENTER_QUESTION_NUMBER);
	                String questionChoiceInput = scan.nextLine();
	                if (!isValidInt(questionChoiceInput)) {
	                    System.out.println(Errors.INVALID_QUESTION_NO);
	                    continue;
	                }
	                questionChoice = Integer.parseInt(questionChoiceInput) - 1;
	                if (questionChoice < 0 || questionChoice >= totalQuestions) {
	                    System.out.println(Errors.INVALID_QUES + totalQuestions);
	                } else if (selectedQuestions[questionChoice]) {
	                    System.out.println(CONSTANTS.QUESTION_SELECTED);
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
	            System.out.println(CONSTANTS.INPUT_OPTION + (i + 1) + ": " + quizQuestions[i][0][0]);
	            System.out.println(CONSTANTS.OPTIONS);
	            for (int j = 1; j <= 4; j++) {
	                System.out.println(CONSTANTS.OPTION + j + ": " + quizQuestions[i][j][0]);
	            }
	            System.out.println(CONSTANTS.CORRECT_ANSWER + quizQuestions[i][0][1]);
	        }
	    }
}
