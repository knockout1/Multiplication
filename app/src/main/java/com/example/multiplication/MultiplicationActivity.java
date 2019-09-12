package com.example.multiplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MultiplicationActivity extends Activity {

    private ImageView resultImage;
    private EditText resultEditText;
    private TextView calculationsNumberTextView;
    private TextView mistakeNumberTextView;
    private TextView timer;
    private Button checkButton;
    private int timerCount = 15;
    private Game game;
    private TextView calculationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplication_activity);
        setResources();
        Intent intent = getIntent();
        game = new Game(new Calculations(intent.getIntegerArrayListExtra("checkedElements")));
        game.prepareCalculation();
        showCalculation();

//TODO: refacotr this
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text;
                if (game.isResolved) {
                    game.prepareCalculation();
                    showCalculation();
                } else {
                    String enteredResult = resultEditText.getText().toString();
                    Integer result;
                    try {
                        result = Integer.valueOf(enteredResult);
                    } catch (NumberFormatException e) {
                        result = -1;
                    }

                    if (game.checkProvidedAnswer(result)) {
                        text = getResources().getString(R.string.bravo);
                        resultImage.setImageResource(R.drawable.ok);
                    } else {
                        text = getResources().getString(R.string.wrongAnswer);
                        resultImage.setImageResource(R.drawable.bad);
                        if (game.checkIfEndGame()) {
                            game.endGame(false);
                            showEnd(false);
                        }
                    }
                    checkButton.setText(getResources().getString(R.string.next));
                    resultEditText.setEnabled(false);
                    resultImage.setVisibility(View.VISIBLE);
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void setResources() {
        resultImage = findViewById(R.id.resultImage);
        resultEditText = findViewById(R.id.resultEditView);
        calculationsNumberTextView = findViewById(R.id.calculationsCounter);
        mistakeNumberTextView = findViewById(R.id.mistakesCounter);
        timer = findViewById(R.id.timer);
        checkButton = findViewById(R.id.checkButton);
        calculationTextView = findViewById(R.id.calculation);
    }


    private void showCalculation() {
        calculationTextView.setText(game.getCurrentCalculation().getMultiplicand() + "*" + game.getCurrentCalculation().getMultiplier() + "=");
        Button button = findViewById(R.id.checkButton);
        button.setText(getResources().getString(R.string.checkResult));
        resultImage.setVisibility(View.INVISIBLE);
        resultEditText.setText("");
        resultEditText.setEnabled(true);
        calculationsNumberTextView.setText(getResources().getString(R.string.taskNumber) + " " + game.getCurrentCalculationNumber() + "/" + game.getCurrentCalculation().getNumberOfTasksToResolve());
        mistakeNumberTextView.setText(getResources().getString(R.string.mistakeNumber) + " " + game.getNumberOfMistakes() + "/" + game.getNumberOfAllowedMistakes());
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(resultEditText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void startTimer() {
        timerCount = 15;
        new CountDownTimer(17000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(timerCount--));
            }

            public void onFinish() {
            }
        }.start();
    }

    private void showEnd(boolean success) {
        ImageView finalResultImage = findViewById(R.id.resultImage);
        if (success) {
            resultImage.setImageResource(R.drawable.puchar);
        } else {
            resultImage.setImageResource(R.drawable.loose);
        }
        finalResultImage.setVisibility(View.VISIBLE);
        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setVisibility(View.INVISIBLE);

    }

}
