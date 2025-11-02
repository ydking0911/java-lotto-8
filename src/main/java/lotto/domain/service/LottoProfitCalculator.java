package lotto.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LottoProfitCalculator {
    private static final int LOTTO_PRICE = 1_000;

    public BigDecimal calculateProfitRate(long totalReward, int ticketCount) {
        if (ticketCount > 0) {
            return profit(totalReward, ticketCount);
        }
        return BigDecimal.ZERO.setScale(1);
    }

    private BigDecimal profit(long totalReward, int ticketCount) {
        BigDecimal reward = BigDecimal.valueOf(totalReward);
        BigDecimal spent = BigDecimal.valueOf(ticketCount * (long) LOTTO_PRICE);
        return reward.multiply(BigDecimal.valueOf(100))
                .divide(spent, 1, RoundingMode.HALF_UP);
    }
}

