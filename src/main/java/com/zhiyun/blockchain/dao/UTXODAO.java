//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.dao;

import com.zhiyun.blockchain.pojo.UTXO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UTXODAO extends JpaRepository<UTXO, Integer> {
  List<UTXO> getAllByOutput(String output);
}
