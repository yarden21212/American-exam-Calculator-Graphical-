/*
 * AmericanExamController is the "main class" of the project.
 * Creates objects of questions and build a quiz with them.
 * This class includes a GUI to make the quiz more as a game and less as a text
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AmericanExamController {

	//To hold information of Quiz's object and apply functions over it.
	private Quiz quiz;
	//To hold information of Quiestion's object and apply functions over it.
	private Question question;
	//Count the correct and total-answers so the user will get a result in the end of the game.
	private double totalAnswers, correctAnswers;
	
    @FXML
    private TextField questionTextField;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    /*
     * If the user wants to end the game or to start again
     */
    @FXML
    void finishPressed(ActionEvent event) throws FileNotFoundException {
    	
    	gameOver();
    }

    /*
     * To move on to the next question after the user decided his current's answer
     * Checks which Radio button was chosen and take it's information (text).
     */
    @FXML
    void nextPressed(ActionEvent event) throws FileNotFoundException {
    	if(radio1.isSelected() == true) {
    		if(isCorrect(radio1.getText(), question.getCorrectAnswer())) {
    			correctAnswers++;
    		}
    	}
    	else if(radio2.isSelected() == true) {
    		if(isCorrect(radio2.getText(), question.getCorrectAnswer())) {
    			correctAnswers++;
    		}	
    	}
    	else if(radio3.isSelected() == true) {
    		if(isCorrect(radio3.getText(), question.getCorrectAnswer())) {
    			correctAnswers++;
    		}
    	}
    	else if(radio4.isSelected() == true) {
    		if(isCorrect(radio4.getText(), question.getCorrectAnswer())) {
    			correctAnswers++;
    		}
    	}
    	
    	quiz.deleteQuestion();
    	nextQuestion();
    	radio1.setSelected(true);
    }
    /*
     * Checks if the curren't answer the user chose is the right one between the 4.
     */
    private boolean isCorrect(String currAnswer, String correctAnswer) {
    	
    	return currAnswer.equals(correctAnswer);
    }
    /*
     * Generates a random question from the questions' bank
     */
    private Question randomQuestion(Quiz quiz) {
    	
    	Question question = quiz.getRandomQuestion();
    	return question;
    }
    /*
     * Generates a new question and places it on the top of the window
     */
    private void showQuestion(Question question) {
    	
    	questionTextField.setText(question.getQuestion());
    }
    /*
     * Shuffle the 4 different answers so the right answer won't be in the same place every turn.
     */
    private ArrayList<String> shuffleAnswers(Question question, ArrayList<String> answers) {
  
		answers.add(question.getCorrectAnswer());
		answers.addAll(question.getIncorrectAnswers());
		Collections.shuffle(answers);
		
		return answers;
    }
    /*
     * Set the text of the Radio button to a question we want the user to answer.
     */
    private void setRadioText(RadioButton radio, ArrayList<String> answers, int index) {
    	radio.setText(answers.get(index));
    }
    /*
     * Moves on to the next question
     */
    private void nextQuestion() throws FileNotFoundException {
    	if(quiz.isEmpty()) {
    		gameOver();
    	}
    	else {
	    	question = randomQuestion(quiz);
	    	showQuestion(question);
	    	ArrayList<String> answers = new ArrayList<>();
			shuffleAnswers(question, answers);
			
			setRadioText(radio1, answers, 0);
			setRadioText(radio2, answers, 1);
			setRadioText(radio3, answers, 2);
			setRadioText(radio4, answers, 3);
	    }
    }
    /*
     * Ending the game in 2 cases:
     * (1) The user answered all of the questions in the bank and he got his results.
     * (2) The user pressed the "Finish Exam" button.
     */
    private void gameOver() throws FileNotFoundException {
    	double precent = (correctAnswers / totalAnswers) * 100;
    	String result = precent +  "%";
    	new Alert(Alert.AlertType.INFORMATION, "Game over, your result is: " + result).showAndWait();
    	
    	initialize();//Starts a new game
    }
    /*
     * Creates the game using the different methods and uses an independent external text file.
     */
    public void initialize() throws FileNotFoundException {
		
    	FileDatabase fileData = new FileDatabase("quiz.txt");

    	quiz = new Quiz(fileData);
    	totalAnswers = fileData.getQuestionsAmount();
		correctAnswers = 0;
		nextQuestion();
	}
}
