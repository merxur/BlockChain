//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.web;

import com.zhiyun.blockchain.pojo.Input;
import com.zhiyun.blockchain.pojo.Output;
import com.zhiyun.blockchain.pojo.TX;
import com.zhiyun.blockchain.pojo.Transactions;
import com.zhiyun.blockchain.service.InputService;
import com.zhiyun.blockchain.service.OutputService;
import com.zhiyun.blockchain.service.TransactionsService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {
    @Autowired
    TransactionsService transactionsService;
    @Autowired
    InputService inputService;
    @Autowired
    OutputService outputService;

    public TransactionsController() {
    }

    @GetMapping({"/transactions"})
    public List<Transactions> list() throws Exception {
        return this.transactionsService.list();
    }

    @GetMapping({"/txbythash/{thash}"})
    public TX getByThash(@PathVariable("thash") String thash, HttpServletRequest request) throws Exception {
        Transactions transactions = this.transactionsService.findByThash(thash);
        List<Input> input = this.transactionsService.findTransactionInput(transactions);
        List<Output> output = this.transactionsService.findTransactionOutput(transactions);
        TX tx = new TX(transactions, input, output);
        return tx;
    }

    @GetMapping({"/txbyid/{id}"})
    public TX getByThash(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        Transactions transactions = this.transactionsService.findById(id);
        List<Input> input = this.transactionsService.findTransactionInput(transactions);
        List<Output> output = this.transactionsService.findTransactionOutput(transactions);
        TX tx = new TX(transactions, input, output);
        return tx;
    }

    @GetMapping({"/txbybhash/{bhash}"})
    public List<TX> getByBhash(@PathVariable("bhash") String bhash, HttpServletRequest request) throws Exception {
        List<Transactions> transactions = this.transactionsService.findByBhash(bhash);
        List<TX> txs = new ArrayList();
        System.out.println(txs);
        Iterator var5 = transactions.iterator();

        while(var5.hasNext()) {
            Transactions transaction = (Transactions)var5.next();
            List<Input> input = this.transactionsService.findTransactionInput(transaction);
            List<Output> output = this.transactionsService.findTransactionOutput(transaction);
            TX tx = new TX(transaction, input, output);
            txs.add(tx);
        }

        return txs;
    }

    @GetMapping({"/txbyaddress/{address}"})
    public List<TX> getByAddress(@PathVariable("address") String address, HttpServletRequest request) throws Exception {
        List<Input> inputs = this.inputService.findAllByAddress(address);
        List<Output> outputs = this.outputService.findAllByAddress(address);
        List<Transactions> transactions = new ArrayList();
        List<TX> txs = new ArrayList();
        boolean flag = false;
        Iterator var8 = inputs.iterator();

        Transactions transaction;
        Iterator var11;
        Transactions transactions1;
        TX tx;
        List inputs1;
        List outputs1;
        while(var8.hasNext()) {
            Input input = (Input)var8.next();
            transaction = this.transactionsService.findByThash(input.getTransactions().getThash());
            var11 = transactions.iterator();

            while(var11.hasNext()) {
                transactions1 = (Transactions)var11.next();
                if (transactions1.equals(transaction)) {
                    flag = true;
                }
            }

            if (flag) {
                flag = false;
            } else {
                inputs1 = this.transactionsService.findTransactionInput(transaction);
                outputs1 = this.transactionsService.findTransactionOutput(transaction);
                tx = new TX(transaction, inputs1, outputs1);
                transactions.add(transaction);
                txs.add(tx);
            }
        }

        var8 = outputs.iterator();

        while(var8.hasNext()) {
            Output output = (Output)var8.next();
            transaction = this.transactionsService.findByThash(output.getTransactions().getThash());
            var11 = transactions.iterator();

            while(var11.hasNext()) {
                transactions1 = (Transactions)var11.next();
                if (transactions1.equals(transaction)) {
                    flag = true;
                }
            }

            if (flag) {
                flag = false;
            } else {
                inputs1 = this.transactionsService.findTransactionInput(transaction);
                outputs1 = this.transactionsService.findTransactionOutput(transaction);
                tx = new TX(transaction, inputs1, outputs1);
                transactions.add(transaction);
                txs.add(tx);
            }
        }

        System.out.println(txs);
        return txs;
    }
}
