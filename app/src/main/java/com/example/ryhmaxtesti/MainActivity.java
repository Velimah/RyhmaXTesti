package com.example.ryhmaxtesti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText ika, paino, pituus, kaula, vyotaro, lantio;
    TextView tulos, tulos2, tulos3;
    String BMRtulos, BMItulos, RAStulos;
    RadioGroup radioGroup;
    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.rg);
        paino = findViewById(R.id.paino);
        pituus = findViewById(R.id.pituus);
        ika = findViewById(R.id.ika);
        kaula = findViewById(R.id.kaula);
        vyotaro = findViewById(R.id.vyotaro);
        lantio = findViewById(R.id.lantio);
        tulos = findViewById(R.id.tulos);
        tulos2 = findViewById(R.id.tulos2);
        tulos3 = findViewById(R.id.tulos3);

    }


    //Painoindeksi metodi
    public void laskeBMI(View view) {

        String eka = paino.getText().toString();
        String toka = pituus.getText().toString();

        float painoArvo = Float.parseFloat(eka);
        float pituusArvo = Float.parseFloat(toka) / 100;
        float bmi = painoArvo / (pituusArvo * pituusArvo);

        if (bmi < 15){
            BMItulos = " Sairaalloinen alipaino";
        }else if (bmi < 18){
            BMItulos = " Merkittävä alipaino";
        }else if (bmi < 19){
            BMItulos = " Lievä alipaino";
        }else if (bmi < 25){
            BMItulos = " Normaali paino";
        }else if (bmi < 30){
            BMItulos = " Lievä ylipaino";
        }else if (bmi < 35){
            BMItulos = " Merkittävä ylipaino";
        }else if (bmi < 40){
            BMItulos = " Vaikea ylipaino";
        }else{
            BMItulos = " Sairaalloinen ylipaino";
        }

        tulos.setText(df.format(bmi) + BMItulos);
    }
    //Perusaineenvaihdunta metodi
    public void laskeBMR(View view) {

        String eka = paino.getText().toString();
        String toka = pituus.getText().toString();
        String kolmas = ika.getText().toString();

        float painoArvo = Float.parseFloat(eka);
        float pituusArvo = Float.parseFloat(toka);
        float ikaArvo = Float.parseFloat(kolmas);

        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.nappiMies) {
            double bmr = 13.397 * painoArvo + 4.799 * pituusArvo - 5.677 * ikaArvo + 88.362;
            BMRtulos = " Kcal";
            tulos2.setText(df.format(bmr)+ BMRtulos);
        } else if (checkedId == R.id.nappiNainen) {
            double bmr = 9.247 * painoArvo + 3.098 * pituusArvo - 4.330 * ikaArvo + 447.593;
            BMRtulos = " Kcal";
            tulos2.setText(df.format(bmr)+ BMRtulos);
        }

    }
    //Rasvaprosentti metodi
    public void laskeRAS(View view) {

        String eka = kaula.getText().toString();
        String toka = vyotaro.getText().toString();
        String kolmas = lantio.getText().toString();
        String neljas = pituus.getText().toString();

        float kaulaArvo = Float.parseFloat(eka);
        float vyotaroArvo = Float.parseFloat(toka);
        float lantioArvo = Float.parseFloat(kolmas);
        float pituusArvo = Float.parseFloat(neljas);

        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.nappiMies) {
            double rasvaP = (495 / (1.0324 - (0.19077 * Math.log10((vyotaroArvo - kaulaArvo))) + (0.15456 * Math.log10(pituusArvo)))) - 450;
            RAStulos = " %";
            tulos3.setText(df.format(rasvaP) + RAStulos);
        } else if (checkedId == R.id.nappiNainen) {
            double rasvaP = (495 / (1.29579 - (0.35004 * Math.log10((vyotaroArvo + lantioArvo - kaulaArvo))) + (0.22100 * Math.log10(pituusArvo)))) - 450;
            RAStulos = " %";
            tulos3.setText(df.format(rasvaP) + RAStulos);
        }
    }
}
