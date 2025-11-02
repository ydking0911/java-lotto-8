package lotto.application.usecase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lotto.application.dto.request.LottoResultRequest;
import lotto.application.dto.response.LottoResultResponse;
import lotto.domain.entity.Lotto;
import lotto.domain.entity.LottoDraw;
import lotto.domain.entity.LottoResult;
import lotto.domain.entity.Rank;
import lotto.domain.service.LottoProfitCalculator;
import lotto.domain.service.LottoResultService;

public class LottoResultUseCase {
    private final LottoProfitCalculator profitCalculator;
    private final LottoResultService resultService;

    public LottoResultUseCase(LottoProfitCalculator profitCalculator, LottoResultService resultService) {
        this.profitCalculator = profitCalculator;
        this.resultService = resultService;
    }

    public LottoResultResponse calculate(LottoResultRequest request, List<Lotto> tickets) {
        LottoDraw draw = new LottoDraw(request.winningNumbers(), request.bonusNumber());
        LottoResult lottoResult = resultService.summarize(draw, tickets);
        BigDecimal profitRate = profitCalculator.calculateProfitRate(lottoResult.totalReward(), tickets.size());
        Map<Rank, Integer> rankCounts = lottoResult.rankCounts();
        return new LottoResultResponse(rankCounts, profitRate);
    }
}
