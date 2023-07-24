package com.markswell.roladordedadoswod;

/**
 * Created by markswell on 21/04/17.
 */

public class IntegerCustom {

    Integer i;
    public IntegerCustom(Integer i) {
        this.i = i;
    }

    @Override
    public String toString() {

        return i.equals(1) ? i + " dado" : i + " dados";
    }
}
