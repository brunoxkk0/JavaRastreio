package br.com.brunoxkk0;

import br.com.brunoxkk0.core.Rastreio;
import br.com.brunoxkk0.core.SRO;
import br.com.brunoxkk0.utils.InvalidRequestException;
import br.com.brunoxkk0.utils.InvalidSroException;
import br.com.brunoxkk0.utils.RequestFactory;
import br.com.brunoxkk0.utils.RequestProcessor;

public class RastreioAPI {

    private RastreioAPI(){}

    public static Rastreio getRastreio(String SRO) throws InvalidSroException, InvalidRequestException {

        SRO sro = new SRO(SRO);

        RequestFactory requestFactory = new RequestFactory();
        RequestProcessor requestProcessor = new RequestProcessor(requestFactory.post(sro));

        return new Rastreio(sro, requestProcessor.getEvents());
    }

}
