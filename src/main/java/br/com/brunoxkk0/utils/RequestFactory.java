package br.com.brunoxkk0.utils;

import br.com.brunoxkk0.core.SRO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestFactory {

    private static final String TARGET_URL = "https://www2.correios.com.br/sistemas/rastreamento/resultado_semcontent.cfm";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36";

    private static Proxy INTERNAL_PROXY;

    public static void setProxy(Proxy proxy){
        INTERNAL_PROXY = proxy;
    }

    private HttpURLConnection open() throws IOException {

        URL url = new URL(TARGET_URL);

        HttpURLConnection httpURLConnection;

        if(INTERNAL_PROXY != null){
            httpURLConnection = (HttpURLConnection) url.openConnection(INTERNAL_PROXY);
        }else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        httpURLConnection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        return httpURLConnection;
    }

    public byte[] post(SRO sro) throws InvalidRequestException {

        HttpURLConnection httpURLConnection;
        OutputStream outputStream;

        try {

            httpURLConnection = open();

        } catch (IOException ioException) {

            if(ioException instanceof SocketTimeoutException){
                throw new InvalidRequestException("TimeOut", ioException);
            }

            throw new InvalidRequestException(ioException);
        }

        try {

            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(("objetos=" + sro.getSro()).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[16384];

            InputStream inputStream = httpURLConnection.getInputStream();

            int len;
            while ((len = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, len);
            }

            byteArrayOutputStream.flush();

            return byteArrayOutputStream.toByteArray();

        } catch (IOException ioException) {
            throw new InvalidRequestException(ioException);
        }

    }

}
