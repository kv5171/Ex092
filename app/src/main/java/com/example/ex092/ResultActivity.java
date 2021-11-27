package com.example.ex092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * The type Result activity.
 *
 *  @author		Keren Weintraub <kv5171@bs.amalnet.k12.il>
 *  @version	1
 *  @since		15/11/2021
 *  short description:
 *     Calculates the data and shows the result
 */
public class ResultActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    Intent gi;
    /**
     * The Answer.
     */
    TextView ans;
    /**
     * The Sequence list.
     */
    ListView seqList;
    /**
     * The First number.
     */
    float firstNumber, /**
     * The Num d.
     */
    numD, /**
     * The Sequence sum.
     */
    seqSum;
    /**
     * The Sequence type - Geometric sequence / Arithmetic sequence .
     */
    boolean seqType;
    /**
     * The Sequence arr.
     */
    String[] seqArr;
    /**
     * The Sum values arr.
     */
    float[] sumValuesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ans = (TextView)findViewById(R.id.ans);

        seqList = (ListView)findViewById(R.id.seqList);

        seqSum = 0;
        seqArr = new String[20];
        sumValuesArr = new float[20];

        gi = getIntent();
        seqType = gi.getBooleanExtra("seqType", false);
        firstNumber = gi.getFloatExtra("firstNum", 0);
        numD = gi.getFloatExtra("seqD", 0);

        calcArrValues();

        seqList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        seqList.setOnCreateContextMenuListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, seqArr);
        seqList.setAdapter(adp);
    }

    /**
     * Return to Main activity.
     *
     * @param view the view
     */
    public void returnMain(View view) {
        finish();
    }

    /**
     * Calculate arr values.
     */
    public void calcArrValues(){
        for (int i = 0; i < 20; i++){
            if (seqType){
                seqArr[i] = fixValue(firstNumber + i * numD);
            }
            else {
                seqArr[i] = fixValue((float) (firstNumber * Math.pow(numD, i)));
            }
            seqSum += Float.parseFloat(seqArr[i]);
            sumValuesArr[i] = seqSum;
        }
    }

    /**
     * Fix value string.
     *
     * @param value the value
     * @return the string
     */
    public String fixValue(float value){
        if ((float)((int)value) == value) {
            return String.valueOf((int)value);
        }
        return String.valueOf(value);
    }

    /**
     * Create a Context Menu
     *
     * @param menu the menu
     * @param v the view
     * @param menuInfo the menu Info
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("More Options");
        menu.add("position");
        menu.add("sum");
    }

    /**
     * View the position or sum by the item
     *
     * @param item the item of menu
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String option = item.getTitle().toString();
        if (option.equals("position"))
        {
            ans.setText((menuInfo.position + 1) + "");
            return true;
        }
        if (option.equals("sum"))
        {
            ans.setText(fixValue(sumValuesArr[menuInfo.position]));
            return true;
        }
        return super.onContextItemSelected(item);
    }
}