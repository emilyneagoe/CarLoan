package com.example.carloan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView carCost;
    private TextView downPayment;
    private TextView percRate;
    private TextView loanLength;
    private TextView payDisplay;
    private SeekBar monthBar;
    private RadioButton loanRadio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carCost = findViewById(R.id.carCost);
        downPayment = findViewById(R.id.downPayment);
        percRate = findViewById(R.id.percRate);
        loanLength = findViewById(R.id.loanLength);
        payDisplay = findViewById(R.id.payDisplay);
        monthBar = findViewById(R.id.monthBar);
        loanRadio = findViewById(R.id.loanRadio);



        monthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                loanLength.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


        public void calculate(View v) {
            String carCostString = carCost.getText().toString();
            String downPaymentString = downPayment.getText().toString();
            String percRateString = percRate.getText().toString();
            String loanLengthString = loanLength.getText().toString();

            if (carCostString.length()>0) {
                 double carCost = Double.parseDouble(carCostString);
                 if(downPaymentString.length()>0) {
                     double downPayment = Double.parseDouble(downPaymentString);
                     if (percRateString.length()>0) {
                         double percRate = Double.parseDouble(percRateString);
                         if (loanRadio.isChecked()) {
                            double loanLength = Double.parseDouble(loanLengthString);
                            double mr = (percRate / 100) / 12;
                            double L = carCost - downPayment;
                            double N = loanLength;
                            double P = mr * L / (1 - (Math.pow(1 + mr, -N)));

                            payDisplay.setText(String.format("%.2f", P));

                        } else {
                            monthBar.setProgress(36); 
                            double mr = (percRate / 100) / 12;
                            double L = (carCost / 3) - downPayment;
                            double N = 36;
                            double P = mr * L / (1 - (Math.pow(1 + mr, -N)));


                            payDisplay.setText(String.format("%.2f", P));

                        }
                     } else {
                         payDisplay.setText("");
                         Toast.makeText(this, "No APR Value Entered", Toast.LENGTH_SHORT).show();
                     }
                 } else {
                     payDisplay.setText("");
                     Toast.makeText(this, "No Down Payment Value Entered", Toast.LENGTH_SHORT).show();
                 }
            } else {
                payDisplay.setText("");
                Toast.makeText(this, "No Car Cost Value Entered", Toast.LENGTH_SHORT).show();
            }
        }
    }

