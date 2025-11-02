package lotto.ui.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return attemptReadAmount();
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return attemptReadWinningNumbers();
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return attemptReadBonusNumber();
    }

    private int attemptReadAmount() {
        try {
            String input = Console.readLine();
            return parseInt(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readPurchaseAmount();
        }
    }

    private List<Integer> attemptReadWinningNumbers() {
        try {
            String input = Console.readLine();
            return parseNumbers(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readWinningNumbers();
        }
    }

    private int attemptReadBonusNumber() {
        try {
            String input = Console.readLine();
            return parseInt(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readBonusNumber();
        }
    }

    private int parseInt(String input) {
        String trimmed = input.trim();
        if (!trimmed.isEmpty()) {
            return parseNumber(trimmed);
        }
        throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
    }

    private int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
    }

    private List<Integer> parseNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            numbers.add(parseInt(token));
        }
        return numbers;
    }
}
