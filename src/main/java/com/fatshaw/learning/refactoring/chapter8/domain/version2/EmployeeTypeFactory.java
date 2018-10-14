package com.fatshaw.learning.refactoring.chapter8.domain.version2;

public class EmployeeTypeFactory {

    public static EmployeeType createEmployee(int type) {
        switch (type) {
            case Employee.ENGINEER:
                return new EngineerEmployee();
            case Employee.ACCOUNT:
                return new AccountEmployee();
            case Employee.MANAGER:
                return new ManagerEmployee();
        }

        return null;
    }

}
