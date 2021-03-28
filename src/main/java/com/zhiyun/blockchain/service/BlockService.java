//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.service;

import com.zhiyun.blockchain.dao.BlockDAO;
import com.zhiyun.blockchain.pojo.Block;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class BlockService {
    @Autowired
    BlockDAO blockDAO;

    public BlockService() {
    }

    public List<Block> list() {
        Sort sort = Sort.by(Direction.DESC, new String[]{"height"});
        return this.blockDAO.findAll(sort);
    }

    public Block findByHeight(int height) {
        return (Block)this.blockDAO.getOne(height);
    }

    public Block findByBlockHash(String blockHash) {
        return this.blockDAO.findByBlockhash(blockHash);
    }

    public boolean uploadBlock(Block block) {
        this.blockDAO.save(block);
        return true;
    }

    public boolean addBlock(Block block) {
        this.blockDAO.save(block);
        return true;
    }
}
