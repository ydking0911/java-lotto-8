package lotto.application.dto.request;

import java.util.List;

public class LottoResultRequest {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public LottoResultRequest(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> winningNumbers() {
        return winningNumbers;
    }

    public int bonusNumber() {
        return bonusNumber;
    }
}
