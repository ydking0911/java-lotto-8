package lotto.domain.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.domain.entity.Lotto;
import lotto.domain.entity.LottoDraw;
import lotto.domain.entity.LottoResult;
import lotto.domain.entity.Rank;

public class LottoResultService {
    public LottoResult summarize(LottoDraw draw, List<Lotto> tickets) {
        Map<Rank, Integer> counts = initializeCounts();
        for (Lotto ticket : tickets) {
            Rank rank = draw.match(ticket);
            counts.computeIfPresent(rank, (key, value) -> value + 1);
        }
        return LottoResult.from(counts);
    }

    private Map<Rank, Integer> initializeCounts() {
        EnumMap<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
        return counts;
    }
}
