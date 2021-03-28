//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.dao;

import com.zhiyun.blockchain.pojo.Output;
import com.zhiyun.blockchain.pojo.Transactions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputDAO extends JpaRepository<Output, Integer> {
  List<Output> findAllByTransactions(Transactions transactions);

  List<Output> findAllByAddress(String address);

  Output findByTransactionsAndAddress(Transactions transactions, String address);
}
