package com.example.multiplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiplicationActivity extends Activity {

    private Integer multiplicand;
    private Integer multiplier;
    private ImageView resultImage;
    private EditText resultEditText;
    private ArrayList<Integer> checkedValue;
    private List<Pair<Integer, Integer>> calculations = new ArrayList<>();
    private Boolean isResolved;
    private int numberOfMistakes = 0;
    private int numberOfAllowedMistakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplication_activity);
        resultImage = findViewById(R.id.resultImage);
        resultEditText = findViewById(R.id.resultEditView);
        Intent intent = getIntent();
        checkedValue = intent.getIntegerArrayListExtra("checkedElements");
        calculations = prepareCalculations();
        numberOfAllowedMistakes = setNumberOfAllowedMistakes();
        showCalculation();

        final Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text;
                if(isResolved){
                    showCalculation();
                }
                else {
                    String enteredResult = resultEditText.getText().toString();
                    Integer result;
                    try {
                        result = Integer.valueOf(enteredResult);
                    } catch (NumberFormatException e) {
                        result = -1;
                    }
                    if (result.equals(multiplicand * multiplier)) {
                        text = getResources().getString(R.string.bravo);
                        resultImage.setImageResource(R.drawable.ok);
                    } else {
                        text = getResources().getString(R.string.wrongAnswer);
                        resultImage.setImageResource(R.drawable.bad);
                        numberOfMistakes++;
                        if (numberOfMistakes > numberOfAllowedMistakes){
                            showEnd(false);
                        }
                    }
                    checkButton.setText(getResources().getString(R.string.next));
                    resultEditText.setEnabled(false);
                    isResolved = true;
                    resultImage.setVisibility(View.VISIBLE);
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    private void showCalculation() {
        isResolved = false;
        Random random = new Random();
        if (calculations.size() > 0) {
            int index = random.nextInt(calculations.size());
            Pair pair = calculations.get(index);
            multiplicand = (Integer) pair.first;
            multiplier = (Integer) pair.second;
            TextView calculation = findViewById(R.id.calculation);
            calculation.setText(multiplicand + "*" + multiplier + "=");
            calculations.remove(pair);
            Button button = findViewById(R.id.checkButton);
            button.setText(getResources().getString(R.string.checkResult));
            resultImage.setVisibility(View.INVISIBLE);
            resultEditText.setText("");
            resultEditText.setEnabled(true);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(resultEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        } else {
            showEnd(true);
        }
    }

    private void showEnd(boolean success){
        ImageView finalResultImage = findViewById(R.id.resultImage);
        if (success){
            resultImage.setImageResource(R.drawable.puchar);
        }else {
            resultImage.setImageResource(R.drawable.loose);
        }
        finalResultImage.setVisibility(View.VISIBLE);
        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setVisibility(View.INVISIBLE);

    }

    private List<Pair<Integer, Integer>> prepareCalculations(){
        List<Pair<Integer, Integer>> calculations = new ArrayList<>();
        for (int i=0; i<checkedValue.size(); i++){
            for (int j=0; j<10; j++) {
                calculations.add(new Pair<>(checkedValue.get(i), j));
            }
        }
        return calculations;
    }

    private int setNumberOfAllowedMistakes(){
        return checkedValue.size();
    }
}
