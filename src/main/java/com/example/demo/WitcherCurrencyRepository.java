package com.example.demo;

import org.javamoney.moneta.CurrencyUnitBuilder;
import org.springframework.stereotype.Component;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class WitcherCurrencyRepository {


    public List<CurrencyUnit> getAvailableCurrencies() {


        /**
         * Crown currently has a value of six and a half orens
         *
         * 500 Orens are the equivalent of 45 Florens
         *
         * The rate of exchange for florens to crowns is three-to-one.
         */

        return asList(
                // The Crown (also known as the Novigrad crown) is a monetary unit which is used in some Northern Kingdoms
                CurrencyUnitBuilder.of("CRN", "witcher").build(),
                // Oren (alternatively Temerian oren) is the official unit of currency in Temeria and the kingdom's vassal states and territories.
                CurrencyUnitBuilder.of("ORN", "witcher").build(),
                // The Floren, also known as the "Nilfgaardian Floren" is the monetary unit used in Nilfgaard and its provinces
                CurrencyUnitBuilder.of("FLN", "witcher").build()
                );




    }

}
