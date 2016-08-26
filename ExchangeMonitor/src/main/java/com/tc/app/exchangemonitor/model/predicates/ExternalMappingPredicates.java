package com.tc.app.exchangemonitor.model.predicates;

import java.util.function.Predicate;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;

public class ExternalMappingPredicates
{
	public static final Predicate<IExternalMappingEntity> applyShowAllPredicate = (anExternalMapping) -> { return true; };
	public static final Predicate<IExternalMappingEntity> applyShowNonePredicate = (anExternalMapping) -> { return false; };
	
	public static final Predicate<IExternalMappingEntity> isNYMEXPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1;
	};
	
	public static final Predicate<IExternalMappingEntity> isICEPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3;
	};
	
	//NYMEX
	public static final Predicate<IExternalMappingEntity> applyNymexTradersPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('T');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexBrokersPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('B');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexCompaniesPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('C');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexCurrenciesPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('U');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexPortfoliosPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('P');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexTemplateTradesPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('S');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexAccountsPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('K');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexUOMConversionsPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('Q');
	};
	
	public static final Predicate<IExternalMappingEntity> applyNymexTradingPeriodsPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 1 && anExternalMapping.getMappingType().equals('O');
	};
	
	//ICE
	public static final Predicate<IExternalMappingEntity> applyIceTradersPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('T');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIceBrokersPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('B');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIceCompaniesPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('C');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIceCurrenciesPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('U');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIcePortfoliosPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('P');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIceTemplateTradesPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('S');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIceAccountsPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('K');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIceUOMConversionsPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('Q');
	};
	
	public static final Predicate<IExternalMappingEntity> applyIceTradingPeriodsPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getOid() == 3 && anExternalMapping.getMappingType().equals('O');
	};
}