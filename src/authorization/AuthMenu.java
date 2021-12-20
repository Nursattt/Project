package authorization;

import com.company.ClientMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AuthMenu extends JFrame{
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost/dataserver?serverTimezone=Europe/Moscow&useSSL=false";
    private static String user = "root";
    private static String pass = "";

    public static RegistrGUI frames = new RegistrGUI();
    public static ClientMenu ClientFrames = new ClientMenu();
    public static ForgetPassword framesOfForgot = new ForgetPassword();

    private JLabel text;

    private JButton login;
    private JButton for_password;
    private JButton registr;

    private JTextField text_login;
    private JPasswordField text_code;
    private JPasswordField text_password;
    public boolean check = false;
    public AuthMenu(){
        try{
            connection = DriverManager.getConnection(url,user,pass);
            //System.out.println("connection");
        }catch (Exception e){
            e.printStackTrace();
        }

        setSize(390, 500); // размер полотна
        getContentPane().setBackground(new Color(75, 32, 86, 239));
        setLayout(null); // для того чтобы располагать объекты
        getContentPane().setLayout(null);
        setTitle("TECHNODOM");

        text = new JLabel("TECHNODOM");
        text.setBounds(110, 20, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 26));
        add(text);

        text = new JLabel("Login");
        text.setBounds(160, 80, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_login = new JTextField();
        text_login.setBounds(85, 120, 200, 30);
        add(text_login);

        text = new JLabel("Password");
        text.setBounds(160, 150, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_password = new JPasswordField();
        text_password.setBounds(85, 190, 200, 30);
        add(text_password);

        text = new JLabel("Code");
        text.setBounds(160, 220, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_code = new JPasswordField();
        text_code.setBounds(85, 260, 200, 30);
        text_code.setText("user");
        add(text_code);

        login = new JButton("Sign IN");
        login.setFont(new Font("Arial", Font.PLAIN, 16));
        login.setBounds(85, 310, 200, 35);
        login.setForeground(new Color(255, 255, 255, 255));
        login.setBackground(new Color(37, 189, 65));
        add(login);

        for_password = new JButton("Forgot password");
        for_password.setBounds(30, 370, 150, 30);
        for_password.setForeground(new Color(203, 203, 203, 255));
        for_password.setBackground(new Color(75, 32, 86));
        add(for_password);

        registr = new JButton("Create an accound");
        registr.setBounds(210, 370, 150, 30);
        registr.setForeground(new Color(203, 203, 203, 255));
        registr.setBackground(new Color(75, 32, 86));
        add(registr);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT * FROM users ";
                try{
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while(resultSet.next()){
                        if(
                                text_login.getText().equals(resultSet.getString(2)) &&
                                text_password.getText().equals(resultSet.getString(3)) &&
                                text_code.getText().equals("admin")
                        )
                        {
                            check=true;
                            ClientFrames.setVisible(true);
                            setVisible(false);
                        }
                        else if(
                                text_login.getText().equals(resultSet.getString(2)) &&
                                text_password.getText().equals(resultSet.getString(3)) &&
                                text_code.getText().equals("user")
                        )
                        {
                            check=false;
                            ClientFrames.setVisible(true);
                            setVisible(false);
                        }
                    }
                }catch (SQLException c){
                    c.printStackTrace();
                }
            }
        });

        registr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frames.setVisible(true);
                setVisible(false);
            }
        });

        for_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framesOfForgot.setVisible(true);
                setVisible(false);
            }
        });
    }
}