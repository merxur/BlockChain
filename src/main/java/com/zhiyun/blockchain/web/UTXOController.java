//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.web;

import com.zhiyun.blockchain.pojo.UTXO;
import com.zhiyun.blockchain.service.UTXOService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UTXOController {
    @Autowired
    UTXOService utxoService;

    public UTXOController() {
    }

    @GetMapping({"/UTXO"})
    public List<UTXO> list() throws Exception {
        return this.utxoService.list();
    }

    @PostMapping({"/addUTXO"})
    public boolean add(@RequestBody UTXO utxo) throws Exception {
        return this.utxoService.addUTXO(utxo);
    }

    @DeleteMapping({"/deleteUTXO/{address}"})
    public boolean delete(@PathVariable("address") String address, HttpServletRequest request) throws Exception {
        return this.utxoService.deleteUTXO(address);
    }
}
