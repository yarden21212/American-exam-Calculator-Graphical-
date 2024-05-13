/*
 * Calculator class represents a mathematical calculator with basic operations as +, -, *, /, =, +/-, . and C
 * This class build with code and graphic in order to give the best experience
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
public class CalculatorController {
	
	//The height and width of the grid, the rows and columns we organize the buttons
	private final int HEIGHT = 6, WIDTH = 3;
	//Keeps the value of the previous and current numbers/values and the total result until the current's step
	private Double prev, curr, result;
	private boolean isPositive, operationPressed, dotPressed, prevIsEmpty;
	private String currResult, prevResult, calculations;
	private Button[][] btns;
	private String[][] calcButtons;
	private String operation;

    @FXML
    
    private GridPane grid;

    @FXML
    private Label resultLabel;

    /*
     * The buttons will be inserted into a gridPane.
     * This way we can implement the calculator graphically.
     */
    private void initializeButtonsIntoGrid() {
    	
    	btns = new Button[HEIGHT][WIDTH];
    	
    	for(int i = 0; i < HEIGHT; i++) {
    		for(int j = 0; j < WIDTH; j++) {
    			
    			//We don't initialize the last grid's cell, it will remain empty
    			if(!(i == HEIGHT && j == WIDTH)) {
 
    				btns[i][j] = new Button(calcButtons[i][j]);
        			btns[i][j].setPrefSize((grid.getPrefWidth() / WIDTH),(grid.getPrefHeight() / HEIGHT));
        			grid.add(btns[i][j], j, i);
        			btns[i][j].setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							
							buttonsHandler(event);
							
						}
					});
    			}	
    		}
    	}
    }
    
    private void buttonsHandler(ActionEvent event) {
    	
    	Button button = (Button)event.getSource();
    	String btnValue = button.getText();
    	
    	System.out.println(btnValue);

    	checkValueInButton(btnValue);
    }
    /*
     * Checks the text's value inside the button pressed
     */
    private void checkValueInButton(String btnValue) {
    	
    	//Show the current result
    	if(btnValue == "=") {
    		if(!prevWasOpearation()) {
    			equalButtonPressed(btnValue);
    		}
    		else {
    			//resultLabel.setText(itsError());
    			itsError();
    		}
    	}
    	else if(btnValue.compareTo("0")>=0 && btnValue.compareTo("9")<= 0) {
    		numberButtonPressed(btnValue);
    	}
    	else if(btnValue == "+/-") {
    		changeSignPressed();
    	}
    	else if(btnValue.compareTo(".") == 0) {
    		dotPressed(btnValue);
    	}
    	else if(btnValue.compareTo("+") == 0 || btnValue.compareTo("-") == 0 || btnValue.compareTo("*") == 0 || btnValue.compareTo("/") == 0) {
    		operationPressed(btnValue);
    	}	
    	else if(btnValue.compareTo("C") == 0) {
    	
    		reset();
    		editLabel("0.0");
    	}
    }

    private void itsError() {
    	resultLabel.setText("ERROR!");
    	reset();
    }
    private void numberButtonPressed(String btnValue) {
    	
    	dotPressed = false;
    	
      	//If the current's number is 0 and we add another 0, the number will remain 0
    	if(btnValue.equals("0")){
    		//If we didn't insert any value yet, so the result will remain 0
    		if(isCurrEmpty() && prevIsEmpty) {
    			editLabel("0.0");
    		}
    		//The current's number include at the beginning number/numbers that different from 0
    		else {
    			currResult += btnValue;
    			calculations += btnValue;
    			editLabel(calculations);
    		}
    	}
    	else{
    		//The previous operation was "+/-"
    		if(!isPositive) {
    			
    			currResult = "-";
    			isPositive = true;
    			calculations += currResult;
    		}
    		currResult += btnValue;
    		calculations += "" + btnValue;
    		editLabel(calculations);
    	}
    }
    /*
     * Implementation of the "+/-" operation
     */
    private void changeSignPressed() {
    	/*
    	 * If there is only 1 value and then operation like: "1 +" and then we press +/- 
    	 * Form: "value1 (operaion)"
    	 */
    	if(!operation.equals("") && isCurrEmpty() && !prevResult.equals("")) {
    		operation = "";
    		curr = -1 * Double.parseDouble(prevResult);
    		calculations = curr.toString();
    		currResult = calculations; 
    		editLabel(calculations);
    		prev = 0.0;
    		prevResult = "";
    	}
    	//If the form is: "value1 (operation) value2"
    	else if(operation.equals("") && !isCurrEmpty()) {
    		operation = "";
    		curr = -1 * Double.parseDouble(currResult);
    		calculations = curr.toString();
    		currResult = calculations; 
    		editLabel(calculations);
    	}
    	else {
    		itsError();
    	}
    }
    private void dotPressed(String btnValue) {
    	//It's already a decimal number, we can't add another dot
    	if(dotPressed == true) {
    		itsError();
    	}
    	else {
    		//If the current word is empty, we will consider it as 0, so after pressing "." we will get the decimal number 0.0
        	if(isCurrEmpty()) {
        		currResult = "0.0";
        		editLabel(currResult);
        	}
        	/*
        	 * If the current value is in the form of "0.x" then "0." will be placed
        	 * to represent a decimal number and then the new buttom's value
        	 */
        	else if(currResult.compareTo("0.0") == 0){
        		currResult = "0." + btnValue;
        	}
        	else {
        		currResult += btnValue;
        	}
        	
        	calculations += btnValue;
    		editLabel(calculations);
        	dotPressed = true;
    	}
    }
    /*
     * Checks if currResult field is empty (holds the second number)
     */
    private boolean isCurrEmpty() {
    	if((currResult.compareTo("") == 0)) {
    		return true;
    	}
    	
    	return false;
    }
    /*
     * Checks if in the previous step an operation's button was pressed 
     * so in the current's step we can't press an operation again
     */
    private boolean prevWasOpearation() {
    	
    	return operationPressed;
    }
    /*
     * Updating the calculation bar, the label where the user can see the total calculation
     */
    private void editLabel(String text) {
    	
    	resultLabel.setText(text);
    }
    /*
     * If the user pressed an operation button (+, -, *, /) then the functionality happens here
     */
    private void operationPressed(String operationButton) {
    	//Operation pressed before inserting numbers, it's illegal
    	if(isCurrEmpty() && prevIsEmpty) {
    		itsError();
    	}
    	//We stop inserting numbers and we want to do an operation, so we will check if we have 2 different values to do operation with.
    	else {
    		prevIsEmpty = false;
    		dotPressed = false;
    		
    		//We have only 1 value
    		if(prevResult.equals("")) {
    			prevResult = currResult;
    			currResult = "";
    			//Initializing the first operation that appeared (in case we do 2 operations before pressing equals button)
    			operation = operationButton;
    			calculations = prevResult + operationButton;
    			editLabel(calculations);
    		}
    		//We have 2 values
    		else {
    			/*
    			 * In case the user pressed on 2 different operations 
    			 * but he doesn't have 2 different values to apply the first operation on
    			 */
    			if(prevResult.equals("") || currResult.equals("")) {
    				itsError();
    			}
    			else {
    				Double tempDoublePrev = Double.parseDouble(prevResult);
        			Double tempDoubleCurr = Double.parseDouble(currResult);
        			Double result = null;
        			
        			boolean legal = checkDivisionLegal(tempDoubleCurr);
        			if(operation.equals("/") && !legal) {
        				editLabel("ERROR");
        				result = tempDoublePrev;
        			}
        			else {
        				result = applyOperator(tempDoublePrev, tempDoubleCurr);
            			prevResult = result + "";	
            			prev = result;
            			prevIsEmpty = false;
            			currResult = "";
            			curr = 0.0;
            			
            			operation = operationButton;
            			calculations = result + operationButton +"";
            			editLabel(calculations);
        			}	
    			}
    			
    		}
    	}
    }
    private double applyOperator(Double prevVal, Double currVal){
    	if(operation.compareTo("+") == 0) {
    		return prevVal + currVal;
    	}
    	else if(operation.compareTo("-") == 0) {
    		return prevVal - currVal;
    	}
    	else if(operation.compareTo("*") == 0) {
    		return prevVal * currVal;
    	}
    	else{
    		return prevVal / currVal;
    		
    	}
    }
    /*
     * If the user pressed the equal button "=", the functionality happens here
     */
    private void equalButtonPressed(String btnValue) {
    	
    	if(prevResult.equals("")) {
    		if(currResult.equals("")) {
    			System.out.println("here!11");
    			result = 0.0;
    		}
    		else {
    			curr = Double.parseDouble(currResult);
    			result = curr;
    		}
    	}
    	else {;
    		prev = Double.parseDouble(prevResult);
    		curr = Double.parseDouble(currResult);
    		boolean legal = checkDivisionLegal(curr);
    		if(operation.equals("/") && !legal) {
    			editLabel("ERROR");
    			reset();
    		}
    		else {
    			Double operationResult = applyOperator(prev, curr);
    			result = operationResult;
    			editLabel(result + "");
    			reset();
    			curr = operationResult;
    			currResult = curr.toString();
    			
    		}	
    	}	
    }
    /*
     * If the user press "C" or gets an "ERROR" or any other action that requires to reset the calculations
     */
    private void reset() {
    	currResult = "";
		prevResult = "";
		result = 0.0;
		prev = 0.0;
		curr = 0.0;
		calculations = "";
    	operation = "";
    }
    /*
     * Checks if the division was legal, for example number divided by zero is illegal
     */
    private boolean checkDivisionLegal(Double currValue) {
    	
    	if(currValue == 0) {
    		return false;
    	}
    	return true;
    }
    public void initialize() {
    	
    	//Checks if the button "+/-" was pressed in order to change the sign.
    	isPositive = true;
    	//Checks if any operation was pressed, if so, we will act accordingly
    	operationPressed = false;
    	//Checks if the current number is already includes "." (decimal operation), if so, so we can't add another one.
    	dotPressed = false;
    	//Checks if we already have a number so we can add it an operation with a second one
    	prevIsEmpty = true;
    	
    	calcButtons = new String[][]{{"1", "2", "3"}
    								,{"4", "5", "6"}
    								,{"7", "8", "9"}
    								,{"0", "=", "+/-"}
    								,{".", "/", "*"}
    								,{"+", "-", "C"}};
    								
    	initializeButtonsIntoGrid();
    	
    	reset();
    	resultLabel.setStyle("-fx-text-fill: white; -fx-background-color: black");
    	resultLabel.setText("0.0");
    	
    	
    }
   
}
