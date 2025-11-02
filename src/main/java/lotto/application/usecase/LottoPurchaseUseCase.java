package lotto.application.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.application.dto.request.LottoPurchaseRequest;
import lotto.application.dto.response.LottoPurchaseResponse;
import lotto.domain.entity.Lotto;
import lotto.domain.service.LottoPurchaseValidator;
import lotto.domain.service.LottoTicketGenerator;

public class LottoPurchaseUseCase {
    private final LottoTicketGenerator ticketGenerator;
    private final LottoPurchaseValidator purchaseValidator;

    public LottoPurchaseUseCase(
            LottoTicketGenerator ticketGenerator,
            LottoPurchaseValidator purchaseValidator
    ) {
        this.ticketGenerator = ticketGenerator;
        this.purchaseValidator = purchaseValidator;
    }

    public LottoPurchaseResponse purchase(LottoPurchaseRequest request) {
        int amount = request.purchaseAmount();
        purchaseValidator.validateAmount(amount);
        int ticketCount = purchaseValidator.calculateTicketCount(amount);
        List<Lotto> tickets = generateTickets(ticketCount);
        return new LottoPurchaseResponse(ticketCount, toNumbers(tickets), tickets);
    }

    private List<Lotto> generateTickets(int ticketCount) {
        List<Lotto> tickets = new ArrayList<>();
        for (int index = 0; index < ticketCount; index++) {
            tickets.add(ticketGenerator.generate());
        }
        return tickets;
    }

    private List<List<Integer>> toNumbers(List<Lotto> tickets) {
        return tickets.stream()
                .map(Lotto::numbers)
                .collect(Collectors.toList());
    }
}
