package edu.miracosta.cs134.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

// Not going to hard code for Bill.java seek bar is keeping track of amount. Tip percent below
public class MainActivity extends AppCompatActivity {

    // Member variables to format as currency or percent(NumberFormat) Alt Ent for NmrFormat java.tx
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    NumberFormat percent = NumberFormat.getPercentInstance(Locale.getDefault());

    // Member variables for each component used in the app //all null first -- input below
    private EditText amountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    // Member variables for our model --add bill next, look at bill.java (all 0'd out)too add
    private Bill currentBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all member variables in onCreate
        amountEditText = findViewById(R.id.amountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        percentSeekBar = findViewById(R.id.percentSeekBar);

        // Initialize our model
        currentBill = new Bill();
        // Set the tip percent to match the seek bar int 15 by int 100 get 0 so, cast as dec
        currentBill.setTipPercent(percentSeekBar.getProgress() / 100.0);

        // Connect code to the event onProgressChanged for SeekBar type in OnSeekBar, below popped
        //  ++ from top of  OnSeek by enter
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // Update our model
                currentBill.setAmount(progress / 100.0);
                // Change the label of the tip percent CharSequence, int progress for setText blw
                percentTextView.setText(percent.format(currentBill.getTipPercent()));
                tipTextView.setText(currency.format(currentBill.getTipAmount()));
                totalTextView.setText(currency.format(currentBill.getTotalAmount()));
               // tipTextView.setText(percent.format(progress)); //changed to and added too above
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Does nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Does nothing
            }
        });

        // Connect code to the event onTextChanged for EditText
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override  // Called the anonymous inner class
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Does nothing
            }

            @Override  //model has to change when progress changes
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Update our model (currentBill)
                currentBill.setAmount(Double.parseDouble(amountEditText.getText());
                // Convert string to double use parse with double, above

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        })

    }
}
