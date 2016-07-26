package com.tc.app.exchangemonitor.model.predicates;

import java.util.function.Predicate;

import com.tc.app.exchangemonitor.model.ExternalMapping;

public class ExternalMappingPredicates
{
	public static final Predicate<ExternalMapping> applyShowAllPredicate = (ExternalMapping anExternalMapping) -> { return true; };
	public static final Predicate<ExternalMapping> applyShowNonePredicate = (ExternalMapping anExternalMapping) -> { return false; };
	
	public static final Predicate<ExternalMapping> isNYMEXPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1;
	};
	
	public static final Predicate<ExternalMapping> isICEPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3;
	};
	
	//NYMEX
	public static final Predicate<ExternalMapping> applyNymexTradersPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('T');
	};
	
	public static final Predicate<ExternalMapping> applyNymexBrokersPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('B');
	};
	
	public static final Predicate<ExternalMapping> applyNymexCompaniesPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('C');
	};
	
	public static final Predicate<ExternalMapping> applyNymexCurrenciesPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('U');
	};
	
	public static final Predicate<ExternalMapping> applyNymexPortfoliosPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('P');
	};
	
	public static final Predicate<ExternalMapping> applyNymexTemplateTradesPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('S');
	};
	
	public static final Predicate<ExternalMapping> applyNymexAccountsPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('K');
	};
	
	public static final Predicate<ExternalMapping> applyNymexUOMConversionsPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('Q');
	};
	
	public static final Predicate<ExternalMapping> applyNymexTradingPeriodsPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('O');
	};
	
	//ICE
	public static final Predicate<ExternalMapping> applyIceTradersPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('T');
	};
	
	public static final Predicate<ExternalMapping> applyIceBrokersPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('B');
	};
	
	public static final Predicate<ExternalMapping> applyIceCompaniesPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('C');
	};
	
	public static final Predicate<ExternalMapping> applyIceCurrenciesPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('U');
	};
	
	public static final Predicate<ExternalMapping> applyIcePortfoliosPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('P');
	};
	
	public static final Predicate<ExternalMapping> applyIceTemplateTradesPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('S');
	};
	
	public static final Predicate<ExternalMapping> applyIceAccountsPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('K');
	};
	
	public static final Predicate<ExternalMapping> applyIceUOMConversionsPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('Q');
	};
	
	public static final Predicate<ExternalMapping> applyIceTradingPeriodsPredicate = (ExternalMapping anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('O');
	};
}