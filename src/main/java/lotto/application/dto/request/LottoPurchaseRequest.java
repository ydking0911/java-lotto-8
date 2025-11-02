package lotto.application.dto.request;

public class LottoPurchaseRequest {
    private final int purchaseAmount;

    public LottoPurchaseRequest(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int purchaseAmount() {
        return purchaseAmount;
    }
}
