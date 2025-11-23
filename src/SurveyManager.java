public class SurveyManager {
    private Survey currentSurvey;
    
    /* default constructor */
    public SurveyManager() {
    }
    
    /* takes in name, creates and returns survey */
    public Survey createSurvey(String name) {
        currentSurvey = new Survey(name);
        return currentSurvey;
    }
    
    /* returns current survey */
    public Survey getCurrentSurvey() {
        return currentSurvey;
    }
    
    /* takes in survey, sets as current survey */
    public void setCurrentSurvey(Survey survey) {
        this.currentSurvey = survey;
    }
}
