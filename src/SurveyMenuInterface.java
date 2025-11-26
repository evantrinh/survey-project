public class SurveyMenuInterface {
	private SurveyManager manager;
	private InputHandler inputHandler;
	private OutputHandler outputHandler;
	private TabulationService tabulationService;
	private GradingService gradingService;
	private FileService fileService;

	/* default constructor */
	public SurveyMenuInterface() {
		this.manager = new SurveyManager();
		this.inputHandler = new InputHandler();
		this.outputHandler = new OutputHandler();
		this.tabulationService = new TabulationService();
		this.gradingService = new GradingService();
		this.fileService = new FileService();
	}

	/* displays main menu */
	public void displayMainMenu() {
		boolean running = true;

		while (running) {
			System.out.println("\n=== MAIN MENU ===");
			System.out.println("1) Survey");
			System.out.println("2) Test");
			System.out.println("3) Quit");

			int choice = getIntInput("Enter your choice (1-3): ");

			switch (choice) {
			case 1:
				displaySurveyMenu();
				break;
			case 2:
				displayTestMenu();
				break;
			case 3:
				handleExit();
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 3.");
			}
		}
	}

	/* displays survey menu */
	public void displaySurveyMenu() {
		boolean running = true;

		while (running) {
			System.out.println("\n=== SURVEY MENU ===");
			System.out.println("1) Create a new Survey");
			System.out.println("2) Display an existing Survey");
			System.out.println("3) Load an existing Survey");
			System.out.println("4) Save the current Survey");
			System.out.println("5) Take the current Survey");
			System.out.println("6) Modify the current Survey");
			System.out.println("7) Tabulate a survey");
			System.out.println("8) Return to previous menu");

			int choice = getIntInput("Enter your choice (1-8): ");

			switch (choice) {
			case 1:
				handleCreateSurvey();
				break;
			case 2:
				handleDisplaySurvey();
				break;
			case 3:
				handleLoadSurvey();
				break;
			case 4:
				handleSaveSurvey();
				break;
			case 5:
				handleTakeSurvey();
				break;
			case 6:
				handleModifySurvey();
				break;
			case 7:
				handleTabulateSurvey();
				break;
			case 8:
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 8.");
			}
		}
	}

	/* displays test menu */
	public void displayTestMenu() {
		boolean running = true;

		while (running) {
			System.out.println("\n=== TEST MENU ===");
			System.out.println("1) Create a new Test");
			System.out.println("2) Display an existing Test without correct answers");
			System.out.println("3) Display an existing Test with correct answers");
			System.out.println("4) Load an existing Test");
			System.out.println("5) Save the current Test");
			System.out.println("6) Take the current Test");
			System.out.println("7) Modify the current Test");
			System.out.println("8) Tabulate a Test");
			System.out.println("9) Grade a Test");
			System.out.println("10) Return to the previous menu");

			int choice = getIntInput("Enter your choice (1-10): ");

			switch (choice) {
			case 1:
				handleCreateTest();
				break;
			case 2:
				handleDisplayTest();
				break;
			case 3:
				handleDisplayTestWithAnswers();
				break;
			case 4:
				handleLoadTest();
				break;
			case 5:
				handleSaveTest();
				break;
			case 6:
				handleTakeTest();
				break;
			case 7:
				handleModifyTest();
				break;
			case 8:
				handleTabulateTest();
				break;
			case 9:
				handleGradeTest();
				break;
			case 10:
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 10.");
			}
		}
	}

	/* handles creating new survey */
	public void handleCreateSurvey() {
		System.out.println("\n=== Create New Survey ===");
		Survey survey = manager.createSurvey("");

		// add questions using Menu 3
		boolean addingQuestions = true;
		while (addingQuestions) {
			addingQuestions = displayAddQuestionMenu(survey);
		}

		outputHandler.displayMessage("Survey created successfully!");
	}

	/* takes in survey, displays add question menu, and returns if continue */
	private boolean displayAddQuestionMenu(Survey survey) {
		System.out.println("1) Add a new T/F question");
		System.out.println("2) Add a new multiple-choice question");
		System.out.println("3) Add a new short answer question");
		System.out.println("4) Add a new essay question");
		System.out.println("5) Add a new date question");
		System.out.println("6) Add a new matching question");
		System.out.println("7) Return to previous menu");

		int choice = getIntInput("Enter your choice (1-7): ");

		switch (choice) {
		case 1:
			addTrueFalseQuestion(survey);
			return true;
		case 2:
			addMultipleChoiceQuestion(survey);
			return true;
		case 3:
			addShortAnswerQuestion(survey);
			return true;
		case 4:
			addEssayQuestion(survey);
			return true;
		case 5:
			addDateQuestion(survey);
			return true;
		case 6:
			addMatchingQuestion(survey);
			return true;
		case 7:
			return false;
		default:
			System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			return true;
		}
	}

	/* takes in test, displays add question menu, adds correct answer after each question */
	private boolean displayAddTestQuestionMenu(Test test) {
		System.out.println("1) Add a new T/F question");
		System.out.println("2) Add a new multiple-choice question");
		System.out.println("3) Add a new short answer question");
		System.out.println("4) Add a new essay question");
		System.out.println("5) Add a new date question");
		System.out.println("6) Add a new matching question");
		System.out.println("7) Return to previous menu");

		int choice = getIntInput("Enter your choice (1-7): ");

		int questionIndex = test.getNumQuestions();

		switch (choice) {
		case 1:
			addTrueFalseQuestion(test);
			addCorrectAnswer(test, questionIndex);
			return true;
		case 2:
			addMultipleChoiceQuestion(test);
			addCorrectAnswer(test, questionIndex);
			return true;
		case 3:
			addShortAnswerQuestion(test);
			addCorrectAnswer(test, questionIndex);
			return true;
		case 4:
			addEssayQuestion(test);
			// essays cannot be graded, add null correct answer
			test.addCorrectAnswer(null);
			return true;
		case 5:
			addDateQuestion(test);
			addCorrectAnswer(test, questionIndex);
			return true;
		case 6:
			addMatchingQuestion(test);
			addCorrectAnswer(test, questionIndex);
			return true;
		case 7:
			return false;
		default:
			System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			return true;
		}
	}

	/* takes in survey, adds true/false question */
	private void addTrueFalseQuestion(Survey survey) {
		System.out.println("\nEnter the prompt for your True/False question:");

		String prompt = getStringInput("");
		TrueFalseQuestion question = new TrueFalseQuestion(prompt);

		survey.addQuestion(question);
		System.out.println("True/False question added.");
	}

	/* takes in survey, adds multiple choice question */
	private void addMultipleChoiceQuestion(Survey survey) {
		System.out.println("\nEnter the prompt for your multiple-choice question:");
		String prompt = getStringInput("");

		int numChoices = getIntInput("Enter the number of choices for your multiple-choice question: ");

		// get answer choices
		MultipleChoiceQuestion question = new MultipleChoiceQuestion(prompt);
		for (int i = 0; i < numChoices; i++) {
			System.out.println("Enter choice #" + (i + 1) + ":");
			String choice = getStringInput("");
			question.addChoice(choice);
		}

		// get if multiple responses, no. of responses
		boolean allowMultiple = getYesNoInput("Does this question allow multiple responses?");
		if (allowMultiple) {
			int maxSelections = getIntInput("Enter the maximum number of selections allowed: ");
			question.setMaxSelections(maxSelections);
		} else {
			question.setMaxSelections(1);
		}

		survey.addQuestion(question);
		System.out.println("Multiple choice question added.");
	}

	/* takes in survey, adds short answer question */
	private void addShortAnswerQuestion(Survey survey) {
		System.out.println("\nEnter the prompt for your short answer question:");
		String prompt = getStringInput("");

		ShortAnswerQuestion question = new ShortAnswerQuestion(prompt);

		boolean allowMultiple = getYesNoInput("Does this question allow multiple short answers?");
		if (allowMultiple) {
			int numResponses = getIntInput("How many short answers are required? ");
			question.setNumResponsesRequired(numResponses);
		}

		int maxLength = getIntInput("Enter maximum character length: ");
		question.setMaxLength(maxLength);

		survey.addQuestion(question);
		System.out.println("Short answer question added.");
	}

	/* takes in survey, adds essay question */
	private void addEssayQuestion(Survey survey) {
		System.out.println("\nEnter the prompt for your essay question:");
		String prompt = getStringInput("");

		EssayQuestion question = new EssayQuestion(prompt);

		boolean allowMultiple = getYesNoInput("Does this question allow multiple essay responses?");
		if (allowMultiple) {
			int numResponses = getIntInput("How many essay responses are required? ");
			question.setNumResponsesRequired(numResponses);
		}

		int minWords = getIntInput("Enter minimum words: ");
		int maxWords = getIntInput("Enter maximum words: ");
		question.setMinWords(minWords);
		question.setMaxWords(maxWords);

		survey.addQuestion(question);
		System.out.println("Essay question added.");
	}

	/* takes in survey, adds date question */
	private void addDateQuestion(Survey survey) {
		System.out.println("\nEnter the prompt for your date question:");
		String prompt = getStringInput("");

		ValidDateQuestion question = new ValidDateQuestion(prompt);

		boolean allowMultiple = getYesNoInput("Does this question allow multiple dates?");
		if (allowMultiple) {
			int numResponses = getIntInput("How many dates are required? ");
			question.setNumResponsesRequired(numResponses);
		}

		survey.addQuestion(question);
		System.out.println("Date question added.");
	}

	/* takes in survey, adds matching question */
	private void addMatchingQuestion(Survey survey) {
		System.out.println("\nEnter the prompt for your matching question:");
		String prompt = getStringInput("");

		MatchingQuestion question = new MatchingQuestion(prompt);

		int numPairs = getIntInput("Enter the number of items to match: ");

		System.out.println("\nEnter the left column items:");
		for (int i = 0; i < numPairs; i++) {
			System.out.println("Enter item " + (char) ('A' + i) + ":");
			String item = getStringInput("");
			question.addLeftItem(item);
		}

		System.out.println("\nEnter the right column items:");
		for (int i = 0; i < numPairs; i++) {
			System.out.println("Enter item " + (i + 1) + ":");
			String item = getStringInput("");
			question.addRightItem(item);
		}

		survey.addQuestion(question);
		System.out.println("Matching question added.");
	}

	/* handles modifying survey */
	public void handleModifySurvey() {
		System.out.println("\n=== Modify Survey ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (currentSurvey == null) {
			System.out.println("You must have a survey loaded in order to modify it.");
			return;
		}

		outputHandler.displaySurvey(currentSurvey);

		System.out.println("\nWhat question do you wish to modify?");
		int questionNum = getIntInput("Enter question number: ");

		Question question = currentSurvey.getQuestion(questionNum - 1);
		if (question == null) {
			System.out.println("Invalid question number.");
			return;
		}

		modifyQuestionInteractive(question);
	}

	/* handles loading survey */
	public void handleLoadSurvey() {
		System.out.println("\n=== Load Survey ===");

		String[] files = fileService.listSurveyFiles();

		if (files == null || files.length == 0) {
			System.out.println("No survey files found.");
			return;
		}

		System.out.println("Please select a file to load:");
		for (int i = 0; i < files.length; i++) {
			System.out.println((i + 1) + ") " + files[i].replace(".ser", ""));
		}

		int choice = getIntInput("Enter your choice: ");

		if (choice < 1 || choice > files.length) {
			System.out.println("Invalid choice.");
			return;
		}

		String filename = files[choice - 1];
		Survey loadedSurvey = fileService.loadSurvey(filename);
		if (loadedSurvey != null) {
			manager.setCurrentSurvey(loadedSurvey);
		}
	}

	/* handles saving survey */
	public void handleSaveSurvey() {
		System.out.println("\n=== Save Survey ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (currentSurvey == null) {
			System.out.println("You must have a survey loaded in order to save it.");
			return;
		}

		String filename = getStringInput("Enter filename to save: ");

		if (!filename.endsWith(".ser")) {
			filename += ".ser";
		}

		fileService.saveSurvey(currentSurvey, filename);
	}

	/* handles taking survey */
	public void handleTakeSurvey() {
		System.out.println("\n=== Take Survey ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (currentSurvey == null) {
			System.out.println("You must have a survey loaded in order to take it.");
			return;
		}

		if (currentSurvey.getNumQuestions() == 0) {
			System.out.println("This survey has no questions.");
			return;
		}

		SurveyResponse response = currentSurvey.takeSurvey();

		// save response to separate file per assignment requirements
		String responseFilename = "response_" + System.currentTimeMillis() + ".ser";
		fileService.saveResponse(response, responseFilename);
	}

	/* handles displaying survey */
	public void handleDisplaySurvey() {
		System.out.println("\n=== Display Survey ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (currentSurvey == null) {
			System.out.println("You must have a survey loaded in order to display it.");
			return;
		}

		outputHandler.displaySurvey(currentSurvey);
	}

	/* takes in question, modifies question interactively */
	private void modifyQuestionInteractive(Question question) {
		// display current prompt
		System.out.println("\nCurrent prompt: " + question.getPrompt());
		System.out.println("Do you wish to modify the prompt? (y/n)");
		String modifyPrompt = getStringInput("");

		if (modifyPrompt.equalsIgnoreCase("y")) {
			System.out.println("Enter a new prompt:");
			String newPrompt = getStringInput("");
			question.setPrompt(newPrompt);
			System.out.println("Prompt updated.");
		}

		// handle specific question types
		if (question instanceof MultipleChoiceQuestion && !(question instanceof TrueFalseQuestion)) {
			modifyMultipleChoiceQuestion((MultipleChoiceQuestion) question);
		} else if (question instanceof TrueFalseQuestion) {
			System.out.println("True/False question prompt modified.");
		} else if (question instanceof ShortAnswerQuestion) {
			modifyShortAnswerQuestion((ShortAnswerQuestion) question);
		} else if (question instanceof EssayQuestion) {
			modifyEssayQuestion((EssayQuestion) question);
		} else if (question instanceof MatchingQuestion) {
			modifyMatchingQuestion((MatchingQuestion) question);
		} else if (question instanceof ValidDateQuestion) {
			modifyDateQuestion((ValidDateQuestion) question);
		}
	}

	/* takes in question, modifies multiple choice question */
	private void modifyMultipleChoiceQuestion(MultipleChoiceQuestion question) {
		System.out.println("\nDo you wish to modify choices? (y/n)");
		String modifyChoices = getStringInput("");

		if (modifyChoices.equalsIgnoreCase("y")) {
			// show choices to modify
			String[] choices = question.getChoices();
			System.out.println("\nCurrent choices:");
			for (int i = 0; i < choices.length; i++) {
				System.out.println((char) ('A' + i) + ") " + choices[i]);
			}

			System.out.println("\nWhich choice do you want to modify?");
			String choiceLetter = getStringInput("").toUpperCase();

			// modify choice by prompting user
			if (!choiceLetter.isEmpty()) {
				int index = choiceLetter.charAt(0) - 'A';
				if (index >= 0 && index < choices.length) {
					System.out.println("Enter new value for choice " + choiceLetter + ":");
					String newValue = getStringInput("");

					// rebuild choices list with the updated value
					question.removeChoice(index);

					// insert new value at the same position
					String[] tempChoices = new String[choices.length];
					for (int i = 0; i < choices.length; i++) {
						if (i == index) {
							tempChoices[i] = newValue;
						} else if (i < index) {
							tempChoices[i] = choices[i];
						} else {
							tempChoices[i] = choices[i];
						}
					}

					// clear and re-add all choices in correct order
					String[] currentChoices = question.getChoices();
					for (int i = currentChoices.length - 1; i >= 0; i--) {
						question.removeChoice(i);
					}

					for (String tempChoice : tempChoices) {
						question.addChoice(tempChoice);
					}

					System.out.println("Choice updated.");
				} else {
					System.out.println("Invalid choice letter.");
				}
			}
		}
	}

	/* takes in question, modifies short answer question */
	private void modifyShortAnswerQuestion(ShortAnswerQuestion question) {
		System.out.println("\nCurrent max length: " + question.getMaxLength());
		System.out.println("Do you wish to modify the max length? (y/n)");
		String modify = getStringInput("");

		if (modify.equalsIgnoreCase("y")) {
			int newMax = getIntInput("Enter new max length: ");
			question.setMaxLength(newMax);
			System.out.println("Max length updated.");
		}
	}

	/* takes in question, modifies essay question */
	private void modifyEssayQuestion(EssayQuestion question) {
		System.out.println("\nCurrent min words: " + question.getMinWords());
		System.out.println("Current max words: " + question.getMaxWords());
		System.out.println("Do you wish to modify word limits? (y/n)");
		String modify = getStringInput("");

		if (modify.equalsIgnoreCase("y")) {
			int newMin = getIntInput("Enter new min words: ");
			int newMax = getIntInput("Enter new max words: ");
			question.setMinWords(newMin);
			question.setMaxWords(newMax);
			System.out.println("Word limits updated.");
		}
	}

	/* takes in question, modifies matching question */
	private void modifyMatchingQuestion(MatchingQuestion question) {
		System.out.println("\nDo you wish to modify matching items? (y/n)");
		String modify = getStringInput("");

		if (modify.equalsIgnoreCase("y")) {
			question.modifyQuestion();
		}
	}

	/* takes in question, modifies date question */
	private void modifyDateQuestion(ValidDateQuestion question) {
		System.out.println("\nCurrent date format: " + question.getDateFormat());
		System.out.println("Do you wish to modify the date format? (y/n)");
		String modify = getStringInput("");

		if (modify.equalsIgnoreCase("y")) {
			String newFormat = getStringInput("Enter new date format: ");
			question.setDateFormat(newFormat);
			System.out.println("Date format updated.");
		}
	}

	/* handles exiting application */
	public void handleExit() {
		System.out.println("\nExiting the survey system.");

		Survey currentSurvey = manager.getCurrentSurvey();
		if (currentSurvey != null) {
			String save = getStringInput("Save current survey before exiting? (y/n): ");
			if (save.equalsIgnoreCase("y") || save.equalsIgnoreCase("yes")) {
				if (currentSurvey instanceof Test) {
					handleSaveTest();
				} else {
					handleSaveSurvey();
				}
			}
		}
	}

	/* handles tabulating survey */
	public void handleTabulateSurvey() {
		System.out.println("\n=== Tabulate Survey ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (currentSurvey == null) {
			System.out.println("You must have a survey loaded in order to tabulate it.");
			return;
		}

		if (currentSurvey.getResponses().length == 0) {
			System.out.println("No responses to tabulate.");
			return;
		}

		tabulationService.tabulateSurvey(currentSurvey);
	}

	/* handles creating test */
	public void handleCreateTest() {
		System.out.println("\n=== Create New Test ===");
		Test test = new Test("");

		/* add questions using menu 3 - correct answers prompted after each question */
		boolean addingQuestions = true;
		while (addingQuestions) {
			addingQuestions = displayAddTestQuestionMenu(test);
		}

		manager.setCurrentSurvey(test);
		System.out.println("Test created successfully!");
	}

	/* handles displaying test */
	public void handleDisplayTest() {
		System.out.println("\n=== Display Test ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (!(currentSurvey instanceof Test)) {
			System.out.println("You must have a test loaded in order to display it.");
			return;
		}

		outputHandler.displaySurvey(currentSurvey);
	}

	/* handles loading test */
	public void handleLoadTest() {
		System.out.println("\n=== Load Test ===");

		String[] files = fileService.listSurveyFiles();

		if (files == null || files.length == 0) {
			System.out.println("No test files found.");
			return;
		}

		System.out.println("Please select a file to load:");
		for (int i = 0; i < files.length; i++) {
			System.out.println((i + 1) + ") " + files[i].replace(".ser", ""));
		}

		int choice = getIntInput("Enter your choice: ");

		if (choice < 1 || choice > files.length) {
			System.out.println("Invalid choice.");
			return;
		}

		String filename = files[choice - 1];
		Survey loadedSurvey = fileService.loadSurvey(filename);

		if (loadedSurvey instanceof Test) {
			manager.setCurrentSurvey(loadedSurvey);
		} else if (loadedSurvey != null) {
			System.out.println("Loaded file is not a test.");
		}
	}

	/* handles saving test */
	public void handleSaveTest() {
		System.out.println("\n=== Save Test ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (!(currentSurvey instanceof Test)) {
			System.out.println("You must have a test loaded in order to save it.");
			return;
		}

		String filename = getStringInput("Enter filename to save: ");

		if (!filename.endsWith(".ser")) {
			filename += ".ser";
		}

		fileService.saveSurvey(currentSurvey, filename);
	}

	/* handles taking test */
	public void handleTakeTest() {
		System.out.println("\n=== Take Test ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (!(currentSurvey instanceof Test)) {
			System.out.println("You must have a test loaded in order to take it.");
			return;
		}

		if (currentSurvey.getNumQuestions() == 0) {
			System.out.println("This test has no questions.");
			return;
		}

		SurveyResponse response = currentSurvey.takeSurvey();

		// save response to separate file per assignment requirements
		String responseFilename = "response_" + System.currentTimeMillis() + ".ser";
		fileService.saveResponse(response, responseFilename);
	}

	/* handles modifying test */
	public void handleModifyTest() {
		System.out.println("\n=== Modify Test ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (!(currentSurvey instanceof Test test)) {
			System.out.println("You must have a test loaded in order to modify it.");
			return;
		}

		outputHandler.displaySurvey(currentSurvey);

		System.out.println("\nWhat question do you wish to modify?");
		int questionNum = getIntInput("Enter question number: ");

		Question question = currentSurvey.getQuestion(questionNum - 1);
		if (question == null) {
			System.out.println("Invalid question number.");
			return;
		}

		modifyQuestionInteractive(question);

		/* update correct answer using helper method */
		modifyCorrectAnswer(test, questionNum - 1);
	}

	/* handles tabulating test */
	public void handleTabulateTest() {
		System.out.println("\n=== Tabulate Test ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (!(currentSurvey instanceof Test)) {
			System.out.println("You must have a test loaded in order to tabulate it.");
			return;
		}

		if (currentSurvey.getResponses().length == 0) {
			System.out.println("No responses to tabulate.");
			return;
		}

		tabulationService.tabulateSurvey(currentSurvey);
	}

	/* handles grading test */
	public void handleGradeTest() {
		System.out.println("\n=== Grade Test ===");

		// list only test files for selection
		String[] files = fileService.listTestFiles();

		if (files == null || files.length == 0) {
			System.out.println("No test files found.");
			return;
		}

		System.out.println("Select an existing test to grade:");
		for (int i = 0; i < files.length; i++) {
			System.out.println((i + 1) + ") " + files[i].replace(".ser", ""));
		}

		int testChoice = getIntInput("Enter your choice: ");

		if (testChoice < 1 || testChoice > files.length) {
			System.out.println("Invalid choice.");
			return;
		}

		String filename = files[testChoice - 1];
		String testName = filename.replace(".ser", "");
		Survey loadedSurvey = fileService.loadSurvey(filename);

		if (!(loadedSurvey instanceof Test test)) {
			System.out.println("Selected file is not a test.");
			return;
		}

		if (test.getResponses().length == 0) {
			System.out.println("No responses to grade.");
			return;
		}

		// list all responses for selection using filename
		SurveyResponse[] responses = test.getResponses();
		System.out.println("\nSelect an existing response set:");
		for (int i = 0; i < responses.length; i++) {
			System.out.println((i + 1) + ") " + testName + " - Response " + (i + 1));
		}

		int responseChoice = getIntInput("Enter your choice: ");

		if (responseChoice < 1 || responseChoice > responses.length) {
			System.out.println("Invalid choice.");
			return;
		}

		SurveyResponse response = responses[responseChoice - 1];
		GradeReport report = gradingService.gradeTest(test, response);
		outputHandler.displayGradeReport(report);
	}

	/* handles displaying test with correct answers */
	public void handleDisplayTestWithAnswers() {
		System.out.println("\n=== Display Test with Correct Answers ===");
		Survey currentSurvey = manager.getCurrentSurvey();

		if (!(currentSurvey instanceof Test test)) {
			System.out.println("You must have a test loaded in order to display it.");
			return;
		}

		test.displayTestWithAnswers();
	}

	/* takes in test and question index, adds correct answer */
	private void addCorrectAnswer(Test test, int questionIndex) {
		Question question = test.getQuestion(questionIndex);

		System.out.println("Enter the correct answer:");
		Answer correctAnswer = question.takeAnswer();
		test.addCorrectAnswer(correctAnswer);
	}

	/* takes in test and question index, modifies correct answer */
	private void modifyCorrectAnswer(Test test, int questionIndex) {
		Question question = test.getQuestion(questionIndex);

		/* skip pure essay questions - they cannot be auto-graded */
		if (question instanceof EssayQuestion && !(question instanceof ShortAnswerQuestion)) {
			return;
		}

		boolean update = getYesNoInput("Do you want to update the correct answer?");
		if (update) {
			System.out.println("Enter the correct answer:");
			Answer correctAnswer = question.takeAnswer();
			test.setCorrectAnswer(questionIndex, correctAnswer);
			System.out.println("Correct answer updated.");
		}
	}

	/* takes in prompt, returns integer input */
	private int getIntInput(String prompt) {
		return inputHandler.getIntInput(prompt);
	}

	/* takes in prompt, returns string input */
	private String getStringInput(String prompt) {
		return inputHandler.getStringInput(prompt);
	}

	/* takes in prompt, returns validated yes/no boolean */
	private boolean getYesNoInput(String prompt) {
		String input = "";
		while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n") &&
			   !input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
			System.out.print(prompt + " (y/n): ");
			input = getStringInput("").trim();
			if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n") &&
				!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
				System.out.println("Invalid input. Please enter y or n.");
			}
		}
		return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
	}
}
