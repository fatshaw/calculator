package com.fatshaw.learning.calculator.domain.trade;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Transaction<T> {

    List<T> valuesSnapshot;
    List<T> removedNumbers;
    List<T> addedNumbers;
}
