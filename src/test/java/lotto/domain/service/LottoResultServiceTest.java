package lotto.domain.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.entity.Lotto;
import lotto.domain.entity.LottoDraw;
import lotto.domain.entity.LottoResult;
import lotto.domain.entity.Rank;

class LottoResultServiceTest {
    @DisplayName("로또 티켓의 당첨 결과를 순위별로 요약한다.")
    @Test
    void summarizeTickets() {
        LottoResultService service = new LottoResultService();
        LottoDraw draw = new LottoDraw(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );

        LottoResult result = service.summarize(draw, tickets);

        assertThat(result.rankCounts().get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.rankCounts().get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.rankCounts().get(Rank.MISS)).isEqualTo(1);
    }
}
