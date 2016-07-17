package com.evertcode.calctip.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.evertcode.calctip.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipDetailActivity extends AppCompatActivity {

    @BindView(R.id.txtBillTotal)
    TextView txtBillTotal;

    @BindView(R.id.txtTip)
    TextView txtTip;

    @BindView(R.id.txtTimeStamp)
    TextView txtTimeStamp;

    public final static String TIP_KEY = "tip";
    public final static String DATE_KEY ="timestamp";
    public final static String BILL_SUB_TOTAL_KEY ="sub";
    //public final static String BILL_TOTAL_KEY ="total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String strSubTotal = String.format(getString(R.string.tipdetail_message_tip), intent.getDoubleExtra(BILL_SUB_TOTAL_KEY, 0d));
        String strTip = String.format(getString(R.string.global_message_tip), intent.getDoubleExtra(TIP_KEY, 0d));

        txtBillTotal.setText(strSubTotal);
        txtTip.setText(strTip);
        txtTimeStamp.setText(intent.getStringExtra(DATE_KEY));

    }
}
