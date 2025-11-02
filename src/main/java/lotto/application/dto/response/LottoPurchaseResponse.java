package lotto.application.dto.response;

import java.util.List;

import lotto.domain.entity.Lotto;

public class LottoPurchaseResponse {
    private final int ticketCount;
    private final List<List<Integer>> tickets;
    private final List<Lotto> lottoTickets;

    public LottoPurchaseResponse(int ticketCount, List<List<Integer>> tickets, List<Lotto> lottoTickets) {
        this.ticketCount = ticketCount;
        this.tickets = tickets;
        this.lottoTickets = lottoTickets;
    }

    public int ticketCount() {
        return ticketCount;
    }

    public List<List<Integer>> tickets() {
        return tickets;
    }

    public List<Lotto> lottoTickets() {
        return lottoTickets;
    }
}
