package lotto.domain.entity;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final Map<Rank, Integer> counts;

    private LottoResult(Map<Rank, Integer> counts) {
        this.counts = counts;
    }

    public static LottoResult from(Map<Rank, Integer> rankCounts) {
        Objects.requireNonNull(rankCounts, "[ERROR] 당첨 결과는 null일 수 없습니다.");
        EnumMap<Rank, Integer> copiedCounts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            copiedCounts.put(rank, rankCounts.getOrDefault(rank, 0));
        }
        return new LottoResult(Collections.unmodifiableMap(copiedCounts));
    }

    public static LottoResult empty() {
        EnumMap<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
        return new LottoResult(Collections.unmodifiableMap(counts));
    }

    public Map<Rank, Integer> rankCounts() {
        return counts;
    }

    public long totalReward() {
        return counts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().reward() * entry.getValue())
                .sum();
    }
}
