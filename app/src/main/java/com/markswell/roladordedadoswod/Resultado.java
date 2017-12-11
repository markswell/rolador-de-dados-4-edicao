package com.markswell.roladordedadoswod;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by markswell on 12/11/17.
 */

public class Resultado {
    @Getter @Setter
    private String resultado;
    @Getter @Setter
    private String contagemDados;

    public Resultado(String resultado, String contagemDados) {
        this.resultado = resultado;
        this.contagemDados = contagemDados;
    }
}
