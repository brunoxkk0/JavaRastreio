package br.com.brunoxkk0;

import br.com.brunoxkk0.utils.InvalidSroException;
import br.com.brunoxkk0.utils.RastreioParser;
import br.com.brunoxkk0.utils.WebHelper;

public class RastreioAPI {

    private String sro;
    private String events;
    private String currentStatus;

    public RastreioAPI(String sro) throws InvalidSroException {
        if(!parseSro(sro)){
            throw new InvalidSroException();
        }

        this.sro = sro;

        WebHelper webHelper = new WebHelper();
        RastreioParser rastreioParser = new RastreioParser(webHelper.post("https://www2.correios.com.br/sistemas/rastreamento/resultado_semcontent.cfm","objetos="+sro, "null"));

        currentStatus = rastreioParser.getCurrentStatus();
        events = rastreioParser.convert();
    }

    private boolean parseSro(String sro){
        String patten = "([A-Z]){2}([0-9]){9}([A-Z]){2}";
        return sro.matches(patten);
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getEvents() {
        return events;
    }

    public String getSro() {
        return sro;
    }
}
