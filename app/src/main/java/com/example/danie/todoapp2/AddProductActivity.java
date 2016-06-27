package com.example.danie.todoapp2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.danie.todoapp2.config.DB;
import com.example.danie.todoapp2.models.BuyingItem;
import com.example.danie.todoapp2.models.Greatness;

public class AddProductActivity extends AppCompatActivity {
    private EditText editTextName = null;
    private EditText editTextAmount = null;
    private RadioButton radioPackage = null;
    private RadioButton radioPot = null;
    private RadioButton radioKilogram = null;
    private RadioButton radioLiters = null;
    private Button btnSave = null;
    private Button btnCancel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        this.editTextName = (EditText) findViewById(R.id.editTextName);
        this.editTextAmount = (EditText) findViewById(R.id.editTextAmount);
        this.radioPackage = (RadioButton) findViewById(R.id.radioPackage);
        this.radioPot = (RadioButton) findViewById(R.id.radioPot);
        this.radioKilogram = (RadioButton) findViewById(R.id.radioKilogram);
        this.radioLiters = (RadioButton) findViewById(R.id.radioLiters);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnCancel = (Button) findViewById(R.id.btnCancel);

        this.radioPackage.setText(Greatness.getValue(Greatness.PACKAGE, false));
        this.radioPackage.setEnabled(false);
        this.radioPot.setText(Greatness.getValue(Greatness.POT, false));
        this.radioPot.setEnabled(false);
        this.radioKilogram.setText(Greatness.getValue(Greatness.KILOGRAM, false));
        this.radioKilogram.setEnabled(false);
        this.radioLiters.setText(Greatness.getValue(Greatness.LITERS, false));
        this.radioLiters.setEnabled(false);

        final AddProductActivity self = this;

        this.editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String newVal = s.toString().replace(',', '.');
                boolean isPlural = newVal.compareTo("") != 0 && (new Float(newVal)) > 1.0f;

                if (newVal.compareTo("") != 0 && (new Float(newVal)) > 0) {
                    float newFloatVal = new Float(newVal);
                    if (Math.round(newFloatVal) != newFloatVal) {
                        self.radioPackage.setEnabled(false);
                        self.radioPot.setEnabled(false);
                        self.radioKilogram.setEnabled(true);
                        self.radioLiters.setEnabled(true);
                    } else {
                        self.radioPackage.setEnabled(true);
                        self.radioPot.setEnabled(true);
                        self.radioKilogram.setEnabled(true);
                        self.radioLiters.setEnabled(true);
                    }
                } else {
                    self.radioPackage.setEnabled(false);
                    self.radioPot.setEnabled(false);
                    self.radioKilogram.setEnabled(false);
                    self.radioLiters.setEnabled(false);
                }

                self.radioPackage.setText(Greatness.getValue(Greatness.PACKAGE, isPlural));
                self.radioPot.setText(Greatness.getValue(Greatness.POT, isPlural));
                self.radioKilogram.setText(Greatness.getValue(Greatness.KILOGRAM, isPlural));
                self.radioLiters.setText(Greatness.getValue(Greatness.LITERS, isPlural));
            }
        });

        this.radioPackage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    self.radioPot.setChecked(false);
                    self.radioKilogram.setChecked(false);
                    self.radioLiters.setChecked(false);
                }
            }
        });

        this.radioPot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    self.radioPackage.setChecked(false);
                    self.radioKilogram.setChecked(false);
                    self.radioLiters.setChecked(false);
                }
            }
        });

        this.radioKilogram.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    self.radioPackage.setChecked(false);
                    self.radioPot.setChecked(false);
                    self.radioLiters.setChecked(false);
                }
            }
        });

        this.radioLiters.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    self.radioPackage.setChecked(false);
                    self.radioPot.setChecked(false);
                    self.radioKilogram.setChecked(false);
                }
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.finish();
            }
        });

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isNameFilled = self.editTextName.getText().toString().compareTo("") != 0;
                boolean isAmountFilled = self.editTextAmount.getText().toString().compareTo("") != 0;
                boolean isGreatnessSelected = self.radioPackage.isChecked() || self.radioPot.isChecked() || self.radioKilogram.isChecked() || self.radioLiters.isChecked();

                if (!isNameFilled || !isAmountFilled || !isGreatnessSelected) {
                    Snackbar snackbar;
                    snackbar = Snackbar.make(v, "Todos os campos devem ser preenchidos!", Snackbar.LENGTH_LONG);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(Color.RED);
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.BLACK);
                    snackbar.show();

                    if (!isNameFilled)
                        self.editTextName.requestFocus();
                    else if (!isAmountFilled)
                        self.editTextAmount.requestFocus();
                    else if (!isGreatnessSelected)
                        self.radioPackage.requestFocus();
                } else {
                    BuyingItem buyingItem = new BuyingItem();
                    buyingItem.setName(self.editTextName.getText().toString());
                    buyingItem.setAmount(new Float(self.editTextAmount.getText().toString()));

                    if (self.radioPackage.isChecked())
                        buyingItem.setGreatness(Greatness.PACKAGE);
                    else if (self.radioPot.isChecked())
                        buyingItem.setGreatness(Greatness.POT);
                    else if (self.radioKilogram.isChecked())
                        buyingItem.setGreatness(Greatness.KILOGRAM);
                    else if (self.radioLiters.isChecked())
                        buyingItem.setGreatness(Greatness.LITERS);

                    DB.addItem(self, buyingItem);

                    self.finish();
                }
            }
        });
    }
}
