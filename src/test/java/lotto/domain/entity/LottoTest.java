package lotto.domain.entity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("번호가 6개가 아니면 예외가 발생한다.")
    @Test
    void invalidSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("번호가 범위를 벗어나면 예외가 발생한다.")
    @Test
    void invalidRange() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("번호에 중복이 있으면 예외가 발생한다.")
    @Test
    void duplicatedNumbers() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호는 오름차순으로 정렬된다.")
    @Test
    void numbersAreSorted() {
        Lotto lotto = new Lotto(List.of(6, 3, 1, 5, 4, 2));
        assertThat(lotto.numbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("당첨 번호와 일치하는 개수를 계산한다.")
    @Test
    void countMatches() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int matches = lotto.countMatches(List.of(1, 2, 3, 11, 12, 13));
        assertThat(matches).isEqualTo(3);
    }

    @DisplayName("지정한 숫자를 포함하는지 판단한다.")
    @Test
    void containsNumber() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.contains(6)).isTrue();
    }
}
