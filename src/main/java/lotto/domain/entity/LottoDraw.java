package lotto.domain.entity;

import java.util.List;

public class LottoDraw {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public LottoDraw(List<Integer> winningNumbers, int bonusNumber) {
        LottoNumbers validated = LottoNumbers.from(winningNumbers);
        validateBonusNumber(bonusNumber, validated.asList());
        this.winningNumbers = validated.asList();
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto ticket) {
        int matchCount = ticket.countMatches(winningNumbers);
        boolean bonusMatched = ticket.contains(bonusNumber);
        return Rank.valueOf(matchCount, bonusMatched);
    }

    public List<Integer> winningNumbers() {
        return winningNumbers;
    }

    public int bonusNumber() {
        return bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber, List<Integer> numbers) {
        validateRange(bonusNumber);
        validateDuplication(bonusNumber, numbers);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber >= 1 && bonusNumber <= 45) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
    }

    private void validateDuplication(int bonusNumber, List<Integer> numbers) {
        if (!numbers.contains(bonusNumber)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
