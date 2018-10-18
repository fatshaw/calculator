package com.fatshaw.learning.calculator.command;

import com.fatshaw.learning.calculator.domain.transaction.Transaction;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CommandResult {

    Transaction<BigDecimal> transaction;
}
