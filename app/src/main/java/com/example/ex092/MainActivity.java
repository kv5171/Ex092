package com.example.ex092;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * The type Main activity.
 *
 *  @author		Keren Weintraub <kv5171@bs.amalnet.k12.il>
 *  @version	1
 *  @since		15/11/2021
 *  short description:
 *     Calculation of a geometric / arithmetic series according to the input
 */
public class MainActivity extends AppCompatActivity {
    Intent si;
    /**
     * The Sequence type.
     */
    Switch seqType;
    /**
     * The First num.
     */
    EditText firstNum, /**
     * The Sequence d.
     */
    sequenceD;
    /**
     * The First value.
     */
    String firstValue, /**
     * The String d.
     */
    stringD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = (EditText)findViewById(R.id.ans);
        sequenceD = (EditText)findViewById(R.id.seqD);
        seqType = (Switch)findViewById(R.id.seqType);

        si = new Intent(this, ResultActivity.class);
    }

    /**
     * Get input from the text view fields and check if they are correct
     * If so - go to the Result activity, else print an error message (with toast)
     *
     * @param view the view
     */
    public void goSolver(View view) {
        firstValue = firstNum.getText().toString();
        stringD = sequenceD.getText().toString();

        if (!firstValue.equals("") && !stringD.equals("")
                && !firstValue.equals(".") && !stringD.equals(".")
                && !firstValue.equals("-") && !stringD.equals("-")
                && !firstValue.equals("-.") && !stringD.equals("-."))
        {
            si.putExtra("seqType", seqType.isChecked());
            si.putExtra("firstNum", Float.parseFloat(firstValue));
            si.putExtra("seqD", Float.parseFloat(stringD));

            startActivity(si);
        }
        else {
            Toast.makeText(this, "Error! Fill all thes values", Toast.LENGTH_SHORT).show();
        }
    }
}