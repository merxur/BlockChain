//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "block"
)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Block {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "height"
    )
    int height;
    int size;
    int txcount;
    String _version;
    int difficulty;
    int bits;
    int nonce;
    String replayed;
    Timestamp _time;
    String blockhash;
    String prevhash;
    String nexthash;
    String merkleroot;

    public Block() {
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTxcount() {
        return this.txcount;
    }

    public void setTxcount(int txcount) {
        this.txcount = txcount;
    }

    public String get_version() {
        return this._version;
    }

    public void set_version(String _version) {
        this._version = _version;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getBits() {
        return this.bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public int getNonce() {
        return this.nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getReplayed() {
        return this.replayed;
    }

    public void setReplayed(String replayed) {
        this.replayed = replayed;
    }

    public Timestamp get_time() {
        return this._time;
    }

    public void set_time(Timestamp _time) {
        this._time = _time;
    }

    public String getBlockhash() {
        return this.blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public String getPrevhash() {
        return this.prevhash;
    }

    public void setPrevhash(String prevhash) {
        this.prevhash = prevhash;
    }

    public String getNexthash() {
        return this.nexthash;
    }

    public void setNexthash(String nexthash) {
        this.nexthash = nexthash;
    }

    public String getMerkleroot() {
        return this.merkleroot;
    }

    public void setMerkleroot(String merkleroot) {
        this.merkleroot = merkleroot;
    }

    public String toString() {
        return "Block{height=" + this.height + ", size=" + this.size + ", txcount=" + this.txcount + ", _version='" + this._version + '\'' + ", difficulty=" + this.difficulty + ", bits=" + this.bits + ", nonce=" + this.nonce + ", replayed='" + this.replayed + '\'' + ", _time=" + this._time + ", blockhash='" + this.blockhash + '\'' + ", prevhash='" + this.prevhash + '\'' + ", nexthash='" + this.nexthash + '\'' + ", merkleroot='" + this.merkleroot + '\'' + '}';
    }
}
