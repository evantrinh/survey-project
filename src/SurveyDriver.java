public class SurveyDriver {
    /* takes in args, starts application */
    public static void main(String[] args) {
        System.out.println("Welcome to the survey system.");
        
        SurveyMenuInterface menuInterface = new SurveyMenuInterface();
        menuInterface.displayMainMenu();
    }
}
