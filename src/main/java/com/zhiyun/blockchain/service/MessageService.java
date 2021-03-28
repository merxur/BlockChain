//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.service;

import com.zhiyun.blockchain.dao.MessageDAO;
import com.zhiyun.blockchain.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;

    public MessageService() {
    }

    public Message getMessage() {
        return this.messageDAO.findById(1);
    }

    public boolean updateMessage(Message message) {
        this.messageDAO.save(message);
        return true;
    }
}
