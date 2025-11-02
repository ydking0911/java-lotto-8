package lotto.domain.entity;

public enum Rank {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    MISS(0, false, 0L);

    private final int matchCount;
    private final boolean requiresBonus;
    private final long reward;

    Rank(int matchCount, boolean requiresBonus, long reward) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.reward = reward;
    }

    public static Rank valueOf(int matchCount, boolean bonusMatched) {
        for (Rank rank : values()) {
            if (!rank.isSameMatchCount(matchCount)) {
                continue;
            }
            if (rank.requiresBonus && !bonusMatched) {
                continue;
            }
            if (rank.isThirdRankCandidate(bonusMatched)) {
                continue;
            }
            return rank;
        }
        return MISS;
    }

    private boolean isSameMatchCount(int matchCount) {
        return this.matchCount == matchCount;
    }

    private boolean isThirdRankCandidate(boolean bonusMatched) {
        if (this.requiresBonus) {
            return false;
        }
        return this.matchCount == SECOND.matchCount && bonusMatched;
    }

    public long reward() {
        return reward;
    }

    public int matchCount() {
        return matchCount;
    }

    public boolean requiresBonus() {
        return requiresBonus;
    }
}
