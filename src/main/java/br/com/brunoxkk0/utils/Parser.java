package br.com.brunoxkk0.utils;

public class Parser {

    String body;
    String codeSro = "";

    public Parser(String raw){
        body = raw.split("<body>")[1];

        codSro(body);

        System.out.println(body);
        System.out.println(codeSro);
    }

    public void codSro(String string){
        String[] base = string.split("<span class=\"codSro\">");

        for(String sro : base){
            if(sro.startsWith("<span>")){
                codeSro += sro.replace("</span>","").replace("<span>","");
            }
        }

        if(codeSro.split("</h3>")[0].length() == 13){
            codeSro = codeSro.split("</h3>")[0];
        }
    }
}
