package lotto.domain.entity;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoNumbers validatedNumbers = LottoNumbers.from(numbers);
        this.numbers = validatedNumbers.asList();
    }

    public List<Integer> numbers() {
        return numbers;
    }

    public int countMatches(List<Integer> values) {
        return (int) numbers.stream()
                .filter(values::contains)
                .count();
    }

    public boolean contains(int value) {
        return numbers.contains(value);
    }
}
