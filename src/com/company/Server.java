package com.company;

import authorization.AuthMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.lang.*;
import java.util.List;

public class Server extends JFrame {
    private JTextField text_name;
    private JTextField text_price;
    private JTextField text_delete;
    private JTextField text_edit;

    private JLabel priceOfItem;
    private JLabel text;
    private JLabel nameOfitem;

    private JButton LoginText;
    private JButton Quit;
    private JButton back;
    private JButton addItem;
    private JButton showItem;
    private JButton deleteItem;
    private JButton editItem;

    List<Item> good_item = new ArrayList<>();
    int count = 0;
    private long balance=10000;
    Main forSave = new Main();
    JFrame frame = new JFrame();
    public static AuthMenu AuthmenuFrame = new AuthMenu();
    public static ClientMenu ClientMenuFrame = new ClientMenu();

    public Server() {
        setSize(500, 400); // размер полотна

        setLayout(null); // для того чтобы располагать объекты
        getContentPane().setLayout(null);
        setTitle("TECHNODOM");

        text = new JLabel("АДМИН МЕНЮ: ДОБАВИТЬ ТОВАР");
        text.setBounds(120, 20, 300, 60);
        add(text);

        LoginText = new JButton(balance+"$");
        LoginText.setBounds(370, 5, 100, 20);
        LoginText.setBackground(new Color(75, 32, 86, 239));
        LoginText.setForeground(new Color(255, 255, 255, 255));
        add(LoginText);

        Quit = new JButton("Выйти");
        Quit.setBounds(370, 30, 100, 20);
        Quit.setBackground(new Color(75, 32, 86, 239));
        Quit.setForeground(new Color(255, 255, 255, 255));
        add(Quit);

        back = new JButton("back");
        back.setBounds(370, 55, 100, 20);
        back.setBackground(new Color(75, 32, 86, 239));
        back.setForeground(new Color(255, 255, 255, 255));
        add(back);

        nameOfitem = new JLabel("Name: ");
        nameOfitem.setBounds(100, 100, 100, 30);
        add(nameOfitem);

        text_name = new JTextField();
        text_name.setBounds(180, 100, 100, 30);
        add(text_name);

        priceOfItem = new JLabel("Price: ");
        priceOfItem.setBounds(100, 150, 100, 30);
        add(priceOfItem);

        text_price = new JTextField();
        text_price.setBounds(180, 150, 100, 30);
        add(text_price);

        addItem = new JButton("add");
        addItem.setBounds(70, 210, 80, 30);
        addItem.setBackground(new Color(75, 32, 86, 239));
        addItem.setForeground(new Color(255, 255, 255, 255));
        add(addItem);

        showItem = new JButton("show");
        showItem.setBounds(70, 250, 80, 30);
        showItem.setBackground(new Color(75, 32, 86, 239));
        showItem.setForeground(new Color(255, 255, 255, 255));
        add(showItem);

        deleteItem = new JButton("delete");
        deleteItem.setBounds(200, 210, 80, 30);
        deleteItem.setBackground(new Color(75, 32, 86, 239));
        deleteItem.setForeground(new Color(255, 255, 255, 255));
        add(deleteItem);

        text_delete = new JTextField();
        text_delete.setBounds(290, 210, 50, 30);
        add(text_delete);

        editItem = new JButton("edit");
        editItem.setBounds(200, 250, 80, 30);
        editItem.setBackground(new Color(75, 32, 86, 239));
        editItem.setForeground(new Color(255, 255, 255, 255));
        add(editItem);

        text_edit = new JTextField();
        text_edit.setBounds(290, 250, 50, 30);
        add(text_edit);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientMenuFrame.setVisible(true);
                setVisible(false);
            }
        });

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(null,text_name.getText(), parseLong(text_price.getText()));
                if(text_name.getText().equals("") || text_price.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "Please ENTER all data!!!","Oops, something is wrong", JOptionPane.INFORMATION_MESSAGE);
                else {
                    JOptionPane.showMessageDialog(frame, "The item is added","information", JOptionPane.INFORMATION_MESSAGE);
                    good_item.add(item);
                    forSave.saveUsersList(good_item);
                    forSave.addItem(good_item);
                    text_name.setText(""); text_price.setText(""); text_delete.setText(""); text_edit.setText("");
                    ++count;
                }
            }
        });

        LoginText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text_delete.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "Please ENTER index!!!","Oops, something is wrong", JOptionPane.INFORMATION_MESSAGE);
                else {
                    JOptionPane.showMessageDialog(frame, "The item is deleted", "information", JOptionPane.INFORMATION_MESSAGE);
                    forSave.deleteItem(parseInt(text_delete.getText()));
                    text_name.setText("");text_price.setText("");text_delete.setText("");text_edit.setText("");
                }
            }
        });

        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text_edit.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "Please ENTER index!!!","Oops, something is wrong", JOptionPane.INFORMATION_MESSAGE);
                else {
                    JOptionPane.showMessageDialog(frame, "The item is edited", "information", JOptionPane.INFORMATION_MESSAGE);
                    forSave.editItem(parseInt(text_edit.getText()), text_name.getText(), parseLong(text_price.getText()));
                    text_name.setText("");text_price.setText("");text_delete.setText("");text_edit.setText("");
                }
            }
        });
    }
}