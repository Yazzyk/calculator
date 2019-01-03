package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    public JButton[] jbt = new JButton[20];
    String[] str = {"1", "2", "3", "+", "C", "4", "5", "6", "-", "退格", "7", "8", "9", "*", "1/X", "0", "+/-", ".", "/",
            "="};
    JPanel jPanel = new JPanel();
    JTextField jTextField = new JTextField("0");
    Font font = new Font("Dialog", Font.BOLD, 30);

    public Main() {
        super("计算器");
        Listener listener = new Listener();
        // 按钮
        for (int i = 0; i < jbt.length; i++) {
            jbt[i] = new JButton(str[i]);
            jPanel.add(jbt[i]);
            jbt[i].addActionListener(listener);
        }
        // 字体
        jTextField.setFont(font);

        // 文本框
        jTextField.setPreferredSize(new Dimension(400, 50));
        jTextField.setEnabled(false);
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        // JPanel按钮
        jPanel.setSize(400, 400);
        jPanel.setBackground(Color.LIGHT_GRAY);
        jPanel.setLayout(new GridLayout(4, 5));
        // 在JFrame中添加
        add(jTextField, BorderLayout.PAGE_START);
        add(jPanel, BorderLayout.CENTER);
        setBounds(300, 100, 400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // 监听器
    class Listener implements ActionListener {
        List<Character> ch = new ArrayList<Character>();
        List<Character> symbol = new ArrayList<Character>();// 符号
        List<Double> nums = new ArrayList<Double>();// 两个数字
        // 循环输出数组


        private String outArr(List<Character> ch2) {
            String str = "";
            for (int i = 0; i < ch2.size(); i++) {
                str += ch2.get(i);
            }
            return str;
        }

        private double operation() {
            double result = 0;
            if (symbol.size()>0 && symbol.get(0) == '+') {
                System.out.println("nums:" + nums);
                System.out.println("symbol:" + symbol);
                if (nums.size() > 1) {
                    result = nums.get(0) + nums.get(1);
                    nums.remove(0);
                    nums.remove(0);
                    if (symbol.size() > 0) {
                        symbol.remove(0);
                    }
                    nums.add(0, result);
                }
            }
            if (symbol.size()>0 && symbol.get(0) == '-') {
                System.out.println("nums:" + nums);
                System.out.println("symbol:" + symbol);
                if (nums.size() > 1) {
                    result = nums.get(0) - nums.get(1);
                    nums.remove(0);
                    nums.remove(0);
                    if (symbol.size() > 0) {
                        symbol.remove(0);
                    }
                    nums.add(0, result);
                }
            }
            if (symbol.size()>0 && symbol.get(0) == '*') {
                System.out.println("nums:" + nums);
                System.out.println("symbol:" + symbol);
                if (nums.size() > 1) {
                    result = nums.get(0) * nums.get(1);
                    nums.remove(0);
                    nums.remove(0);
                    if (symbol.size() > 0) {
                        symbol.remove(0);
                    }
                    nums.add(0, result);
                }
            }
            if (symbol.size()>0 && symbol.get(0) == '/') {
                System.out.println("nums:" + nums);
                System.out.println("symbol:" + symbol);
                if (nums.size() > 1) {
                    result = nums.get(0) / nums.get(1);
                    nums.remove(0);
                    nums.remove(0);
                    if (symbol.size() > 0) {
                        symbol.remove(0);
                    }
                    nums.add(0, result);
                }
            }
            return result;
        }

        private void writeCh(String jtf){
            ch.clear();
            char[] ch3 = (jtf+"").toCharArray();
            for (int m=0;m<ch3.length;m++){
                ch.add(ch3[m]);
            }
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            for (int i = 0; i < 20; i++) {
                if (ae.getSource() == jbt[i]) {
                    char jbtText = jbt[i].getText().toCharArray()[0];
                    String jtfNow = jTextField.getText();
                    switch (jbt[i].getText()) {
                        case "1":
                        case "2":
                        case "3":
                        case "4":
                        case "5":
                        case "6":
                        case "7":
                        case "8":
                        case "9":
                        case "0":
                            if ((jtfNow.toCharArray()[0] == '0' && jtfNow.length()==1) || ch.size() == 0) {
                                ch.clear();
                                ch.add(jbtText);
                                jTextField.setText(outArr(ch));
                                System.out.println("ch"+ch);
                            } else {
                                ch.add(jbtText);
                                jTextField.setText(outArr(ch));
                                System.out.println("ch"+ch);
                            }
                            break;
                        case ".":
                            if (ch.indexOf(jbtText) < 0) {
                                ch.add('.');
                                jTextField.setText(jtfNow + jbtText);
                                System.out.println(ch);
                            }
                            break;
                        case "+/-":
                            writeCh(jtfNow);
                            if (ch.get(0) == '-') {
                                ch.remove(0);
                                jTextField.setText(outArr(ch));
                                System.out.println(ch);
                            } else {
                                ch.add(0, '-');
                                jTextField.setText(outArr(ch));
                                System.out.println(ch);
                            }
                            break;
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            char lastSym = ch.get(ch.size() - 1);
                            if (lastSym != '+' && lastSym != '-' && lastSym != '*' && lastSym != '/' && lastSym != '.') {
                                if (symbol.size() > 0) {
                                    nums.add(Double.valueOf(jtfNow));
                                    symbol.add(jbtText);
                                    ch.clear();
                                    jTextField.setText(operation() + "");
                                } else {
                                    nums.add(Double.valueOf(jtfNow));
                                    symbol.add(jbtText);
                                    ch.clear();
                                    jTextField.setText(jbt[i].getText());
                                    System.out.println("nums：" + nums);
                                    System.out.println("symbol:" + symbol);
                                    System.out.println("ch:" + ch);
                                }
                            }
                            break;

                        case "退格":
                            writeCh(jtfNow);
                            if(ch.size() == 1){
                                ch.clear();
                                ch.add('0');
                                jTextField.setText(outArr(ch));
                                System.out.println(ch);
                            }else {
                                ch.remove(ch.size() - 1);
                                jTextField.setText(outArr(ch));
                                System.out.println(ch);
                            }
                            break;
                        case "1/X":
                            double myNum = Double.valueOf(jtfNow);
                            jTextField.setText(1 / myNum + "");
                            System.out.println(nums);
                            break;
                        case "=":
                            nums.add(Double.valueOf(jtfNow));
                            jTextField.setText("" + operation());
                            symbol.clear();
                            nums.clear();
                            System.out.println("ch:" + ch);
                            System.out.println("symbol:" + symbol);
                            System.out.println("nums:" + nums);
                            break;
                        case "C":
                            ch.clear();
                            symbol.clear();
                            nums.clear();
                            jTextField.setText("0");
                            System.out.println(ch);
                            break;
                    }

                }
            }
        }

    }

    public static void main(String[] args) {
        new Main();
    }
}
