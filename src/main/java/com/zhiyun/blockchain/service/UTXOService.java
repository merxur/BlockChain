//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.service;

import com.zhiyun.blockchain.dao.OutputDAO;
import com.zhiyun.blockchain.dao.TransactionsDAO;
import com.zhiyun.blockchain.dao.UTXODAO;
import com.zhiyun.blockchain.pojo.Output;
import com.zhiyun.blockchain.pojo.UTXO;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UTXOService {
    @Autowired
    UTXODAO utxodao;
    @Autowired
    OutputDAO outputDAO;
    @Autowired
    TransactionsDAO transactionsDAO;

    public UTXOService() {
    }

    public boolean addUTXO(UTXO utxo) {
        this.utxodao.save(utxo);
        return true;
    }

    public boolean deleteUTXO(String address) {
        List<UTXO> utxos = this.utxodao.getAllByOutput(address);
        Iterator var3 = utxos.iterator();

        while(var3.hasNext()) {
            UTXO utxo = (UTXO)var3.next();
            this.utxodao.delete(utxo);
        }

        return true;
    }

    public List<UTXO> list() {
        return this.utxodao.findAll();
    }

    public double balanceByUTXO(String address) {
        List<UTXO> utxos = this.utxodao.getAllByOutput(address);
        double balance = 0.0D;
        System.out.println(utxos);
        Output output;
        if (utxos.size() > 0) {
            for(Iterator var5 = utxos.iterator(); var5.hasNext(); balance += output.getMoney()) {
                UTXO utxo = (UTXO)var5.next();
                output = this.outputDAO.findByTransactionsAndAddress(utxo.getTransactions(), utxo.getOutput());
            }
        }

        return balance;
    }
}
