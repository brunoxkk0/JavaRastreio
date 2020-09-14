package br.com.brunoxkk0.jrastreio;

import br.com.brunoxkk0.jrastreio.core.Rastreio;
import br.com.brunoxkk0.jrastreio.core.SRO;
import br.com.brunoxkk0.jrastreio.utils.InvalidRequestException;
import br.com.brunoxkk0.jrastreio.utils.InvalidSroException;
import br.com.brunoxkk0.jrastreio.utils.RequestFactory;
import br.com.brunoxkk0.jrastreio.utils.RequestProcessor;

public class RastreioAPI {

    private RastreioAPI(){}

    public static Rastreio getRastreio(String SRO) throws InvalidSroException, InvalidRequestException {

        SRO sro = new SRO(SRO);

        RequestFactory requestFactory = new RequestFactory();
        RequestProcessor requestProcessor = new RequestProcessor(requestFactory.post(sro));

        return new Rastreio(sro, requestProcessor.getEvents());
    }

}
