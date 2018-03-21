package com.example.demo;

import org.javamoney.moneta.convert.ExchangeRateBuilder;
import org.javamoney.moneta.spi.DefaultNumberValue;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import javax.money.convert.ConversionContextBuilder;
import javax.money.convert.ExchangeRate;
import javax.money.convert.RateType;
import java.util.ArrayList;
import java.util.List;

@Component
public class RateRepository {

    // là on s'appuie normallement sur un Repository, qui peut aller chercher les ExchangeRate en base
    List<ExchangeRate> buildRates() {
        List<ExchangeRate> rates = new ArrayList<>();

        ExchangeRateBuilder builder;

        builder = new ExchangeRateBuilder(ConversionContextBuilder.create(WitcherExchangeRateProvider.CONTEXT, RateType.DEFERRED).build());
        builder.setBase(Monetary.getCurrency("FLN", "witcher"));
        builder.setTerm(Monetary.getCurrency("CRN", "witcher"));
        builder.setFactor(DefaultNumberValue.of(0.33));
        rates.add(builder.build());

        builder = new ExchangeRateBuilder(ConversionContextBuilder.create(WitcherExchangeRateProvider.CONTEXT, RateType.DEFERRED).build());
        builder.setBase(Monetary.getCurrency("CRN", "witcher"));
        builder.setTerm(Monetary.getCurrency("ORN", "witcher"));
        builder.setFactor(DefaultNumberValue.of(6.5));
        rates.add(builder.build());

        return rates;
    }
}
