package com.fatshaw.learning.refactoring.chapter8.domain.version1;

class Employee {

    public static final int ENGINEER = 0;
    public static final int ACCOUNT = 1;
    public static final int MANAGER = 2;

    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int payAmount() {
        switch (type) {
            case ENGINEER:
                return getMonthlySalary();
            case ACCOUNT:
                return getMonthlySalary() + getSubsidiary();
            case MANAGER:
                return getMonthlySalary() + getManagerSalary();
        }
        return 0;
    }

    private int getManagerSalary() {
        return 20;
    }

    private int getSubsidiary() {
        return 2;
    }

    private int getMonthlySalary() {
        return 10;
    }


}
