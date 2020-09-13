package br.com.brunoxkk0.core;

import br.com.brunoxkk0.utils.InvalidSroException;
import br.com.brunoxkk0.utils.SROParser;

public class SRO {

    private final String sro;


    public SRO(String sro) throws InvalidSroException {

        if(sro == null || !SROParser.match(sro)) throw new InvalidSroException();

        this.sro = sro;

    }

    public String getSro() {
        return sro;
    }

    @Override
    public String toString() {
        return "SRO{" +
                "sro='" + sro + '\'' +
                '}';
    }
}
