package lotto.application.usecase;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.application.dto.request.LottoResultRequest;
import lotto.application.dto.response.LottoResultResponse;
import lotto.domain.entity.Lotto;
import lotto.domain.entity.Rank;
import lotto.domain.service.LottoProfitCalculator;
import lotto.domain.service.LottoResultService;

class LottoResultUseCaseTest {
    @DisplayName("당첨 결과를 집계하고 수익률을 계산한다.")
    @Test
    void calculateResult() {
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 40, 41, 42)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );

        LottoProfitCalculator profitCalculator = new LottoProfitCalculator();
        LottoResultService resultService = new LottoResultService();
        LottoResultUseCase useCase = new LottoResultUseCase(profitCalculator, resultService);
        LottoResultResponse response = useCase.calculate(new LottoResultRequest(List.of(1, 2, 3, 4, 5, 6), 7), tickets);

        assertThat(response.rankCounts().get(Rank.FIFTH)).isEqualTo(1);
        assertThat(response.rankCounts().get(Rank.MISS)).isEqualTo(1);
        assertThat(response.profitRate()).isEqualTo(new BigDecimal("250.0"));
    }
}
