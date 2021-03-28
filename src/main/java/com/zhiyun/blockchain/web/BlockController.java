//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.web;

import com.zhiyun.blockchain.pojo.Block;
import com.zhiyun.blockchain.pojo.Transactions;
import com.zhiyun.blockchain.service.BlockService;
import com.zhiyun.blockchain.service.TransactionsService;
import com.zhiyun.blockchain.tools.Tools;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockController {
    @Autowired
    BlockService blockService;
    @Autowired
    TransactionsService transactionsService;

    public BlockController() {
    }

    @GetMapping({"/blocks"})
    public List<Block> list() throws Exception {
        return this.blockService.list();
    }

    @GetMapping({"/block/{height}"})
    public Block getByHeight(@PathVariable("height") String height, HttpServletRequest request) throws Exception {
        if (Tools.isInteger(height)) {
            return this.blockService.findByHeight(Integer.valueOf(height));
        } else {
            return height.length() == 64 ? this.blockService.findByBlockHash(height) : null;
        }
    }

    @GetMapping({"/block_transactions/{height}"})
    public List<Transactions> list(@PathVariable("height") int height, HttpServletRequest request) throws Exception {
        Block block = this.blockService.findByHeight(height);
        return this.transactionsService.findByBhash(block.getBlockhash());
    }
}
