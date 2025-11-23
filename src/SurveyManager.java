public class SurveyManager {
    private Survey currentSurvey;
    
    /* default constructor */
    public SurveyManager() {
    }
    
    /* takes in name, creates and returns survey */
    public Survey createSurvey(String name) {
        currentSurvey = new Survey(name);
        // System.out.println("Survey '" + name + "' created successfully.");
        return currentSurvey;
    }
    
    /* takes in survey, modifies survey */
    public void modifySurvey(Survey survey) {
        if (survey == null) {
            System.out.println("No survey to modify.");
            return;
        }
        survey.modifySurvey();
    }
    
    /* takes in survey, displays survey */
    public void displaySurvey(Survey survey) {
        if (survey == null) {
            System.out.println("No survey to display.");
            return;
        }
        survey.displaySurvey();
    }
    
    /* returns current survey */
    public Survey getCurrentSurvey() {
        return currentSurvey;
    }
    
    /* takes in survey, sets as current survey */
    public void setSurvey(Survey survey) {
        this.currentSurvey = survey;
    }
}
