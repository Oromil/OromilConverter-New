package com.kilograpp.oromilconverter.util;

import com.kilograpp.oromilconverter.data.network.entities.Response;

public class ValuteResponseMapper {

    public static Response map(Response response) {

        response.getValutes().add(response.allValutes.amd);
        response.getValutes().add(response.allValutes.AUD);
        response.getValutes().add(response.allValutes.AZN);
        response.getValutes().add(response.allValutes.bgn);
        response.getValutes().add(response.allValutes.brl);
        response.getValutes().add(response.allValutes.byn);
        response.getValutes().add(response.allValutes.cad);
        response.getValutes().add(response.allValutes.chf);
        response.getValutes().add(response.allValutes.cny);
        response.getValutes().add(response.allValutes.czk);
        response.getValutes().add(response.allValutes.dkk);
        response.getValutes().add(response.allValutes.eur);
        response.getValutes().add(response.allValutes.gbp);
        response.getValutes().add(response.allValutes.hkd);
        response.getValutes().add(response.allValutes.huf);
        response.getValutes().add(response.allValutes.inr);
        response.getValutes().add(response.allValutes.jpy);
        response.getValutes().add(response.allValutes.kgs);
        response.getValutes().add(response.allValutes.krw);
        response.getValutes().add(response.allValutes.kzt);
        response.getValutes().add(response.allValutes.mdl);
        response.getValutes().add(response.allValutes.nok);
        response.getValutes().add(response.allValutes.pln);
        response.getValutes().add(response.allValutes.ron);
        response.getValutes().add(response.allValutes.sek);
        response.getValutes().add(response.allValutes.sgd);
        response.getValutes().add(response.allValutes.tjs);
        response.getValutes().add(response.allValutes.tmt);
        response.getValutes().add(response.allValutes.ttry);
        response.getValutes().add(response.allValutes.uah);
        response.getValutes().add(response.allValutes.usd);
        response.getValutes().add(response.allValutes.uzs);
        response.getValutes().add(response.allValutes.xdr);
        response.getValutes().add(response.allValutes.zar);

        return response;
    }
}
