package lotto.domain.service;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.entity.Lotto;

public class LottoTicketGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;

    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT);
        return new Lotto(numbers);
    }
}
