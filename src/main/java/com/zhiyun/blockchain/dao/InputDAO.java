//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.dao;

import com.zhiyun.blockchain.pojo.Input;
import com.zhiyun.blockchain.pojo.Transactions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputDAO extends JpaRepository<Input, Integer> {
  List<Input> findAllByTransactions(Transactions transactions);

  List<Input> findAllByAddress(String address);
}
