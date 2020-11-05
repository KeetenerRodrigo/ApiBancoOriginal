package com.keetener.apibancooriginal.service;

import com.keetener.apibancooriginal.model.Statistic;
import com.keetener.apibancooriginal.model.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Keetener Rodrigo
 */
@Service
public class StatisticService {
	
	/**
	 * @param transactions
	 * @return Statistic
	 */
	public Statistic create(List<Transaction> transactions) {
		
		var statistics = new Statistic();
		statistics.setCount(transactions.stream().count());
		statistics.setAvg(BigDecimal.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).average().orElse(0.0))
				.setScale(2, RoundingMode.HALF_UP));
		statistics.setMin(BigDecimal.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).min().orElse(0.0))
				.setScale(2, RoundingMode.HALF_UP));
		statistics.setMax(BigDecimal.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).max().orElse(0.0))
				.setScale(2, RoundingMode.HALF_UP));
		statistics.setSum(BigDecimal.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).sum())
				.setScale(2, RoundingMode.HALF_UP));
		
		return statistics;
	}

}
