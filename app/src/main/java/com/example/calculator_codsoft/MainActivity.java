package com.example.calculator_codsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    TextView tvRes, tvExp;
    MaterialButton bAC, bOpenCloseBracket, bPercent;
    MaterialButton bDivide, bMultiply, bAdd, bSubtract, bEquals;
    MaterialButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    MaterialButton bDot, bBackSpace;
    char sym = '(', op;
    String expression = "";
    double res, num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRes = findViewById(R.id.resultTV);
        tvExp = findViewById(R.id.expressionTV);

        bAC = findViewById(R.id.buttonAC);
        bOpenCloseBracket = findViewById(R.id.buttonOCBracket);
        bPercent = findViewById(R.id.buttonPer);
        bDivide = findViewById(R.id.buttonDiv);
        bMultiply = findViewById(R.id.buttonMul);
        bAdd = findViewById(R.id.buttonAdd);
        bSubtract = findViewById(R.id.buttonSub);
        bEquals = findViewById(R.id.buttonEq);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        bDot = findViewById(R.id.buttonDot);
        bBackSpace = findViewById(R.id.buttonBS);

        bAC.setOnClickListener(view -> clearCalculator());
        bOpenCloseBracket.setOnClickListener(view -> openCloseBrackets());


        bPercent.setOnClickListener(view -> setOperator('%'));
        bDivide.setOnClickListener(view -> setOperator('/'));
        bMultiply.setOnClickListener(view -> setOperator('*'));
        bAdd.setOnClickListener(view -> setOperator('+'));
        bSubtract.setOnClickListener(view -> setOperator('-'));

        bEquals.setOnClickListener(view -> performCalculation());


        b0.setOnClickListener(view -> appendDigit("0"));
        b1.setOnClickListener(view -> appendDigit("1"));
        b2.setOnClickListener(view -> appendDigit("2"));
        b3.setOnClickListener(view -> appendDigit("3"));
        b4.setOnClickListener(view -> appendDigit("4"));
        b5.setOnClickListener(view -> appendDigit("5"));
        b6.setOnClickListener(view -> appendDigit("6"));
        b7.setOnClickListener(view -> appendDigit("7"));
        b8.setOnClickListener(view -> appendDigit("8"));
        b9.setOnClickListener(view -> appendDigit("9"));

        bDot.setOnClickListener(view -> appendDigit("."));
        bBackSpace.setOnClickListener(view -> backSpace());
    }

    private void appendDigit(String digit){
        tvExp.setText(tvExp.getText().toString() + digit);
    }

    private void setOperator(char operator){
        if (tvExp.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Number", Toast.LENGTH_SHORT).show();
            return;
        }
        op = operator;
        res = Double.parseDouble(tvExp.getText().toString());
        tvExp.setText("");
    }

    private void openCloseBrackets(){
        if(sym == '(') {
            expression += "(";
            tvExp.setText(expression);
            sym = ')';
        }
         else if(sym == ')') {
            expression += ")";
            tvExp.setText(expression);
            sym = '(';
        }
    }

    private void performCalculation() {
        if (op == 'o') {
            Toast.makeText(getApplicationContext(), "Please Select an Operator", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tvExp.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Number", Toast.LENGTH_SHORT).show();
            return;
        }

        num = Double.parseDouble(tvExp.getText().toString());
        double result = 0.0;

        switch (op) {
            case '+':
                result = res + num;
                break;
            case '-':
                result = res - num;
                break;
            case '*':
                result = res * num;
                break;
            case '%':
                result = (res / 100) * num;
                break;
            case '/':
                if (num == 0) {
                    Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    result = res / num;
                }
                break;
        }

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            Toast.makeText(getApplicationContext(), "Result is too large or invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (result == (long) result) {
            tvRes.setText(String.format("%.0f", result));
        } else {
            tvRes.setText(String.format("%.4f", result));
        }
        tvExp.setText("");
    }


    private void backSpace(){
        if (tvExp.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Number", Toast.LENGTH_SHORT).show();
            return;
        }
        expression = tvExp.getText().toString();
        expression = expression.substring(0, expression.length() - 1);
        tvExp.setText(expression);
    }

    private void clearCalculator(){
        tvExp.setText("");
        res = 0.0;
        num = 0.0;
        op = 'o';
        tvRes.setText("");
    }
}