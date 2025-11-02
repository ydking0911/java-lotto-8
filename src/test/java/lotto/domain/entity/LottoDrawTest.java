package lotto.domain.entity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoDrawTest {
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void duplicatedBonusNumber() {
        assertThatThrownBy(() -> new LottoDraw(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 결과를 계산한다.")
    @Test
    void matchResult() {
        LottoDraw draw = new LottoDraw(List.of(1, 2, 3, 4, 5, 6), 7);
        Rank rank = draw.match(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
