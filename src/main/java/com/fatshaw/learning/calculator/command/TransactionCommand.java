package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.transaction.Trade;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TransactionCommand {

    Trade<BigDecimal> calculator;
    String token;
    int pos;
}
