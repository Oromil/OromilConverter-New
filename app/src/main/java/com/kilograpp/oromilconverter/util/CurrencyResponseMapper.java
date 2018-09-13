package com.kilograpp.oromilconverter.util;

import com.kilograpp.oromilconverter.data.network.entities.Response;

public class CurrencyResponseMapper {

    public static Response map(Response response) {

        response.getCurrencies().add(response.allCurrency.amd);
        response.getCurrencies().add(response.allCurrency.AUD);
        response.getCurrencies().add(response.allCurrency.AZN);
        response.getCurrencies().add(response.allCurrency.bgn);
        response.getCurrencies().add(response.allCurrency.brl);
        response.getCurrencies().add(response.allCurrency.byn);
        response.getCurrencies().add(response.allCurrency.cad);
        response.getCurrencies().add(response.allCurrency.chf);
        response.getCurrencies().add(response.allCurrency.cny);
        response.getCurrencies().add(response.allCurrency.czk);
        response.getCurrencies().add(response.allCurrency.dkk);
        response.getCurrencies().add(response.allCurrency.eur);
        response.getCurrencies().add(response.allCurrency.gbp);
        response.getCurrencies().add(response.allCurrency.hkd);
        response.getCurrencies().add(response.allCurrency.huf);
        response.getCurrencies().add(response.allCurrency.inr);
        response.getCurrencies().add(response.allCurrency.jpy);
        response.getCurrencies().add(response.allCurrency.kgs);
        response.getCurrencies().add(response.allCurrency.krw);
        response.getCurrencies().add(response.allCurrency.kzt);
        response.getCurrencies().add(response.allCurrency.mdl);
        response.getCurrencies().add(response.allCurrency.nok);
        response.getCurrencies().add(response.allCurrency.pln);
        response.getCurrencies().add(response.allCurrency.ron);
        response.getCurrencies().add(response.allCurrency.sek);
        response.getCurrencies().add(response.allCurrency.sgd);
        response.getCurrencies().add(response.allCurrency.tjs);
        response.getCurrencies().add(response.allCurrency.tmt);
        response.getCurrencies().add(response.allCurrency.ttry);
        response.getCurrencies().add(response.allCurrency.uah);
        response.getCurrencies().add(response.allCurrency.usd);
        response.getCurrencies().add(response.allCurrency.uzs);
        response.getCurrencies().add(response.allCurrency.xdr);
        response.getCurrencies().add(response.allCurrency.zar);

        return response;
    }
}
