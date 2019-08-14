package com.example.multiplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MultiplicationActivity extends Activity {

    private Integer selectedNumber;
    private Integer randomValue;
    private ImageView resultImage;
    private EditText resultEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplication_activity);
        Button checkButton = findViewById(R.id.checkButton);
        resultImage = findViewById(R.id.resultImage);
        resultEditText = findViewById(R.id.resultEditView);
        showCalculation();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = findViewById(R.id.checkButton);
                CharSequence text;
                int duration = Toast.LENGTH_LONG;
                if(button.getText().equals("Następny")){
                    showCalculation();
                }
                else {
                    String enteredResult = resultEditText.getText().toString();
                    Context context = getApplicationContext();
                    Integer result;
                    try {
                        result = Integer.valueOf(enteredResult);
                    } catch (NumberFormatException e) {
                        result = -1;
                    }
                    if (result.equals(selectedNumber * randomValue)) {
                        text = "Brawo Ty!";
                        resultImage.setImageResource(R.drawable.ok);
                        button.setText("Następny");
                        resultEditText.setEnabled(false);
                    } else {
                        text = "Niestety nie";
                        resultImage.setImageResource(R.drawable.bad);
                    }
                    resultImage.setVisibility(View.VISIBLE);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

    }

    private void showCalculation() {
        Intent intent = getIntent();
        ArrayList<Integer> checkedValue = intent.getIntegerArrayListExtra("checkedElements");
        Random random = new Random();
        int index = random.nextInt(checkedValue.size());
        selectedNumber = checkedValue.get(index);
        randomValue = random.nextInt(10) + 1;
        TextView calculation = findViewById(R.id.calculation);
        calculation.setText(selectedNumber + "*" + randomValue + "=");
        Button button = findViewById(R.id.checkButton);
        button.setText("Sprawdź");
        resultImage.setVisibility(View.INVISIBLE);
        resultEditText.setText("");
        resultEditText.setEnabled(true);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(resultEditText, InputMethodManager.SHOW_IMPLICIT);
        }


    }
}
