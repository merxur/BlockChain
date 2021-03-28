//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.web;

import com.zhiyun.blockchain.pojo.Block;
import com.zhiyun.blockchain.pojo.Input;
import com.zhiyun.blockchain.pojo.Message;
import com.zhiyun.blockchain.pojo.Output;
import com.zhiyun.blockchain.pojo.TX;
import com.zhiyun.blockchain.pojo.Transactions;
import com.zhiyun.blockchain.pojo.UTXO;
import com.zhiyun.blockchain.service.BlockService;
import com.zhiyun.blockchain.service.InputService;
import com.zhiyun.blockchain.service.MessageService;
import com.zhiyun.blockchain.service.OutputService;
import com.zhiyun.blockchain.service.TransactionsService;
import com.zhiyun.blockchain.service.UTXOService;
import com.zhiyun.blockchain.tools.RSAUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    @Autowired
    UTXOService utxoService;
    @Autowired
    OutputService outputService;
    @Autowired
    InputService inputService;
    @Autowired
    TransactionsService transactionsService;
    @Autowired
    MessageService messageService;
    @Autowired
    BlockService blockService;

    public WalletController() {
    }

    @GetMapping({"/registerKey"})
    public HashMap<String, String> registerKey() throws Exception {
        HashMap<String, String> map = RSAUtil.generateKeyPair();
        map.put("address", RSAUtil.getSHA256((String)map.get("public")));
        map.remove("public");
        System.out.println((String)map.get("private"));
        return map;
    }

    @PostMapping({"/loginByPrivate"})
    public Object login(@RequestBody String privateKeyString, HttpSession session) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String privateKey = privateKeyString.substring(1, privateKeyString.length() - 1);
        System.out.println(privateKey);
        String publicKey = RSAUtil.private2public(privateKey);
        String address = RSAUtil.getSHA256(publicKey);
        double balance = this.utxoService.balanceByUTXO(address);
        session.setAttribute("address", address);
        session.setAttribute("balance", balance);
        return address;
    }

    @PostMapping({"/addTransaction"})
    public String addTransaction(@RequestBody TX object, HttpSession session) {
        Message message = this.messageService.getMessage();
        Transactions transactions = new Transactions();
        Block block = message.getBlock();
        transactions.setBlock(block);
        int size = 0;
        int input = 0;
        int output = 0;
        String Address = "";
        String address = "";

        Iterator var11;
        Input in;
        for(var11 = object.getInput().iterator(); var11.hasNext(); Address = Address + in.getAddress()) {
            in = (Input)var11.next();
            ++size;
            input = (int)((double)input + in.getMoney());
        }

        address = ((Input)object.getInput().get(0)).getAddress();
        System.out.println(object.getOutput());

        Output out;
        for(var11 = object.getOutput().iterator(); var11.hasNext(); Address = Address + out.getAddress()) {
            out = (Output)var11.next();
            ++size;
            output = (int)((double)output + out.getMoney());
        }

        transactions.setSize(size);
        transactions.setInput((double)input);
        transactions.setOutput((double)output);
        transactions.setFees((double)(input - output));
        String thash = RSAUtil.getSHA256("height" + transactions.getBlock().getHeight() + "size" + transactions.getSize() + "input" + transactions.getInput() + "output" + transactions.getOutput() + "fees" + transactions.getFees() + "Address" + Address);
        transactions.setThash(thash);
        transactions.setBhash(block.getBlockhash());
        this.transactionsService.add(transactions);
        if (object.getInput().size() == 0) {
            out = (Output)object.getOutput().get(0);
            out.setTransactions(transactions);
            this.outputService.add(out);
            UTXO utxo = new UTXO();
            utxo.setInput((String)null);
            utxo.setOutput(out.getAddress());
            utxo.setTransactions(transactions);
            this.utxoService.addUTXO(utxo);
        } else {
            in = (Input)object.getInput().get(0);
            in.setTransactions(transactions);
            this.inputService.add(in);
            this.utxoService.deleteUTXO(in.getAddress());
            Iterator var18 = object.getOutput().iterator();

            while(var18.hasNext()) {
                out = (Output)var18.next();
                out.setTransactions(transactions);
                this.outputService.add(out);
                UTXO utxo = new UTXO();
                utxo.setInput(in.getAddress());
                utxo.setOutput(out.getAddress());
                utxo.setTransactions(transactions);
                this.utxoService.addUTXO(utxo);
            }
        }

        block.setSize(block.getSize() + transactions.getSize());
        block.setTxcount(block.getTxcount() + 1);
        block.setBits(block.getBits() + input + output);
        this.blockService.uploadBlock(block);
        session.removeAttribute("balance");
        session.removeAttribute("address");
        session.setAttribute("address", address);
        double balance = this.utxoService.balanceByUTXO(address);
        session.setAttribute("balance", balance);
        return "redirect:wallet";
    }

    @PostMapping({"/getMessage"})
    public Map<String, String> getMessage(@RequestBody String address, HttpSession session) {
        Message message = this.messageService.getMessage();
        Block block = message.getBlock();
        List<Transactions> transactions = this.transactionsService.findByBlock(block);
        Transactions miningTransaction = new Transactions();
        miningTransaction.setBhash(block.getBlockhash());
        miningTransaction.setBlock(block);
        miningTransaction.setSize(1);
        miningTransaction.setInput(0.0D);
        new Output();
        double fees = 0.0D;
        List<String> merkleTree = new ArrayList();

        Transactions transaction;
        for(Iterator var11 = transactions.iterator(); var11.hasNext(); fees += transaction.getFees()) {
            transaction = (Transactions)var11.next();
            merkleTree.add(transaction.getThash());
        }

        miningTransaction.setOutput(50.0D + fees);
        miningTransaction.setFees(0.0D);
        String thash = RSAUtil.getSHA256("height" + miningTransaction.getBlock().getHeight() + "size" + miningTransaction.getSize() + "input" + miningTransaction.getInput() + "output" + miningTransaction.getOutput() + "fees" + miningTransaction.getFees() + "Address" + address);
        miningTransaction.setThash(thash);
        merkleTree.add(thash);

        while(merkleTree.size() > 1) {
            List<String> newTree = new ArrayList();

            for(int i = 0; i < merkleTree.size(); i += 2) {
                if (i == merkleTree.size() - 1) {
                    newTree.add(RSAUtil.getSHA256((String)merkleTree.get(i)));
                } else {
                    newTree.add(RSAUtil.getSHA256((String)merkleTree.get(i) + (String)merkleTree.get(i + 1)));
                }
            }

            merkleTree = newTree;
        }

        address = address.substring(1, address.length() - 1);
        block.setMerkleroot((String)merkleTree.get(0));
        this.blockService.uploadBlock(block);
        Map<String, String> map = new HashMap();
        map.put("hash", block.getMerkleroot() + block.getPrevhash() + address + block.getBits() + block.getTxcount() + block.getSize());
        map.put("difficulty", String.valueOf(block.getDifficulty()));
        return map;
    }

    @PostMapping({"/uploadMining"})
    public String mining(@RequestBody Map<String, String> map, HttpSession session) {
        String address = (String)map.get("address");
        int nonce = Integer.parseInt((String)map.get("nonce"));
        Message message = this.messageService.getMessage();
        Block block = message.getBlock();
        block.setNonce(nonce);
        Timestamp timestamp = new Timestamp((new Date()).getTime());
        block.setReplayed(address);
        block.setBlockhash(RSAUtil.getSHA256(block.getMerkleroot() + block.getPrevhash() + address + block.getBits() + block.getTxcount() + block.getSize() + nonce));
        System.out.println(block.getBlockhash());
        if (block.getHeight() > 1) {
            Block prevBlock = this.blockService.findByHeight(block.getHeight() - 1);
            prevBlock.setNexthash(block.getBlockhash());
            this.blockService.uploadBlock(prevBlock);
        }

        List<Transactions> transactions = this.transactionsService.findByBlock(block);
        Transactions miningTransaction = new Transactions();
        miningTransaction.setBhash(block.getBlockhash());
        miningTransaction.setBlock(block);
        miningTransaction.setSize(1);
        miningTransaction.setInput(0.0D);
        Output miningOutput = new Output();
        double fees = 0.0D;
        Iterator var13 = transactions.iterator();

        while(var13.hasNext()) {
            Transactions transaction = (Transactions)var13.next();
            transaction.setBhash(block.getBlockhash());
            fees += transaction.getFees();
            this.transactionsService.upload(transaction);
        }

        miningTransaction.setOutput(50.0D + fees);
        miningTransaction.setFees(0.0D);
        String thash = RSAUtil.getSHA256("height" + miningTransaction.getBlock().getHeight() + "size" + miningTransaction.getSize() + "input" + miningTransaction.getInput() + "output" + miningTransaction.getOutput() + "fees" + miningTransaction.getFees() + "Address" + address);
        miningTransaction.setThash(thash);
        this.transactionsService.add(miningTransaction);
        miningOutput.setAddress(address);
        miningOutput.setMoney(50.0D + fees);
        miningOutput.setTransactions(miningTransaction);
        this.outputService.add(miningOutput);
        UTXO miningUTXO = new UTXO();
        miningUTXO.setInput("");
        miningUTXO.setOutput(address);
        miningUTXO.setTransactions(miningTransaction);
        this.utxoService.addUTXO(miningUTXO);
        this.blockService.uploadBlock(block);
        Block newBlock = new Block();
        newBlock.setSize(1);
        newBlock.setTxcount(1);
        newBlock.set_version("1");
        if ((System.currentTimeMillis() - block.get_time().getTime()) / 60000L > 1L) {
            newBlock.setDifficulty(block.getDifficulty() - 1);
        } else {
            newBlock.setDifficulty(block.getDifficulty() + 1);
        }

        newBlock.setBits(50);
        newBlock.setNonce(0);
        newBlock.setReplayed("0");
        newBlock.set_time(timestamp);
        newBlock.setBlockhash("0");
        newBlock.setPrevhash(block.getBlockhash());
        newBlock.setNexthash("0");
        newBlock.setMerkleroot("0");
        this.blockService.addBlock(newBlock);
        message.setBlock(newBlock);
        this.messageService.updateMessage(message);
        session.removeAttribute("balance");
        session.removeAttribute("address");
        double balance = this.utxoService.balanceByUTXO(address);
        session.setAttribute("address", address);
        session.setAttribute("balance", balance);
        return "redirect:wallet";
    }
}
