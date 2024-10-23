package Participant;

public class AttemptQuiz {
    static Constants constant = new Constants();
	static Error error = new Error();
    private  ShowResult result = new ShowResult();
    private  int[] userAnswers;
    
    public void initiateQuiz() {
        if (Questions.totalQuestions == 0 || Questions.questionBank == null) {
            Questions.questionBank = demoQuiz.demoQuestion;  
            Questions.totalQuestions = demoQuiz.demoQuestion.length;
        } else{
            userAnswers = new int[Questions.totalQuestions];
            presentQuestions();
            result.evaluateResponses(userAnswers, Questions.questionBank, Questions.totalQuestions);
            result.Result();
        }
    }    

    private void presentQuestions() {
        for (int i = 0; i < Questions.totalQuestions; i++) {
            System.out.println(constant.QUES + (i + 1) + ": " + Questions.questionBank[i][0][0]);
            System.out.println(constant.OPTIONS);
            for (int j = 1; j <= 4; j++) {
                System.out.println(constant.OPTIONS + j + ": " + Questions.questionBank[i][j][0]);
            }

            boolean validInput = false;
            String answer = "";
          
            System.out.print(constant.USER_ANSWER);
            answer = inputScanner.nextLine();

            if (Questions.isValidInt(answer) && Integer.parseInt(answer) >= 1 && Integer.parseInt(answer) <= 4) {
                validInput = true;
            } else {
                System.out.println(error.INVALID_CHOICE);
            }   
        }
    }
}
