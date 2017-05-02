package com.javarush.task.task38.task3809;

public class JavaRushBankAccount {
    private String ownerName;

    @LongPositive
    private long amount;

    public JavaRushBankAccount(final String ownerName) {
        this.ownerName = ownerName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(final long amount) {
        this.amount = amount;
    }
}
