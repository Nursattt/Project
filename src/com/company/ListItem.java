package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListItem extends JFrame {
    Main item_from_main = new Main();
    JTable table;
    public ListItem(){
        ArrayList<Item> list1 = item_from_main.getItems();

        setSize(600,200);
        setTitle("LIST ITEMS");
        setLayout(new FlowLayout());
        String columnNames[] = {"ID","NAME","PRICE"};
        Object[][] data = new Object[list1.size()][3];
        for(int i=0; i<list1.size(); ++i){
            data[i][0] = list1.get(i).getId();
            data[i][1] = list1.get(i).getName();
            data[i][2] = list1.get(i).getPrice();
        }
        table = new JTable(data,columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,100));
        table.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);
    }
}