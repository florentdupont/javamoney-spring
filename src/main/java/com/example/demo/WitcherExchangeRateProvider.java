package com.example.demo;

import org.javamoney.moneta.spi.AbstractRateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.convert.*;
import java.util.List;


@Service
public class WitcherExchangeRateProvider extends AbstractRateProvider {

    @Autowired
    RateRepository rateRepository;

    public static final ProviderContext CONTEXT =
            ProviderContextBuilder.of("WTCH", RateType.DEFERRED)
                    .set("providerDescription", "Novigrad Central Bank")
                    .set("days", 1).build();

    public WitcherExchangeRateProvider() {
        super(CONTEXT);
    }

    @Override
    public ExchangeRate getExchangeRate(ConversionQuery conversionQuery) {

        List<ExchangeRate> rates = rateRepository.buildRates();

        // ici, je récupère le Rate qui m'intéresse le plus en m'appuyant sur le conversionQuery
        // et notamment sur le Base (la monnaie de base) et le term (la monnaie de destination).
        // je retourne l'Exchange Rate qui convient le mieux.
        // en pratique, je pourrais également m'appuyer sur d'autres contexte (la date du jours, etc..)
        // pour retourner le taux le plus approprié.

        return rates.stream().filter(x -> x.getBaseCurrency().equals(conversionQuery.getBaseCurrency())).findFirst().get();
    }



}
