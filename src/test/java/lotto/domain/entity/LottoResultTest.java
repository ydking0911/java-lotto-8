package lotto.domain.entity;
import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    @DisplayName("순위별 개수를 기반으로 총 상금을 계산한다.")
    @Test
    void calculateTotalReward() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        counts.put(Rank.FIFTH, 2);
        counts.put(Rank.FIRST, 1);

        LottoResult result = LottoResult.from(counts);

        assertThat(result.rankCounts().get(Rank.FIFTH)).isEqualTo(2);
        assertThat(result.rankCounts().get(Rank.MISS)).isZero();
        assertThat(result.totalReward()).isEqualTo(Rank.FIFTH.reward() * 2L + Rank.FIRST.reward());
    }

    @DisplayName("빈 결과는 모든 순위의 개수가 0이다.")
    @Test
    void emptyResultHasZeroCounts() {
        LottoResult result = LottoResult.empty();

        for (Rank rank : Rank.values()) {
            assertThat(result.rankCounts().get(rank)).isZero();
        }
    }
}
