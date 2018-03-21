package com.example.demo;

import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.tools.jar.CommandLine;

import javax.annotation.PostConstruct;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import javax.money.spi.Bootstrap;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	WitcherServiceProvider serviceProvider;

	@PostConstruct
	public void initBootstrap() {
		Bootstrap.init(serviceProvider);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		System.out.println("Hello World");

		CurrencyUnit cu = Monetary.getCurrency("CRN", "witcher");

		System.out.println(cu);

		Money m1 = Money.of(500, "CRN");
		Money m2 = Money.of(300, "CRN");

		System.out.println(m1.add(m2));

		ExchangeRateProvider rateProvider = MonetaryConversions.getExchangeRateProvider("WTCH");

		// récupère la currency conversion des Crowns
		CurrencyConversion toCrowns = rateProvider.getCurrencyConversion("CRN");

		// 45 Florens
		Money flo = Money.of(45, "FLN");

		Money crowns = flo.with(toCrowns);

		CurrencyConversion toOrens = rateProvider.getCurrencyConversion("ORN");
		Money orens = crowns.with(toOrens);

		System.out.println(orens);
	}
}
