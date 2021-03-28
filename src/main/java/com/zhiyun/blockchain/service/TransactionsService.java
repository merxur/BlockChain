//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.service;

import com.zhiyun.blockchain.dao.InputDAO;
import com.zhiyun.blockchain.dao.OutputDAO;
import com.zhiyun.blockchain.dao.TransactionsDAO;
import com.zhiyun.blockchain.pojo.Block;
import com.zhiyun.blockchain.pojo.Input;
import com.zhiyun.blockchain.pojo.Output;
import com.zhiyun.blockchain.pojo.Transactions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {
    @Autowired
    TransactionsDAO transactionsDAO;
    @Autowired
    InputDAO inputDAO;
    @Autowired
    OutputDAO outputDAO;

    public TransactionsService() {
    }

    public boolean add(Transactions transactions) {
        this.transactionsDAO.save(transactions);
        return true;
    }

    public boolean upload(Transactions transactions) {
        this.transactionsDAO.save(transactions);
        return true;
    }

    public List<Transactions> findByBhash(String bhash) {
        Sort sort = Sort.by(Direction.DESC, new String[]{"id"});
        return this.transactionsDAO.findAllByBhash(bhash, sort);
    }

    public Transactions findByThash(String thash) {
        return this.transactionsDAO.findByThash(thash);
    }

    public Transactions findById(int id) {
        return this.transactionsDAO.findById(id);
    }

    public List<Input> findTransactionInput(Transactions transactions) {
        return this.inputDAO.findAllByTransactions(transactions);
    }

    public List<Output> findTransactionOutput(Transactions transactions) {
        return this.outputDAO.findAllByTransactions(transactions);
    }

    public List<Transactions> list() {
        Sort sort = Sort.by(Direction.DESC, new String[]{"id"});
        return this.transactionsDAO.findAll(sort);
    }

    public List<Transactions> findByBlock(Block block) {
        return this.transactionsDAO.findByBlock(block);
    }
}
