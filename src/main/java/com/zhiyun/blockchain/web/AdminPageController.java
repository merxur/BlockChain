//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    public AdminPageController() {
    }

    @GetMapping({"/home"})
    public String home() {
        return "brower/home";
    }

    @GetMapping({"/block"})
    public String block() {
        return "brower/block";
    }

    @GetMapping({"/transaction"})
    public String transaction() {
        return "brower/transaction";
    }

    @GetMapping({"/address"})
    public String address() {
        return "brower/address";
    }

    @GetMapping({"/login"})
    public String login() {
        return "wallet/login";
    }

    @GetMapping({"/wallet"})
    public String wallet() {
        return "wallet/wallet";
    }

    @GetMapping({"/logout"})
    public String logout(HttpSession session) {
        session.removeAttribute("address");
        return "redirect:home";
    }
}
