package com.company;

import authorization.AuthMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import java.lang.*;
import java.sql.*;

public class ClientMenu extends JFrame {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost/dataserver?serverTimezone=Europe/Moscow&useSSL=false";
    private static String user = "root";
    private static String pass = "";

    private JTextField text_buy;
    private JLabel text;
    private JButton LoginText;
    private JButton Quit;
    private JButton showItem;
    private JButton addBalance;
    private JButton adminMenu;
    private JButton buyItem;
    private int balance=10000;
    Main forSave = new Main();
    JFrame frame = new JFrame();
    public static AuthMenu AuthmenuFrame = new AuthMenu();
    public static Server adminMenuFrame = new Server();
    public ClientMenu() {
        try{
            connection = DriverManager.getConnection(url,user,pass);
            //System.out.println("connection");
        }catch (Exception e){
            e.printStackTrace();
        }

        setSize(400, 300); // размер полотна

        setLayout(null); // для того чтобы располагать объекты
        getContentPane().setLayout(null);
        setTitle("TECHNODOM");

        text = new JLabel("ПОЛЬЗОВАТЕЛЬ МЕНЮ");
        text.setBounds(15, 10, 300, 60);
        add(text);

        LoginText = new JButton(balance+ "$");
        LoginText.setBounds(180, 5, 100, 20);
        LoginText.setBackground(new Color(75, 32, 86, 239));
        LoginText.setForeground(new Color(255, 255, 255, 255));
        add(LoginText);

        Quit = new JButton("Выйти");
        Quit.setBounds(180, 30, 100, 20);
        Quit.setBackground(new Color(75, 32, 86, 239));
        Quit.setForeground(new Color(255, 255, 255, 255));
        add(Quit);

        showItem = new JButton("show");
        showItem.setBounds(50, 90, 150, 30);
        showItem.setBackground(new Color(75, 32, 86, 239));
        showItem.setForeground(new Color(255, 255, 255, 255));
        add(showItem);

        buyItem = new JButton("buy");
        buyItem.setBounds(50, 130, 150, 30);
        buyItem.setBackground(new Color(75, 32, 86, 239));
        buyItem.setForeground(new Color(255, 255, 255, 255));
        add(buyItem);

        text_buy = new JTextField();
        text_buy.setBounds(220, 130, 50, 30);
        add(text_buy);

        addBalance = new JButton("top up balance");
        addBalance.setBounds(50, 170, 150, 30);
        addBalance.setBackground(new Color(75, 32, 86, 239));
        addBalance.setForeground(new Color(255, 255, 255, 255));
        add(addBalance);

        adminMenu = new JButton("admin menu");
        adminMenu.setBounds(50, 210, 150, 30);
        adminMenu.setBackground(new Color(75, 32, 86, 239));
        adminMenu.setForeground(new Color(255, 255, 255, 255));
        add(adminMenu);

        adminMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AuthmenuFrame.check==true) {
                    adminMenuFrame.setVisible(true);
                    setVisible(false);
                }
                else JOptionPane.showMessageDialog(frame, "NO ACCESS TO OPEN ADMIN MENU","Oops, something is wrong", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        LoginText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "It is your balance","information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balance+=500;
                LoginText.setText(balance + "$");
                JOptionPane.showMessageDialog(frame, "+500$ to your balance","information", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthmenuFrame.setVisible(true);
                setVisible(false);
            }
        });

        showItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //for (int i = 0; i < 5; ++i) System.out.println();
                //forSave.showItem();
                ListItem list = new ListItem();
                list.setVisible(true);
            }
        });

        buyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(balance>=0) {
                    int id = parseInt(text_buy.getText());
                    String query = "SELECT * FROM server where id=" + id;
                    if (text_buy.getText().equals(""))
                        JOptionPane.showMessageDialog(frame, "Please ENTER index!!!", "Oops, something is wrong", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        try {
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery(query);
                            if (resultSet.next())
                                balance -= resultSet.getInt(3);
                            LoginText.setText(balance + "$");
                        } catch (SQLException c) {
                            c.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(frame, "The item is bought", "information", JOptionPane.INFORMATION_MESSAGE);
                        forSave.deleteItem(parseInt(text_buy.getText()));
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "no enough money", "Oops, something is wrong", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}