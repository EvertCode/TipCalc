package com.evertcode.calctip.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evertcode.calctip.CalcTipAplication;
import com.evertcode.calctip.R;
import com.evertcode.calctip.bean.TipRecord;
import com.evertcode.calctip.fragments.TipHistoryListFragment;
import com.evertcode.calctip.fragments.TipHistoryListFragmentListener;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputBill)
    EditText inputBill;

    @BindView(R.id.inputPercentage)
    EditText inputPercentage;

    @BindView(R.id.txtTip)
    TextView txtTip;

    @BindView(R.id.totalPerson)
    TextView totalPerson;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHEGE = 1;
    private final static int DEFAULT_PERSON = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TipHistoryListFragment tipHistoryListFragment = (TipHistoryListFragment)getSupportFragmentManager().findFragmentById(R.id.frameList);
        tipHistoryListFragment.setRetainInstance(true);

        fragmentListener = (TipHistoryListFragmentListener)tipHistoryListFragment;

        this.totalPerson.setText(String.valueOf(DEFAULT_PERSON));
        this.inputPercentage.setText(String.valueOf(DEFAULT_TIP_PERCENTAGE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_about){
            this.about();
        }
        return super.onOptionsItemSelected(item);
    }

    private void about(){
        final CalcTipAplication app =(CalcTipAplication) getApplication();
        final String strUrl = app.getAboutUrl();

        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);


    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit(){
        this.hideKeyBoard();

        final String strInputTotal = this.inputBill.getText().toString().trim();

        String strInputPerson = this.totalPerson.getText().toString().trim();

        if(strInputPerson.isEmpty()){
            this.totalPerson.setText(String.valueOf(DEFAULT_PERSON));
            strInputPerson = String.valueOf(DEFAULT_PERSON);
        }

        if(!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int person = Integer.parseInt(strInputPerson);

            TipRecord tipRecord = new TipRecord();
            tipRecord.setSubTotal(total);
            tipRecord.setPerson(person);
            //tipRecord.setBill(total);
            tipRecord.setTipPercentage(getTipPorcentage());
            tipRecord.setTimestamp(new Date());





            String strTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());
            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        this.hideKeyBoard();
        handleTipChange(TIP_STEP_CHEGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        this.hideKeyBoard();
        handleTipChange(-TIP_STEP_CHEGE);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        fragmentListener.clearList();
    }

    private void handleTipChange(int change) {
        int tipPercentage = getTipPorcentage();
        tipPercentage += change;
        if(tipPercentage >0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    private int getTipPorcentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        if(!strInputTipPercentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }else{
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    private void hideKeyBoard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }catch(final Exception e){
            Log.e(getLocalClassName(), Log.getStackTraceString(e));
        }

    }


}
