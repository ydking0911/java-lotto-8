package lotto.ui.view;

import java.util.List;
import java.util.Map;

import lotto.application.dto.response.LottoPurchaseResponse;
import lotto.application.dto.response.LottoResultResponse;
import lotto.domain.entity.Rank;

public class OutputView {
    private static final List<Rank> DISPLAY_RANK_ORDER = List.of(
            Rank.FIFTH,
            Rank.FOURTH,
            Rank.THIRD,
            Rank.SECOND,
            Rank.FIRST
    );

    public void printPurchaseResult(LottoPurchaseResponse response) {
        System.out.printf("%d개를 구매했습니다.%n", response.ticketCount());
        for (List<Integer> ticket : response.tickets()) {
            System.out.println(ticket);
        }
    }

    public void printStatistics(LottoResultResponse response) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        Map<Rank, Integer> rankCounts = response.rankCounts();
        for (Rank rank : DISPLAY_RANK_ORDER) {
            Integer count = rankCounts.get(rank);
            printRank(rank, count);
        }
        System.out.printf("총 수익률은 %s%%입니다.%n", response.profitRate().toPlainString());
    }

    private void printRank(Rank rank, Integer count) {
        if (rank == Rank.SECOND) {
            printSecondRank(count);
            return;
        }
        System.out.printf("%d개 일치 (%s원) - %d개%n", rank.matchCount(), formatReward(rank), count);
    }

    private void printSecondRank(Integer count) {
        System.out.printf("5개 일치, 보너스 볼 일치 (%s원) - %d개%n", formatReward(Rank.SECOND), count);
    }

    private String formatReward(Rank rank) {
        return String.format("%,d", rank.reward());
    }
}
