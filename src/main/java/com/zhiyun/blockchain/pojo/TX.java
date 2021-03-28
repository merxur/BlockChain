//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.pojo;

import java.util.List;

public class TX {
    Transactions transactions;
    List<Input> input;
    List<Output> output;

    public TX() {
    }

    public TX(Transactions transactions, List<Input> input, List<Output> output) {
        this.transactions = transactions;
        this.input = input;
        this.output = output;
    }

    public Transactions getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public List<Input> getInput() {
        return this.input;
    }

    public void setInput(List<Input> input) {
        this.input = input;
    }

    public List<Output> getOutput() {
        return this.output;
    }

    public void setOutput(List<Output> output) {
        this.output = output;
    }

    public String toString() {
        return "TX{transactions=" + this.transactions + ", input=" + this.input + ", output=" + this.output + '}';
    }
}
