package com.fatshaw.learning.calculator.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TransactionResult {

    Transaction transaction;
}
