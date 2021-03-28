//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.service;

import com.zhiyun.blockchain.dao.OutputDAO;
import com.zhiyun.blockchain.pojo.Output;
import com.zhiyun.blockchain.pojo.Transactions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputService {
    @Autowired
    OutputDAO outputDAO;

    public OutputService() {
    }

    public List<Output> findAllByTransactions(Transactions transactions) {
        return this.outputDAO.findAllByTransactions(transactions);
    }

    public List<Output> findAllByAddress(String address) {
        return this.outputDAO.findAllByAddress(address);
    }

    public Output findByTransactionsAndAddress(Transactions transactions, String address) {
        return this.outputDAO.findByTransactionsAndAddress(transactions, address);
    }

    public boolean add(Output output) {
        this.outputDAO.save(output);
        return true;
    }
}
