package lotto.application.dto.response;

import java.math.BigDecimal;
import java.util.Map;

import lotto.domain.entity.Rank;

public class LottoResultResponse {
    private final Map<Rank, Integer> rankCounts;
    private final BigDecimal profitRate;

    public LottoResultResponse(Map<Rank, Integer> rankCounts, BigDecimal profitRate) {
        this.rankCounts = rankCounts;
        this.profitRate = profitRate;
    }

    public Map<Rank, Integer> rankCounts() {
        return rankCounts;
    }

    public BigDecimal profitRate() {
        return profitRate;
    }
}
