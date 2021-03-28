//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.service;

import com.zhiyun.blockchain.dao.InputDAO;
import com.zhiyun.blockchain.pojo.Input;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputService {
    @Autowired
    InputDAO inputDAO;

    public InputService() {
    }

    public List<Input> findAllByAddress(String address) {
        return this.inputDAO.findAllByAddress(address);
    }

    public boolean add(Input input) {
        this.inputDAO.save(input);
        return true;
    }
}
