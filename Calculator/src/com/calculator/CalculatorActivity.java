package com.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
	private EditText firstNumber, secondNumber;
	private TextView total;
	private Button addButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		firstNumber = (EditText) findViewById(R.id.firstNumber);
		secondNumber = (EditText) findViewById(R.id.secondNumber);
		addButton = (Button) findViewById(R.id.addButton);
		total = (TextView) findViewById(R.id.total);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String firstNumberResult = firstNumber.getText().toString();
				String secondNumberResult = secondNumber.getText().toString();
				Integer totalResult = Integer.parseInt(firstNumberResult)
						+ Integer.parseInt(secondNumberResult);
				total.setText(Integer.toString(totalResult));
			}
		});

	}
}