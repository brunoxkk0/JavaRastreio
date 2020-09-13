package br.com.brunoxkk0.utils;

import java.util.regex.Pattern;

public class SROParser {

    private static final Pattern SRO_PATTERN = Pattern.compile("([A-Z]){2}([0-9]){9}([A-Z]){2}");

    public static boolean match(String sro){
        return SRO_PATTERN.matcher(sro).find();
    }
}
