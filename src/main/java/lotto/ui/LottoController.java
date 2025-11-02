package lotto.ui;

import java.util.List;

import lotto.application.dto.request.LottoPurchaseRequest;
import lotto.application.dto.request.LottoResultRequest;
import lotto.application.dto.response.LottoPurchaseResponse;
import lotto.application.dto.response.LottoResultResponse;
import lotto.application.usecase.LottoPurchaseUseCase;
import lotto.application.usecase.LottoResultUseCase;
import lotto.domain.entity.Lotto;
import lotto.ui.view.InputView;
import lotto.ui.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseUseCase purchaseUseCase;
    private final LottoResultUseCase resultUseCase;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoPurchaseUseCase purchaseUseCase,
            LottoResultUseCase resultUseCase
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.purchaseUseCase = purchaseUseCase;
        this.resultUseCase = resultUseCase;
    }

    public void run() {
        LottoPurchaseResponse purchaseResponse = purchase();
        outputView.printPurchaseResult(purchaseResponse);
        LottoResultResponse resultResponse = calculateResult(purchaseResponse.lottoTickets());
        outputView.printStatistics(resultResponse);
    }

    private LottoPurchaseResponse purchase() {
        try {
            int amount = inputView.readPurchaseAmount();
            LottoPurchaseRequest request = new LottoPurchaseRequest(amount);
            return purchaseUseCase.purchase(request);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return purchase();
        }
    }

    private LottoResultResponse calculateResult(List<Lotto> tickets) {
        try {
            List<Integer> winningNumbers = inputView.readWinningNumbers();
            int bonusNumber = inputView.readBonusNumber();
            LottoResultRequest request = new LottoResultRequest(winningNumbers, bonusNumber);
            return resultUseCase.calculate(request, tickets);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return calculateResult(tickets);
        }
    }
}
