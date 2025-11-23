public class OutputHandler {
    
    /* default constructor */
    public OutputHandler() {
    }
    
    /* takes in message, displays message */
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    /* takes in error, displays error message */
    public void displayError(String error) {
        System.err.println("ERROR: " + error);
    }
    
    /* takes in survey, displays survey */
    public void displaySurvey(Survey survey) {
        if (survey == null) {
            displayError("No survey to display.");
            return;
        }
        
        System.out.println("\nSurvey: " + survey.getSurveyName());
        System.out.println("Number of questions: " + survey.getNumQuestions());
        System.out.println();
        
        Question[] questions = survey.getAllQuestions();
        if (questions.length == 0) {
            System.out.println("No questions in this survey.");
        } else {
            for (Question question : questions) {
                displayQuestion(question);
                System.out.println();
            }
        }
    }
    
    /* takes in question, displays question */
    public void displayQuestion(Question question) {
        if (question == null) {
            displayError("No question to display.");
            return;
        }
        
        question.displayQuestion();
    }
    
    /* takes in response, displays response */
    public void displayResponse(SurveyResponse response) {
        if (response == null) {
            displayError("No response to display.");
            return;
        }
        
        response.displayResponse();
    }
}
