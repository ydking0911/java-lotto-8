package lotto;

import lotto.application.usecase.LottoPurchaseUseCase;
import lotto.application.usecase.LottoResultUseCase;
import lotto.domain.service.LottoProfitCalculator;
import lotto.domain.service.LottoPurchaseValidator;
import lotto.domain.service.LottoResultService;
import lotto.domain.service.LottoTicketGenerator;
import lotto.ui.LottoController;
import lotto.ui.view.InputView;
import lotto.ui.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoTicketGenerator generator = new LottoTicketGenerator();
        LottoPurchaseValidator purchaseValidator = new LottoPurchaseValidator();
        LottoProfitCalculator profitCalculator = new LottoProfitCalculator();
        LottoResultService resultService = new LottoResultService();
        LottoPurchaseUseCase purchaseUseCase = new LottoPurchaseUseCase(generator, purchaseValidator);
        LottoResultUseCase resultUseCase = new LottoResultUseCase(profitCalculator, resultService);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoController controller = new LottoController(inputView, outputView, purchaseUseCase, resultUseCase);
        controller.run();
    }
}
