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
    private Integer somaResultados;

    public Rolador(Integer dificuldade, Integer dados, boolean rerolarDez, boolean subtrairUm) {
        this.dificuldade = dificuldade;
        this.dados = dados;
        this.rerolarDez = rerolarDez;
        this.subtrairUm = subtrairUm;
    }

    public Resultado rolar() {
        somaResultados = 0;
        List<Integer> resultados = new ArrayList<>();
        List<Integer> resultadosDez = new ArrayList<>();
        for (int i = 0; i < dados; i++){
            Integer result = getResult(resultados);
            if(result.equals(10) && rerolarDez){
                do {
                    result = getResult(resultadosDez);
                } while (result.equals(10));
            }
        }

        return criarResultado(somaResultados, resultados, resultadosDez);
    }

    @NonNull
    private Integer getResult(List<Integer> resultados) {
        Integer result = obterResultadoDado();
        resultados.add(result);
        avaliar(result);
        return result;
    }

    @NonNull
    private Resultado criarResultado(Integer somaResultados, List<Integer> resultados, List<Integer> resultadosDez) {
        String resultado = new String();
        if(somaResultados < 0){
            resultado = avaliarFalhaCritica(somaResultados);
        }else if(somaResultados.equals(0)){
            resultado = "Falha!";
        }else{
            resultado = avaliarSucesso(somaResultados);
        }
        Gson gson = new Gson();
        return  new Resultado(resultado, gson.toJson(resultados), gson.toJson(resultadosDez));
    }

    @NonNull
    private String avaliarSucesso(Integer somaResultados) {
        if(somaResultados.equals(1))
            return "1sucesso!";
        return somaResultados + " sucessos!";
    }

    @NonNull
    private String avaliarFalhaCritica(Integer somaResultados) {
        if(somaResultados.equals(-1))
            return "Falha crítica!";
        return (somaResultados + " falhas críticas!").replace("-","");
    }

    private Integer avaliar(Integer result) {
        if(result >= dificuldade ){
            ++somaResultados;
        }else if(result.equals(1) && subtrairUm){
            --somaResultados;
        }
        return somaResultados;
    }

    private Integer obterResultadoDado() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
