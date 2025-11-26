import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Test extends Survey {
	@Serial
	private static final long serialVersionUID = 1L;

	private List<Answer> correctAnswers;

	/* default constructor */
	public Test() {
		super();
		this.correctAnswers = new ArrayList<>();
	}

	/* takes in name, constructs test */
	public Test(String name) {
		super(name);
		this.correctAnswers = new ArrayList<>();
	}

	/* takes in answer, adds correct answer */
	public void addCorrectAnswer(Answer answer) {
		this.correctAnswers.add(answer);
	}

	/* takes in index, returns correct answer */
	public Answer getCorrectAnswer(int index) {
		if (index >= 0 && index < correctAnswers.size()) {
			return correctAnswers.get(index);
		}
		return null;
	}

	/* returns all correct answers */
	public Answer[] getAllCorrectAnswers() {
		return correctAnswers.toArray(new Answer[0]);
	}

	/* takes in index and answer, sets correct answer */
	public void setCorrectAnswer(int index, Answer answer) {
		if (index >= 0 && index < correctAnswers.size()) {
			correctAnswers.set(index, answer);
		} else if (index == correctAnswers.size()) {
			correctAnswers.add(answer);
		}
	}

	/* displays test with correct answers */
	public void displayTestWithAnswers() {
		System.out.println("\nTest: " + getSurveyName());
		System.out.println("Number of questions: " + getNumQuestions());
		System.out.println();

		Question[] questions = getAllQuestions();
		for (int i = 0; i < questions.length; i++) {
			questions[i].displayQuestion();

			if (i < correctAnswers.size() && correctAnswers.get(i) != null) {
				Object value = correctAnswers.get(i).getAnswerValue();
				Question question = questions[i];

				if (value != null) {
					if (question instanceof TrueFalseQuestion) {
						System.out.println("The correct answer is " + value);
					} else if (question instanceof MultipleChoiceQuestion) {
						String letter = value.toString().trim().toUpperCase();
						if (!letter.isEmpty()) {
							int index = letter.charAt(0) - 'A';
							String[] choices = ((MultipleChoiceQuestion) question).getChoices();
							if (index >= 0 && index < choices.length) {
								System.out.println("The correct choice is " + letter + ") " + choices[index]);
							} else {
								System.out.println("The correct answer is " + value);
							}
						} else {
							System.out.println("The correct answer is " + value);
						}
					} else {
						System.out.println("The correct answer is " + value);
					}
				} else {
					System.out.println("The correct answer is Not set");
				}
			}

			System.out.println();
		}
	}
}
