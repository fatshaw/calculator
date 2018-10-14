package com.fatshaw.learning.refactoring.chapter8.domain.version2;

public abstract class EmployeeType {

    abstract int payAmount();

    public int getManagerSalary() {
        return 20;
    }

    public int getSubsidiary() {
        return 2;
    }

    public int getMonthlySalary() {
        return 10;
    }

}
