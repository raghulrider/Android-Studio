package com.raghulrider.androidworkshop;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    private EditText firstinp, secondinp, name;
    private TextView output,disptext;
    private RadioGroup radioGroup;
    ActionBar actionBar;
    Animation downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Calculator");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.name);
        radioGroup = findViewById(R.id.radioGroup);
        disptext = findViewById(R.id.displaytext);
        firstinp = findViewById(R.id.firstinput);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        secondinp = findViewById(R.id.secondinput);
        output = findViewById(R.id.answertext);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int first=Integer.parseInt(firstinp.getText().toString());
                int second=Integer.parseInt(secondinp.getText().toString());
                switch (checkedId){
                    case R.id.addition:
                        int add =first+second;
                        disptext.setText("Hey, " + name.getText().toString());
                        output.setText("Addtiton of "+ first +" + "+second +" = " + add);
                        output.setAnimation(downtoup);
                        break;
                    case R.id.subtraction:
                        int sub=first-second;
                        disptext.setText("Hey, " + name.getText().toString());
                        output.setText("Subtraction of "+ first +" - "+second +" = " + sub);
                        output.setAnimation(downtoup);
                        break;
                    case R.id.multiplication:

                        int mul =first * second;
                        disptext.setText("Hey, " + name.getText().toString());
                        output.setText("Multiplication of "+ first +" x "+second +" = " + mul);
                        output.setAnimation(downtoup);
                        break;
                    case R.id.division:
                        int div = first / second;
                        disptext.setText("Hey, " + name.getText().toString());
                        output.setText("Division of "+ first +" / "+second +" = " + div);
                        output.setAnimation(downtoup);
                        break;
                }
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
