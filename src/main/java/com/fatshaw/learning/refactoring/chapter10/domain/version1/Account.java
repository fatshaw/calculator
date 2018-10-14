package com.fatshaw.learning.refactoring.chapter10.domain.version1;

class Account {

    public double getFlowBetween(DateRange dateRange) {
        if (dateRange.getStart().equals(dateRange.getEnd())) {
            System.out.println("start=" + dateRange.getStart() + ",end=" + dateRange.getEnd());
        }
        return 0.1;
    }
}
