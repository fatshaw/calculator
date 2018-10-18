package com.fatshaw.learning.calculator.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Transaction {

    List<BigDecimal> valuesSnapshot;
    List<BigDecimal> removedNumbers;
    List<BigDecimal> addedNumbers;
}
