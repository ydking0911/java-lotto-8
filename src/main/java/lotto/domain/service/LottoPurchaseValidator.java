package lotto.domain.service;

public class LottoPurchaseValidator {
    private static final int LOTTO_PRICE = 1_000;

    public void validateAmount(int amount) {
        if (amount >= LOTTO_PRICE) {
            validateMultiple(amount);
            return;
        }
        throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 이상이어야 합니다.");
    }

    private void validateMultiple(int amount) {
        if (amount % LOTTO_PRICE == 0) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다.");
    }

    public int calculateTicketCount(int amount) {
        return amount / LOTTO_PRICE;
    }
}

