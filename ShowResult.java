package Participant;

public class ShowResult {
    private int[][] participantsData;
    private String[][] participantNames;
    private int totalCorrectAnswers = 0;
    private int totalIncorrectAnswers = 0;
    private static Questions quizDatabase = new Questions();
    private ParticipantScores participantScores;
    private Constants constant = new Constants();

    public ShowResult(int numberOfParticipants) {
        participantsData = new int[numberOfParticipants][2];
        participantNames = new String[numberOfParticipants][1];
        participantScores = new ParticipantScores(numberOfParticipants);
    }

    public void setParticipantName(int index, String name) {
        participantNames[index][0] = name;
    }

    public void evaluateResponses(int[] userAnswers, String[][][] questionBank, int totalQuestions, int participantIndex) {
        totalCorrectAnswers = 0; 
        totalIncorrectAnswers = 0; 

        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswers[i] == Integer.parseInt(questionBank[i][0][1])) {
                totalCorrectAnswers++;
            } else {
                totalIncorrectAnswers++;
            }
        }
        participantsData[participantIndex][0] = totalCorrectAnswers;
        participantsData[participantIndex][1] = totalIncorrectAnswers;
        participantScores.addParticipant(participantNames[participantIndex][0], totalCorrectAnswers);
    }

    public float calculatePercentage(int correctAnswers, int totalQuestions) {
        return ((float) correctAnswers / totalQuestions) * 100;
    }

    public void Result(int numberOfParticipants) {
        System.out.println(constant.RESULTS);
        for (int i = 0; i < numberOfParticipants; i++) {
            System.out.println(constant.PARTICIPANT_NAME + participantNames[i][0]);
            System.out.println(constant.TOTAL_QUESTION + (participantsData[i][0] + participantsData[i][1]));
            System.out.println(constant.CORRECT_ANSWERS + participantsData[i][0]);
            System.out.println(constant.INCORRECT_ANSWERS + participantsData[i][1]);
            float percentage = calculatePercentage(participantsData[i][0], quizDatabase.totalQuestions);
            System.out.println(constant.QUIZ_SCORE + percentage + "%");
            System.out.println("");
        }

        System.out.println(constant.TOPPERS);
        participantScores.displayTopParticipants(); 
    }
}
