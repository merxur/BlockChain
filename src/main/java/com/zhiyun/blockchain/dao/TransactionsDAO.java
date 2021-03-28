//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.dao;

import com.zhiyun.blockchain.pojo.Block;
import com.zhiyun.blockchain.pojo.Transactions;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsDAO extends JpaRepository<Transactions, Integer> {
  List<Transactions> findAllByBhash(String bhash, Sort sort);

  Transactions findByThash(String thash);

  Transactions findById(int id);

  List<Transactions> findByBlock(Block block);
}
