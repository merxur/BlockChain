//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "transactions"
)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Transactions {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id"
    )
    int id;
    String bhash;
    String thash;
    @ManyToOne
    @JoinColumn(
            name = "height"
    )
    Block block;
    int size;
    double input;
    double output;
    double fees;

    public Transactions() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBhash() {
        return this.bhash;
    }

    public void setBhash(String bhash) {
        this.bhash = bhash;
    }

    public String getThash() {
        return this.thash;
    }

    public void setThash(String thash) {
        this.thash = thash;
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getInput() {
        return this.input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public double getOutput() {
        return this.output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getFees() {
        return this.fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public String toString() {
        return "Transactions{id=" + this.id + ", bhash='" + this.bhash + '\'' + ", thash='" + this.thash + '\'' + ", block=" + this.block + ", size=" + this.size + ", input=" + this.input + ", output=" + this.output + ", fees=" + this.fees + '}';
    }
}
