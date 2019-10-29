package br.com.brunoxkk0;

import br.com.brunoxkk0.utils.Parser;
import br.com.brunoxkk0.utils.WebHelper;

public class JavaRastreio {

    public static void main(String[] args) {
        WebHelper webHelper = new WebHelper();

        new Parser(webHelper.post("https://www2.correios.com.br/sistemas/rastreamento/resultado_semcontent.cfm","objetos=PS240269255BR", "null"));
        //System.out.println(webHelper.post("https://www2.correios.com.br/sistemas/rastreamento/resultado_semcontent.cfm","objetos=PS240269255BR", "null"));

    }
}
