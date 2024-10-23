package Participant;

public class ParticipantScores {
    private String[][] participantData; 
    private int participantCount;

    public ParticipantScores(int maxParticipants) {
        participantData = new String[maxParticipants][2]; 
        participantCount = 0;
    }

    public void addParticipant(String name, int score) {
        if (participantCount < participantData.length) {
            participantData[participantCount][0] = name;
            participantData[participantCount][1] = String.valueOf(score);
            participantCount++;
        }
    }

    public void displayTopParticipants() {
        for (int i = 0; i < participantCount - 1; i++) {
            for (int j = 0; j < participantCount - 1 - i; j++) {
                if (Integer.parseInt(participantData[j][1]) < Integer.parseInt(participantData[j + 1][1])) {
                    String[] temp = participantData[j];
                    participantData[j] = participantData[j + 1];
                    participantData[j + 1] = temp;
                }
            }
        }

        System.out.println("Top 5 Participants:");
        for (int i = 0; i < Math.min(5, participantCount); i++) {
            String name = participantData[i][0];
            int score = Integer.parseInt(participantData[i][1]);
            System.out.print(name + ": ");
            for (int j = 0; j < score; j++) {
                System.out.print("*");
            }
            System.out.println(" (" + score + ")");
        }
    }
}
