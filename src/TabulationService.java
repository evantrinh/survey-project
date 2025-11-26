import java.util.*;

public class TabulationService {

	/* default constructor */
	public TabulationService() {
	}

	/* takes in survey, displays tabulation directly */
	public void tabulateSurvey(Survey survey) {
		Question[] questions = survey.getAllQuestions();
		SurveyResponse[] responses = survey.getResponses();

		for (int i = 0; i < questions.length; i++) {
			Question question = questions[i];
			List<Answer> answersForQuestion = new ArrayList<>();

			for (SurveyResponse response : responses) {
				Answer answer = response.getAnswer(question.getQuestionNumber());
				if (answer != null) {
					answersForQuestion.add(answer);
				}
			}

			Answer[] answersArray = answersForQuestion.toArray(new Answer[0]);

			if (question instanceof TrueFalseQuestion) {
				tabulateTrueFalse((TrueFalseQuestion) question, answersArray);
			} else if (question instanceof MultipleChoiceQuestion) {
				tabulateMultipleChoice((MultipleChoiceQuestion) question, answersArray);
			} else if (question instanceof ShortAnswerQuestion) {
				tabulateShortAnswer((ShortAnswerQuestion) question, answersArray);
			} else if (question instanceof EssayQuestion) {
				tabulateEssay((EssayQuestion) question, answersArray);
			} else if (question instanceof ValidDateQuestion) {
				tabulateDate((ValidDateQuestion) question, answersArray);
			} else if (question instanceof MatchingQuestion) {
				tabulateMatching((MatchingQuestion) question, answersArray);
			}
		}
	}

	/* takes in question and responses, displays tabulation for true/false */
	private void tabulateTrueFalse(TrueFalseQuestion question, Answer[] responses) {
		int trueCount = 0;
		int falseCount = 0;

		for (Answer answer : responses) {
			if (answer != null && answer.getAnswerValue() != null) {
				String value = answer.getAnswerValue().toString();
				if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t")) {
					trueCount++;
				} else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("f")) {
					falseCount++;
				}
			}
		}

		System.out.println(question.getPrompt());
		System.out.println("True: " + trueCount);
		System.out.println("False: " + falseCount);
		System.out.println();
	}

	/* takes in question and responses, displays tabulation for multiple choice */
	private void tabulateMultipleChoice(MultipleChoiceQuestion question, Answer[] responses) {
		String[] choices = question.getChoices();
		int[] counts = new int[choices.length];

		for (Answer answer : responses) {
			if (answer != null && answer.getAnswerValue() != null) {
				if (answer instanceof MultipleAnswer) {
					Object[] values = ((MultipleAnswer) answer).getAnswerValues();
					for (Object val : values) {
						countChoice(counts, choices.length, val.toString());
					}
				} else {
					countChoice(counts, choices.length, answer.getAnswerValue().toString());
				}
			}
		}

		System.out.println(question.getPrompt());
		for (int i = 0; i < choices.length; i++) {
			System.out.println((char) ('A' + i) + ": " + counts[i]);
		}
		System.out.println();
	}

	/* helper to count a choice by letter */
	private void countChoice(int[] counts, int numChoices, String letter) {
		letter = letter.trim().toUpperCase();
		if (!letter.isEmpty()) {
			int index = letter.charAt(0) - 'A';
			if (index >= 0 && index < numChoices) {
				counts[index]++;
			}
		}
	}

	/* takes in question and responses, displays tabulation for short answer */
	private void tabulateShortAnswer(ShortAnswerQuestion question, Answer[] responses) {
		Map<String, Integer> tabulation = new LinkedHashMap<>();

		for (Answer answer : responses) {
			if (answer != null && answer.getAnswerValue() != null) {
				if (answer instanceof MultipleAnswer) {
					Object[] values = ((MultipleAnswer) answer).getAnswerValues();
					for (Object val : values) {
						String value = val.toString();
						tabulation.put(value, tabulation.getOrDefault(value, 0) + 1);
					}
				} else {
					String value = answer.getAnswerValue().toString();
					tabulation.put(value, tabulation.getOrDefault(value, 0) + 1);
				}
			}
		}

		System.out.println(question.getPrompt());
		for (Map.Entry<String, Integer> entry : tabulation.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		System.out.println();
	}

	/* takes in question and responses, displays tabulation for essay */
	private void tabulateEssay(EssayQuestion question, Answer[] responses) {
		System.out.println(question.getPrompt());

		for (Answer answer : responses) {
			if (answer != null && answer.getAnswerValue() != null) {
				if (answer instanceof MultipleAnswer) {
					Object[] values = ((MultipleAnswer) answer).getAnswerValues();
					for (Object val : values) {
                        System.out.println();
						System.out.println(val.toString());
					}
				} else {
                    System.out.println();
					System.out.println(answer.getAnswerValue().toString());
				}
			}
		}
		System.out.println();
	}

	/* takes in question and responses, displays tabulation for date */
	private void tabulateDate(ValidDateQuestion question, Answer[] responses) {
		Map<String, Integer> tabulation = new LinkedHashMap<>();

		for (Answer answer : responses) {
			if (answer != null && answer.getAnswerValue() != null) {
				if (answer instanceof MultipleAnswer) {
					Object[] values = ((MultipleAnswer) answer).getAnswerValues();
					for (Object val : values) {
						String value = val.toString();
						tabulation.put(value, tabulation.getOrDefault(value, 0) + 1);
					}
				} else {
					String value = answer.getAnswerValue().toString();
					tabulation.put(value, tabulation.getOrDefault(value, 0) + 1);
				}
			}
		}

		System.out.println(question.getPrompt());
		for (Map.Entry<String, Integer> entry : tabulation.entrySet()) {
            System.out.println();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		System.out.println();
	}

	/* takes in question and responses, displays tabulation for matching */
	private void tabulateMatching(MatchingQuestion question, Answer[] responses) {
		Map<String, Integer> tabulation = new LinkedHashMap<>();

		for (Answer answer : responses) {
			if (answer != null && answer.getAnswerValue() != null) {
				String permutation;
				if (answer instanceof MultipleAnswer) {
					Object[] values = ((MultipleAnswer) answer).getAnswerValues();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < values.length; i++) {
						if (i > 0)
							sb.append("\n");
						sb.append(values[i].toString());
					}
					permutation = sb.toString();
				} else {
					permutation = answer.getAnswerValue().toString();
				}
				tabulation.put(permutation, tabulation.getOrDefault(permutation, 0) + 1);
			}
		}

		/* display matching question with columns */
		System.out.println(question.getPrompt());
		String[] left = question.getLeftColumn();
		String[] right = question.getRightColumn();
		int maxRows = Math.max(left.length, right.length);
		int maxLeftWidth = 0;
		for (String item : left) {
			int width = item.length() + 3;
			if (width > maxLeftWidth) {
				maxLeftWidth = width;
			}
		}
		for (int i = 0; i < maxRows; i++) {
			String leftPart = "";
			String rightPart = "";
			if (i < left.length) {
				leftPart = (char) ('A' + i) + ") " + left[i];
			}
			if (i < right.length) {
				rightPart = (i + 1) + ") " + right[i];
			}
			System.out.printf("%-" + (maxLeftWidth + 5) + "s%s%n", leftPart, rightPart);
		}

		/* display permutations with count first, then lines */
		for (Map.Entry<String, Integer> entry : tabulation.entrySet()) {
            System.out.println();
			System.out.println(entry.getValue());
			System.out.println(entry.getKey());
		}
		System.out.println();
	}
}
