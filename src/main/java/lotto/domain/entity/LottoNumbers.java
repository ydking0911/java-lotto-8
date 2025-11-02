package lotto.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class LottoNumbers {
    private static final int REQUIRED_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> values;

    private LottoNumbers(List<Integer> values) {
        this.values = values;
    }

    public static LottoNumbers from(List<Integer> numbers) {
        Objects.requireNonNull(numbers, "[ERROR] 로또 번호는 null일 수 없습니다.");
        List<Integer> copy = new ArrayList<>(numbers);
        validateSize(copy);
        validateRange(copy);
        validateDuplication(copy);
        Collections.sort(copy);
        return new LottoNumbers(Collections.unmodifiableList(copy));
    }

    public List<Integer> asList() {
        return values;
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() == REQUIRED_SIZE) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
    }

    private static void validateRange(List<Integer> numbers) {
        boolean invalid = numbers.stream()
                .anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);
        if (!invalid) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
    }

    private static void validateDuplication(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() == numbers.size()) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }
}
