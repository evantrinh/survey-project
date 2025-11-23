public class GradingService {
    
    /* default constructor */
    public GradingService() {
    }
    
    /* takes in test and response, returns grade report */
    public GradeReport gradeTest(Test test, SurveyResponse response) {
        GradeReport report = new GradeReport();
        
        int numGradable = countGradableQuestions(test);
        int numCorrect = countCorrectAnswers(test, response);
        int totalQuestions = test.getNumQuestions();
        int numEssays = totalQuestions - numGradable;
        
        report.setNumGradable(numGradable);
        report.setNumCorrect(numCorrect);
        report.setNumEssayQuestions(numEssays);
        
        double gradablePoints = (100.0 * numGradable) / totalQuestions;
        report.setGradablePoints(gradablePoints);
        
        double score = calculateScore(test, response);
        report.setScore(score);
        
        return report;
    }
    
    /* takes in test and response, calculates and returns score */
    private double calculateScore(Test test, SurveyResponse response) {
        int totalQuestions = test.getNumQuestions();
        int numGradable = countGradableQuestions(test);
        int numCorrect = countCorrectAnswers(test, response);
        
        if (numGradable == 0) {
            return 0.0;
        }
        
        double pointsPerQuestion = 100.0 / totalQuestions;
        double score = numCorrect * pointsPerQuestion;
        
        return score;
    }
    
    /* takes in test, counts and returns gradable questions */
    private int countGradableQuestions(Test test) {
        int count = 0;
        Question[] questions = test.getAllQuestions();
        
        for (Question question : questions) {
            if (!(question instanceof EssayQuestion)) {
                count++;
            }
        }
        
        return count;
    }
    
    /* takes in test and response, counts and returns correct answers */
    private int countCorrectAnswers(Test test, SurveyResponse response) {
        int count = 0;
        Answer[] correctAnswers = test.getAllCorrectAnswers();
        Answer[] userAnswers = response.getAllAnswers();
        
        int minLength = Math.min(correctAnswers.length, userAnswers.length);
        
        for (int i = 0; i < minLength; i++) {
            if (correctAnswers[i] != null && userAnswers[i] != null) {
                if (userAnswers[i].compare(correctAnswers[i])) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
