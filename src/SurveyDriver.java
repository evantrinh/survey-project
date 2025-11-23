public class SurveyDriver {
    /* takes in args, starts application */
    public static void main(String[] args) {
        System.out.println("Welcome to the survey system.");
        
        SurveyManager manager = new SurveyManager();
        SurveyMenuInterface menuInterface = new SurveyMenuInterface(manager);
        menuInterface.displayMainMenu();
    }
}
