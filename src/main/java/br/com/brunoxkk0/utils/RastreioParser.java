package br.com.brunoxkk0.utils;

import java.util.ArrayList;

public class RastreioParser {

    private String body;
    private String codeSro = "";
    private String currentStatus = "";
    private ArrayList<String> events;

    public RastreioParser(String raw){
        body = raw.split("<body>")[1];

        codSro(body);
        currentStatus(body);
        events(body);
    }

    private void codSro(String source){
        String[] base = source.split("<span class=\"codSro\">");

        for(String sro : base){
            if(sro.startsWith("<span>")){
                codeSro += sro.replace("</span>","").replace("<span>","");
            }
        }

        if(codeSro.split("</h3>")[0].length() == 13){
            codeSro = codeSro.split("</h3>")[0];
        }
    }

    private void currentStatus(String source){
        String base = source.split("<div class=\"highlightSRO\">")[1];
        base = base.substring(0, base.indexOf("<table class=\"listEvent sro\">"));


        for(String string : base.split("</div>")) {
            if(string.substring(source.indexOf("<")).startsWith("<div id=\"somediv\">")){
                int i = string.substring(source.indexOf("<")).indexOf(">");
                currentStatus = string.substring(i).substring(3);
            }
        }
    }

    private void events(String source){
        String base = source.substring(source.indexOf("<table class=\"listEvent sro\">"),source.lastIndexOf("</table>"));
        ArrayList<String> arrayList = new ArrayList<>();


        for (String string : base.split("<tr>")){
            for(String substring : string.split("<td>")){

                if(substring.indexOf("<td class=") <= 0) continue;

                String temp = substring.substring(substring.indexOf("<td class="));
                String result = "";

                if(temp.startsWith("<td class=\"sroDtEvent\" valign=\"top\">")){
                    String subtemp = temp.substring(0,temp.indexOf("</td>")).replace("<td class=\"sroDtEvent\" valign=\"top\">", "");
                    String[] subbase = subtemp.split("<br />");

                    for(String stringg: subbase){
                        result += " " + (stringg.replace(" ","").replace("\t","").replace("<labelstyle=\"text-transform:capitalize;\">","").replace("&nbsp;","").replace("</label>",""));
                    }
                }

                int pt2 = -1;
                if((pt2 = temp.indexOf("<td class=\"sroLbEvent\">")) >= 0){
                    String subtemp = temp.substring(pt2).replace("<td class=\"sroLbEvent\">", "");
                    String formated = "";

                    result += "," + subtemp.substring(subtemp.indexOf("<strong>")).replace("<strong>","").replace("</strong>","").replace("\t","").replace("<br />","").replace("</td>","").replace("</tr>","");

                }

                arrayList.add(result);
            }
        }
        events = arrayList;
    }

    public String convert(){
        String finalS = "{ \"events\": [";

        for(String data : events){


            String[] splited = data.split(",");
            String[] sub = splited[0].split(" ");

            if(sub.length >= 3){
                finalS += "{\"data\":\"" +sub[1]+"\",";
                finalS += "\"time\":\"" +sub[2]+"\",";
                finalS += "\"local\":\"" +sub[3]+"\",";
                finalS += "\"event\":\"" +splited[1]+"\"},";
            }

        }

        if(finalS.charAt(finalS.length()-1) == ','){
            finalS = finalS.substring(0,finalS.length()-1);
        }

        finalS += "]}";
        return finalS;
    }

    public String getCodeSro() {
        return codeSro;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }
}
