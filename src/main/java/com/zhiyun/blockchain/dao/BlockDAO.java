//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.dao;

import com.zhiyun.blockchain.pojo.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockDAO extends JpaRepository<Block, Integer> {
  Block findByBlockhash(String blockhash);
}
