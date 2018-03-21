package com.example.demo;

import org.javamoney.moneta.internal.DefaultMonetaryAmountsSingletonQuerySpi;
import org.javamoney.moneta.internal.DefaultMonetaryAmountsSingletonSpi;
import org.javamoney.moneta.internal.DefaultMonetaryCurrenciesSingletonSpi;
import org.javamoney.moneta.internal.MoneyAmountFactoryProvider;
import org.javamoney.moneta.internal.convert.DefaultMonetaryConversionsSingletonSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.money.convert.ExchangeRateProvider;
import javax.money.spi.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

@Service
public class WitcherServiceProvider implements ServiceProvider {

    @Autowired
    WitcherCurrencyProvider currencyProvider;

    @Autowired
    WitcherExchangeRateProvider exchangeRateProvider;



    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public <T> List<T> getServices(Class<T> serviceType) {

        if(serviceType.isAssignableFrom(CurrencyProviderSpi.class)) {
            return (List<T>)asList(currencyProvider);
        }

        if(serviceType.isAssignableFrom(MonetaryConversionsSingletonSpi.class)) {
            // implementation par défaut
            return (List<T>)asList(new DefaultMonetaryConversionsSingletonSpi());
        }

        if(serviceType.isAssignableFrom(ExchangeRateProvider.class)) {
            return (List<T>)asList(exchangeRateProvider);
        }


        // s'ils ne sont pas définis, alors ceux par défaut sont utilisés...
//        if(serviceType.isAssignableFrom(MonetaryCurrenciesSingletonSpi.class)) {
//            // implementation par défaut
//            return (List<T>)asList(new DefaultMonetaryCurrenciesSingletonSpi());
//        }
//
//        if(serviceType.isAssignableFrom(MonetaryAmountsSingletonSpi.class)) {
//            // implementation par défaut
//            return (List<T>)asList(new DefaultMonetaryAmountsSingletonSpi());
//        }
//
//        if(serviceType.isAssignableFrom(MonetaryAmountFactoryProviderSpi.class)) {
//            // implementation par défaut - représentée par BigDecimal
//            return (List<T>)asList(new MoneyAmountFactoryProvider());
//        }
//
//        if(serviceType.isAssignableFrom(MonetaryAmountsSingletonQuerySpi.class)) {
//            // implementation par défaut
//            return (List<T>)asList(new DefaultMonetaryAmountsSingletonQuerySpi());
//        }

//        if(serviceType.isAssignableFrom(MonetaryRoundingsSingletonSpi.class)) {
//            // implementation par défaut
//            return (List<T>)asList(new DefaultMonetaryRoundingsSingletonSpi());
//        }


        return emptyList();
    }

}
