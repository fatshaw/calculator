package com.fatshaw.learning.refactoring.chapter8.domain.version2;

class Employee {

    public static final int ENGINEER = 0;
    public static final int ACCOUNT = 1;
    public static final int MANAGER = 2;

    private EmployeeType employeeType;

    public Employee(int type) {
        employeeType = EmployeeTypeFactory.createEmployee(type);
    }

    public int payAmount() {
        return employeeType.payAmount();
    }

}
