package lotto.application.usecase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.application.dto.request.LottoPurchaseRequest;
import lotto.application.dto.response.LottoPurchaseResponse;
import lotto.domain.entity.Lotto;
import lotto.domain.service.LottoPurchaseValidator;
import lotto.domain.service.LottoTicketGenerator;

class LottoPurchaseUseCaseTest {
    @DisplayName("구입 금액에 맞는 수량의 로또를 생성한다.")
    @Test
    void purchaseTickets() {
        LottoTicketGenerator generator = new FixedGenerator();
        LottoPurchaseValidator validator = new LottoPurchaseValidator();
        LottoPurchaseUseCase useCase = new LottoPurchaseUseCase(generator, validator);

        LottoPurchaseResponse response = useCase.purchase(new LottoPurchaseRequest(3_000));

        assertThat(response.ticketCount()).isEqualTo(3);
        assertThat(response.lottoTickets()).hasSize(3);
    }

    @DisplayName("금액이 1,000원 단위가 아니면 예외가 발생한다.")
    @Test
    void invalidAmount() {
        LottoTicketGenerator generator = new FixedGenerator();
        LottoPurchaseValidator validator = new LottoPurchaseValidator();
        LottoPurchaseUseCase useCase = new LottoPurchaseUseCase(generator, validator);

        assertThatThrownBy(() -> useCase.purchase(new LottoPurchaseRequest(1_500)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static class FixedGenerator extends LottoTicketGenerator {
        @Override
        public Lotto generate() {
            return new Lotto(List.of(1, 2, 3, 4, 5, 6));
        }
    }
}
