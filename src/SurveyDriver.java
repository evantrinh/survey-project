public class SurveyDriver {
    private static SurveyManager manager;
    private static SurveyMenuInterface menuInterface;
    
    /* takes in args, starts application */
    public static void main(String[] args) {
        System.out.println("Welcome to the survey system.");
        
        initialize();
        run();
    }
    
    /* initializes system components */
    private static void initialize() {
        manager = new SurveyManager();
        menuInterface = new SurveyMenuInterface(manager);
        
        // System.out.println("Survey system initialized successfully.");
    }
    
    /* runs main application loop */
    private static void run() {
        menuInterface.displayMainMenu();
    }
}
