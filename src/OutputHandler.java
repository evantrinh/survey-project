import java.util.List;
import java.util.Map;

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

	/* takes in grade report, displays grade report */
	public void displayGradeReport(GradeReport report) {
		if (report == null) {
			displayError("No grade report to display.");
			return;
		}

		System.out.println("\n=== Grade Report ===");
		System.out.printf("You received a %.0f on the test. ", report.getScore());
		System.out.printf("The test was worth %.0f points, ", report.getTotalPoints());
		System.out.printf("but only %.0f of those points could be auto-graded", report.getGradablePoints());

		if (report.getNumEssayQuestions() > 0) {
			System.out.printf(" because there %s %d essay question%s.",
					report.getNumEssayQuestions() == 1 ? "was" : "were", report.getNumEssayQuestions(),
					report.getNumEssayQuestions() == 1 ? "" : "s");
		} else {
			System.out.println(".");
		}

		System.out.println();
	}

	/* takes in tabulation results, displays tabulation */
	public void displayTabulation(Map<String, Object> results) {
		if (results == null || results.isEmpty()) {
			displayError("No tabulation results to display.");
			return;
		}

		System.out.println("\n=== Tabulation Results ===");

		for (Map.Entry<String, Object> entry : results.entrySet()) {
			System.out.println("\n" + entry.getKey());
			Object value = entry.getValue();

			if (value instanceof Map<?, ?> map) {
				for (Map.Entry<?, ?> item : map.entrySet()) {
					System.out.println("  " + item.getKey() + ": " + item.getValue());
				}
			} else if (value instanceof List<?> list) {
				for (int i = 0; i < list.size(); i++) {
					System.out.println("  " + (i + 1) + ". " + list.get(i));
				}
			}
		}

		System.out.println();
	}
}
