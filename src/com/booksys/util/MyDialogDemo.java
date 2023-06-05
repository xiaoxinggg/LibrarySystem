package com.booksys.util;

import javax.swing.*;
import java.awt.*;

/**
 * 提示信息窗体设置
 */
public class MyDialogDemo  extends JDialog {
    public MyDialogDemo( String text) {
        Font font = new Font("黑体", Font.BOLD, 20);
        this.setBounds(500,450,300,200);

        //获得一个容器
        JLabel label = new JLabel(text);

        //设置标签居中
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(font);

        Container container = this.getContentPane();
        container.setBackground(new Color(103, 224, 236));
        container.add(label);
        this.setVisible(true);
    }
}
