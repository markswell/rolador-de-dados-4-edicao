package com.markswell.roladordedadoswod;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by markswell on 12/11/17.
 */

public class Rolador {
    private Integer dificuldade;
    private Integer dados;
    private boolean rerolarDez;
    private boolean subtrairUm;

    public Rolador(Integer dificuldade, Integer dados, boolean rerolarDez, boolean subtrairUm) {
        this.dificuldade = dificuldade;
        this.dados = dados;
        this.rerolarDez = rerolarDez;
        this.subtrairUm = subtrairUm;
    }

    public Resultado rolar() {
        Integer somaResultados = 0;
        List<Integer> mostar = new ArrayList<>();
        for (int i = 0; i < dados; i++){
            Integer result = obterResultadoDado();
            mostar.add(result);
            somaResultados = avaliar(somaResultados, result);
            if(result.equals(10) && rerolarDez){
                Integer res = obterResultadoDado();
                somaResultados = avaliar(somaResultados, res);
            }
        }

        return criarResultado(somaResultados, mostar);
    }

    @NonNull
    private Resultado criarResultado(Integer somaResultados, List<Integer> mostar) {
        String resultado = new String();
        if(somaResultados < 0){
            resultado = somaResultados + " falha(s) critica(s)";
        }else if(somaResultados.equals(0)){
            resultado = "Falha";
        }else{
            resultado = somaResultados + " sucesso(s)";
        }
        Gson gson = new Gson();
        String resultadoDados = gson.toJson(mostar);
        return  new Resultado(resultado, resultadoDados);
    }

    private Integer avaliar(Integer somaResultados, Integer result) {
        if(result >= dificuldade ){
            somaResultados = somaResultados + 1;
        }else if(result.equals(1) && subtrairUm){
            somaResultados = somaResultados - 1;
        }
        return somaResultados;
    }

    private Integer obterResultadoDado() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
