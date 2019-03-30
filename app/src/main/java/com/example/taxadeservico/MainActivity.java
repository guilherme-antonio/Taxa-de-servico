package com.example.taxadeservico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText value;
    SeekBar seekBar;
    TextView textView;
    TextView tax;
    TextView total;
    NumberFormat formatacaoPercentual = NumberFormat.getPercentInstance();
    NumberFormat formatacaoCurrency = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        textView = (TextView) findViewById(R.id.text);
        tax = (TextView) findViewById(R.id.tax);
        total = (TextView) findViewById(R.id.total);
        value = (EditText) findViewById(R.id.value);

        value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValores();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sincronizaTextView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void sincronizaTextView(){
        int valor = seekBar.getProgress();

        this.textView.setText(formatacaoPercentual.format(Double.valueOf(valor)/100));

        calcularValores();
    }

    public void calcularValores(){
        float valor = Float.valueOf(value.getText().toString());

        int porcentagem = seekBar.getProgress();

        float taxa = valor * (Float.valueOf(porcentagem) / 100);

        float totalValue = valor + taxa;

        tax.setText(formatacaoCurrency.format(taxa));
        total.setText(formatacaoCurrency.format(totalValue));
    }
}
