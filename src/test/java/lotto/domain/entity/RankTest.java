package lotto.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
    @DisplayName("보너스 공이 일치하면 2등을 반환한다.")
    @Test
    void secondRank() {
        Rank rank = Rank.valueOf(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("보너스 공이 불일치하면 3등을 반환한다.")
    @Test
    void thirdRank() {
        Rank rank = Rank.valueOf(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("매칭 수가 부족하면 미스이다.")
    @Test
    void missRank() {
        Rank rank = Rank.valueOf(2, false);
        assertThat(rank).isEqualTo(Rank.MISS);
    }
}
