package com.keetener.apibancooriginal.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Keetener Rodrigo
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Statistic {
	
	private BigDecimal sum;
	private BigDecimal avg;
	private BigDecimal max;
	private BigDecimal min;
	private long count;

}
