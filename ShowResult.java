package Participant;

public class ShowResult {
    static Constants constant = new Constants();
	static Error error = new Error();

    private int[][] participantsData;
    private String[][] participantNames;
    private int totalCorrectAnswers = 0;
    private int totalIncorrectAnswers = 0;
	private static Questions quizDatabase = new Questions();

    public ShowResult(int numberOfParticipants) {
        participantsData = new int[numberOfParticipants][2];
        participantNames = new String[numberOfParticipants][1];
    }

    public void evaluateResponses(int[] userAnswers, String[][][] questionBank, int totalQuestions, int participantIndex) {
        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswers[i] == Integer.parseInt(questionBank[i][0][1])) {
                totalCorrectAnswers++;
            } else {
                totalIncorrectAnswers++;
            }
        }
        participantsData[participantIndex][0] = totalCorrectAnswers;
        participantsData[participantIndex][1] = totalIncorrectAnswers;
    }

    public float calculatePercentage(int totalQuestions) {
        return ((float) totalCorrectAnswers / totalQuestions) * 100;
    }

    public void Result(int numberOfParticipants) {
        System.out.println(constant.RESULTS);
        for (int i = 0; i < numberOfParticipants; i++) {
            System.out.println(constant.PARTICIPANT_NAME + participantNames[i][0]);
            System.out.println(constant.TOTAL_QUESTION + (participantsData[i][0] + participantsData[i][1]));
            System.out.println(constant.CORRECT_ANSWERS + participantsData[i][0]);
            System.out.println(constant.INCORRECT_ANSWERS + participantsData[i][1]);
            float percentage = calculatePercentage(quizDatabase.totalQuestions);
            System.out.println(constant.QUIZ_SCORE + percentage + "%");
            System.out.println("");
        }
        System.out.println(constant.TOPPERS);
        for (int i = 0; i < numberOfParticipants; i++) {
            int max = 0;
            int index = 0;
            for (int j = 0; j < numberOfParticipants; j++) {
                if (participantsData[j][0] > max) {
                    max = participantsData[j][0];
                    index = j;
                }
            }
            System.out.println(constant.PARTICIPANT_NAME + participantNames[index][0]);
            System.out.println(constant.CORRECT_ANSWERS + participantsData[index][0]);
            participantsData[index][0] = -1;
        }
    }
}

