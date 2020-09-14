package br.com.brunoxkk0.jrastreio.core;

import br.com.brunoxkk0.jrastreio.utils.InvalidSroException;
import br.com.brunoxkk0.jrastreio.utils.SROParser;

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
