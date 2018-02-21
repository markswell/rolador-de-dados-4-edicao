package com.markswell.roladordedadoswod;

import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textDificuldade;
    private TextView textDado;
    private Spinner spinner;
    private ArrayList<IntegerCustom> lista;
    private ImageButton imageButtonMais;
    private ImageButton imageButtonMenos;
    private SeekBar dificuldade;
    private boolean dobraDez;
    private boolean subtraiUm = true;
    private Integer dificuldadeNivel;
    private TextView resultado;
    private FloatingActionButton fab;
    private Integer numeroDados;
    private TextView resultadoIndividual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarCampos();
    }

    private void iniciarCampos() {
        dificuldadeNivel = new Integer(6);
        numeroDados = new Integer(1);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        resultado = (TextView)findViewById(R.id.resultado);
        resultadoIndividual = (TextView)findViewById(R.id.resultado_individual);

        dificuldade = (SeekBar)findViewById(R.id.dificuldade);

        imageButtonMais = (ImageButton)findViewById(R.id.imageButtonMais);
        imageButtonMenos = (ImageButton)findViewById(R.id.imageButtonMenos);

        iniciarRegras();

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/fonte_textos_rolador.ttf");

        textDificuldade = (TextView) findViewById(R.id.textDificuldade);
        textDificuldade.setTypeface(face);
        textDado = (TextView) findViewById(R.id.textDado);
        textDado.setTypeface(face);
        resultado.setTypeface(face);

        iniciaSpinner();
    }

    private void iniciarRegras() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rolador rolador = new Rolador(dificuldadeNivel, numeroDados, dobraDez, subtraiUm);
                Resultado resultadoRolador = rolador.rolar();
                resultado.setText(resultadoRolador.getResultado());
                resultadoIndividual.setText(resultadoRolador.getContagemDados());
            }
        });

        dificuldade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dificuldadeNivel = progress + 1;
                textDificuldade.setText("Dificuldade " + dificuldadeNivel + ":");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imageButtonMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dobraDez = !dobraDez;
                if(dobraDez){
                    imageButtonMais.setImageResource(R.drawable.mais_clicado);
                }else{
                    imageButtonMais.setImageResource(R.drawable.mais);
                }

            }
        });

        imageButtonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtraiUm = !subtraiUm;
                if(subtraiUm){
                    imageButtonMenos.setImageResource(R.drawable.menos_clicado);
                }else{
                    imageButtonMenos.setImageResource(R.drawable.menos);
                }
            }
        });
    }

    private void iniciaSpinner() {
        spinner = (Spinner)findViewById(R.id.spinner);
        lista = new ArrayList<>();

        for(int i = 1; i < 51; i++){
            lista.add(new IntegerCustom(i));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textDado.setText((position + 1)+"");
                numeroDados = position + 1;
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }
}
