package com.jdc.calc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Calculator implements Initializable{
	
	@FXML
	private Label tempResult;
	@FXML
	private Label result;
	@FXML
	private GridPane grid;

	public void close(ActionEvent e) {
		result.getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		grid.getChildren().forEach(node -> {
			Button b = (Button)node;
			b.setOnAction(a -> {
				try {
					buttonClick(b.getText());
				} catch(ArithmeticException e) {
					System.err.println(e.getMessage());
				}
			});
		});
		
		clear();
	}

	private void buttonClick(String text) {
		switch (text) {
		case "CE":
			clear();
			break;
		case "=":
			calculate();
			break;
		case "+":
		case "-":
		case "*":
		case "/":
			calculate(text);
			break;

		default:
			pressNumber(text);
			break;
		}
	}

	private void calculate(String text) {
		String tmp1 = result.getText();
		String tmp2 = tempResult.getText();
		
		if(tmp2.isEmpty()) {

			tempResult.setText(tmp1 + " " + text);
			result.setText("0");
			
		} else {
			String [] array = tmp2.split(" ");
			String r = doCalculate(array[0], tmp1, array[1]);
			tempResult.setText(r + " " + text);
			result.setText("0");
		}
		
	}

	private String doCalculate(String data1, String data2, String operater) {
		
		int d1 = Integer.parseInt(data1);
		int d2 = Integer.parseInt(data2);
		int result = 0;
		
		switch (operater) {
		case "+":
			result = d1 + d2;
			break;
		case "-":
			result = d1 - d2;
			break;
		case "*":
			result = d1 * d2;
			break;
		case "/":
			result = d1 / d2;
			break;

		default:
			break;
		}
		return String.valueOf(result);
	}

	private void pressNumber(String text) {
		String tmp = result.getText();
		
		if(tmp.equals("0")) {
			result.setText(text);
		} else {
			result.setText(tmp + text);
		}
	}

	private void calculate() {
		String tmp1 = result.getText();
		String tmp2 = tempResult.getText();
		
		if(!tmp2.isEmpty()) {
			String [] array = tmp2.split(" ");
			String r = doCalculate(array[0], tmp1, array[1]);
			tempResult.setText("");
			result.setText(r);
		}
	}

	private void clear() {
		tempResult.setText("");
		result.setText("0");
	}

}
