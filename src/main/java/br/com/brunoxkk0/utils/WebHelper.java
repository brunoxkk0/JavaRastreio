package br.com.brunoxkk0.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class WebHelper {

    public String post(String url, String query, String auth) {
        try {

            URLConnection conn = open(url, auth);
            OutputStream output = conn.getOutputStream();
            output.write(query.getBytes(StandardCharsets.UTF_8.name()));
            output.flush();
            output.close();
            InputStream response = conn.getInputStream();
            String outputStr = null;
            BufferedReader reader;
            String line;

            reader = new BufferedReader(new InputStreamReader(response, StandardCharsets.UTF_8.name()));
            while((line = reader.readLine()) != null){
                outputStr += line;
            }

            reader.close();

            return outputStr;
        } catch (IOException var9) {
            var9.printStackTrace();
            return null;
        }
    }

    private HttpURLConnection open(String url, String auth) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        if (auth != null) {
            conn.setRequestProperty("Authorization", auth);
        }

        return conn;
    }

}
