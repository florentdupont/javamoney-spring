package com.example.demo;

import org.javamoney.moneta.internal.JDKCurrencyAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.money.CurrencyQuery;
import javax.money.CurrencyUnit;
import javax.money.spi.CurrencyProviderSpi;
import java.util.*;

@Service
public class WitcherCurrencyProvider implements CurrencyProviderSpi {

    private static final Map<String, CurrencyUnit> CACHED = new HashMap<>();

    @Autowired
    WitcherCurrencyRepository currencyRepository;

    @PostConstruct
    private void init() {
        for (CurrencyUnit cu : currencyRepository.getAvailableCurrencies()) {
            CACHED.put(cu.getCurrencyCode(), cu);
        }
    }

    @Override
    public String getProviderName() {
        return "witcher";
    }

    @Override
    public boolean isCurrencyAvailable(CurrencyQuery query) {
        return false;
    }

    @Override
    public Set<CurrencyUnit> getCurrencies(CurrencyQuery query) {
        Set<CurrencyUnit> result = new HashSet<>();
        if(!query.getCurrencyCodes().isEmpty()) {
            for (String code : query.getCurrencyCodes()) {
                CurrencyUnit cu = CACHED.get(code);
                if (cu != null) {
                    result.add(cu);
                }
            }
            return result;
        }
        result.addAll(CACHED.values());
        return result;
    }
}
