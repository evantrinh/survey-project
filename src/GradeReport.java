public class GradeReport {
    private double score;
    private double totalPoints;
    private double gradablePoints;
    private int numEssayQuestions;
    private int numCorrect;
    private int numGradable;
    
    /* default constructor */
    public GradeReport() {
        this.totalPoints = 100.0;
    }
    
    /* returns score */
    public double getScore() {
        return score;
    }
    
    /* takes in score, sets score */
    public void setScore(double score) {
        this.score = score;
    }
    
    /* returns total points */
    public double getTotalPoints() {
        return totalPoints;
    }
    
    /* takes in points, sets total points */
    public void setTotalPoints(double points) {
        this.totalPoints = points;
    }
    
    /* returns gradable points */
    public double getGradablePoints() {
        return gradablePoints;
    }
    
    /* takes in points, sets gradable points */
    public void setGradablePoints(double points) {
        this.gradablePoints = points;
    }
    
    /* returns number of essay questions */
    public int getNumEssayQuestions() {
        return numEssayQuestions;
    }
    
    /* takes in num, sets number of essay questions */
    public void setNumEssayQuestions(int num) {
        this.numEssayQuestions = num;
    }
    
    /* returns number correct */
    public int getNumCorrect() {
        return numCorrect;
    }
    
    /* takes in num, sets number correct */
    public void setNumCorrect(int num) {
        this.numCorrect = num;
    }
    
    /* returns number gradable */
    public int getNumGradable() {
        return numGradable;
    }
    
    /* takes in num, sets number gradable */
    public void setNumGradable(int num) {
        this.numGradable = num;
    }
}
