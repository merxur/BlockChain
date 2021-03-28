//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.tools;

import java.awt.Component;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

public class PortUtil {
    public PortUtil() {
    }

    public static boolean testPort(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            ss.close();
            return false;
        } catch (BindException var2) {
            return true;
        } catch (IOException var3) {
            return true;
        }
    }

    public static void checkPort(int port, String server, boolean shutdown) {
        if (!testPort(port)) {
            String message;
            if (shutdown) {
                message = String.format("在端口 %d 未检查得到 %s 启动%n", port, server);
                JOptionPane.showMessageDialog((Component)null, message);
                System.exit(1);
            } else {
                message = String.format("在端口 %d 未检查得到 %s 启动%n,是否继续?", port, server);
                if (0 != JOptionPane.showConfirmDialog((Component)null, message)) {
                    System.exit(1);
                }
            }
        }

    }
}
