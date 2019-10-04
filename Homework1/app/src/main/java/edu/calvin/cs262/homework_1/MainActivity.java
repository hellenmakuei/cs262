package edu.calvin.cs262.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.calvin.cs262.homework1.R;

public class MainActivity extends AppCompatActivity {
 private Button addition, subtraction, multipication, division;
 private EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addition = (Button) findViewById(R.id.addbutton);
        subtraction = (Button)findViewById(R.id.SubButton);
        multipication = (Button)findViewById(R.id.multButton);
        division = (Button)findViewById(R.id.divbutton);

        et1 = (EditText)findViewById(R.id.EditTextet1);
        et2 = (EditText)findViewById(R.id.EditTextet2);
        et3 = (EditText)findViewById(R.id.EditTextet3);

        addition.setOnClickListener(new View.OnClickListener()
        {

            @Override

            public void onClick (View v){

            if (et1.getText().toString().equals("") || et2.getText().toString().equals("")) {
                Toast.makeText(MainActivity.this, "please rnter numbers.", Toast.LENGTH_SHORT).show();
            } else {
                et1.getText().toString();
                et2.getText().toString();
                double a1 = Double.valueOf(et1.getText().toString());
                double a2 = Double.valueOf(et2.getText().toString());
                double a3;
                a3 = a1 + a2;
                et3.setText(String.valueOf(a3));
            }
        }});

            subtraction.setOnClickListener(new View.OnClickListener(){

                @Override

                public void onClick(View v) {
                    if (et1.getText().toString().equals("") || et2.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "please enter the number...", Toast.LENGTH_SHORT).show();
                    } else {
                        et1.getText().toString();
                        et2.getText().toString();
                        double a1 = Double.valueOf(et1.getText().toString());
                        double a2 = Double.valueOf(et2.getText().toString());
                        double a3;
                        a3 = a1 - a2;
                        et3.setText(String.valueOf(a3));
                    }


                }
            });

            multipication.setOnClickListener(new View.OnClickListener(){

                @Override

                public void onClick(View v) {
                    if (et1.getText().toString().equals("") || et2.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "please enter number", Toast.LENGTH_SHORT).show();
                    } else {
                        et1.getText().toString();
                        et2.getText().toString();
                        double a1 = Double.valueOf(et1.getText().toString());
                        double a2 = Double.valueOf(et2.getText().toString());
                        double a3;
                        a3 = a1 * a2;
                        et3.setText(String.valueOf(a3));
                    }


                }
            });

            division.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    if (et1.getText().toString().equals("") || et2.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "please Enter number...", Toast.LENGTH_SHORT).show();
                    } else {
                        et1.getText().toString();
                        et2.getText().toString();
                        double a1 = Double.valueOf(et1.getText().toString());
                        double a2 = Double.valueOf(et2.getText().toString());
                        double a3;
                        a3 = a1 / a2;
                        et3.setText(String.valueOf(a3));
                    }


                }
            });
        }

        }

