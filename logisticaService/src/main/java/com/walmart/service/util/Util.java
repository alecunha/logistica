package com.walmart.service.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class Util {

	/**
	 * Formata valor para padrao PT/BR
	 * 
	 * @param BigDecimal valor
	 * 
	 * @return String - Ex: 1.111,25 - #,##0.00
	 */
	public static String formataValorTOReal(BigDecimal valor, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern, new DecimalFormatSymbols(new Locale("pt", "BR")));		
		String valorFormatado = "";		
		if (valor != null) {
			valorFormatado = df.format(valor);
		}
		return valorFormatado;
	}
	
	/**
	 * Formata valor Real em BigDecimal
	 * @param source
	 * @return BigDecimal - 1111.25 - #,##0.00
	 */
	public static BigDecimal formataStringTOValor(String source, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern, new DecimalFormatSymbols(new Locale("pt", "BR")));
		BigDecimal valor = null;
		
		try {
			if (source != null && !source.equals("")) {
				valor = new BigDecimal(df.parse(source).toString());
			}
		} catch (ParseException e) {
			new Exception("BigDecimal formataStringTOValor(String valor)", e);
		}
		
		return valor;
	}
}
