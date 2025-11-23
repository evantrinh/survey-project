import java.util.*;

public class TabulationService {
    
    /* default constructor */
    public TabulationService() {
    }
    
    /* takes in survey, returns tabulation results */
    public Map<String, Object> tabulateSurvey(Survey survey) {
        Map<String, Object> results = new LinkedHashMap<>();
        
        Question[] questions = survey.getAllQuestions();
        SurveyResponse[] responses = survey.getResponses();
        
        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            List<Answer> answersForQuestion = new ArrayList<>();
            
            for (SurveyResponse response : responses) {
                Answer answer = response.getAnswer(i);
                if (answer != null) {
                    answersForQuestion.add(answer);
                }
            }
            
            Answer[] answersArray = answersForQuestion.toArray(new Answer[0]);
            Object tabulationResult = null;
            
            if (question instanceof TrueFalseQuestion) {
                tabulationResult = tabulateTrueFalse(question, answersArray);
            } else if (question instanceof MultipleChoiceQuestion) {
                tabulationResult = tabulateMultipleChoice((MultipleChoiceQuestion) question, answersArray);
            } else if (question instanceof ShortAnswerQuestion) {
                tabulationResult = tabulateShortAnswer((ShortAnswerQuestion) question, answersArray);
            } else if (question instanceof EssayQuestion) {
                tabulationResult = tabulateEssay((EssayQuestion) question, answersArray);
            } else if (question instanceof ValidDateQuestion) {
                tabulationResult = tabulateDate((ValidDateQuestion) question, answersArray);
            } else if (question instanceof MatchingQuestion) {
                tabulationResult = tabulateMatching((MatchingQuestion) question, answersArray);
            }
            
            results.put("Q" + question.getQuestionNumber() + ": " + question.getPrompt(), tabulationResult);
        }
        
        return results;
    }
    
    /* takes in question and responses, returns tabulation for true/false */
    private Map<String, Integer> tabulateTrueFalse(Question question, Answer[] responses) {
        Map<String, Integer> tabulation = new LinkedHashMap<>();
        tabulation.put("True", 0);
        tabulation.put("False", 0);
        
        for (Answer answer : responses) {
            if (answer != null && answer.getAnswerValue() != null) {
                String value = answer.getAnswerValue().toString();
                if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t")) {
                    tabulation.put("True", tabulation.get("True") + 1);
                } else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("f")) {
                    tabulation.put("False", tabulation.get("False") + 1);
                }
            }
        }
        
        return tabulation;
    }
    
    /* takes in question and responses, returns tabulation for multiple choice */
    private Map<String, Integer> tabulateMultipleChoice(MultipleChoiceQuestion question, Answer[] responses) {
        Map<String, Integer> tabulation = new LinkedHashMap<>();
        
        String[] choices = question.getChoices();
        for (String choice : choices) {
            tabulation.put(choice, 0);
        }
        
        for (Answer answer : responses) {
            if (answer != null && answer.getAnswerValue() != null) {
                String value = answer.getAnswerValue().toString();
                if (tabulation.containsKey(value)) {
                    tabulation.put(value, tabulation.get(value) + 1);
                }
            }
        }
        
        return tabulation;
    }
    
    /* takes in question and responses, returns tabulation for short answer */
    private Map<String, Integer> tabulateShortAnswer(ShortAnswerQuestion question, Answer[] responses) {
        Map<String, Integer> tabulation = new LinkedHashMap<>();
        
        for (Answer answer : responses) {
            if (answer != null && answer.getAnswerValue() != null) {
                String value = answer.getAnswerValue().toString();
                tabulation.put(value, tabulation.getOrDefault(value, 0) + 1);
            }
        }
        
        return tabulation;
    }
    
    /* takes in question and responses, returns list of essay responses */
    private List<String> tabulateEssay(EssayQuestion question, Answer[] responses) {
        List<String> essays = new ArrayList<>();
        
        for (Answer answer : responses) {
            if (answer != null && answer.getAnswerValue() != null) {
                essays.add(answer.getAnswerValue().toString());
            }
        }
        
        return essays;
    }
    
    /* takes in question and responses, returns tabulation for date */
    private Map<String, Integer> tabulateDate(ValidDateQuestion question, Answer[] responses) {
        Map<String, Integer> tabulation = new LinkedHashMap<>();
        
        for (Answer answer : responses) {
            if (answer != null && answer.getAnswerValue() != null) {
                String value = answer.getAnswerValue().toString();
                tabulation.put(value, tabulation.getOrDefault(value, 0) + 1);
            }
        }
        
        return tabulation;
    }
    
    /* takes in question and responses, returns tabulation for matching */
    private Map<String, Integer> tabulateMatching(MatchingQuestion question, Answer[] responses) {
        Map<String, Integer> tabulation = new LinkedHashMap<>();
        
        for (Answer answer : responses) {
            if (answer != null && answer.getAnswerValue() != null) {
                String value = answer.getAnswerValue().toString();
                tabulation.put(value, tabulation.getOrDefault(value, 0) + 1);
            }
        }
        
        return tabulation;
    }
}
