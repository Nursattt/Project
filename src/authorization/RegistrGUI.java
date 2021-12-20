package authorization;

import com.company.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrGUI extends JFrame{
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost/dataserver?serverTimezone=Europe/Moscow&useSSL=false";
    private static String user = "root";
    private static String pass = "";

    JFrame frame = new JFrame();

    public static AuthMenu AuthmenuFrame = new AuthMenu();

    private JLabel text;

    private JButton registr;
    private JButton back;

    String[] petStrings = { "Мой родной город", "Имя моей первой бывшей", "Мой любимый цвет", "Кого я люблю больше всех", "Кто я" };
    JComboBox petList;

    private JTextField text_name;
    private JTextField text_login;
    private JPasswordField text_password;
    private JTextField text_age;
    private JTextField text_email;
    private JTextField text_question;

    List<Registr> users= new ArrayList<>();

    public RegistrGUI(){
        try{
            connection = DriverManager.getConnection(url,user,pass);
            //System.out.println("connection");
        }catch (Exception e){
            e.printStackTrace();
        }

        setSize(400, 700); // размер полотна
        getContentPane().setBackground(new Color(75, 32, 86, 239));
        setLayout(null); // для того чтобы располагать объекты
        getContentPane().setLayout(null);
        setTitle("TECHNODOM");

        text = new JLabel("РЕГИСТРАЦИЯ:");
        text.setBounds(110, 20, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 26));
        add(text);

        text = new JLabel("Name");
        text.setBounds(160, 80, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_name = new JTextField();
        text_name.setBounds(85, 120, 200, 30);
        add(text_name);

        text = new JLabel("Login");
        text.setBounds(160, 150, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_login = new JTextField();
        text_login.setBounds(85, 190, 200, 30);
        add(text_login);

        text = new JLabel("Password");
        text.setBounds(160, 220, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_password = new JPasswordField();
        text_password.setBounds(85, 260, 200, 30);
        add(text_password);

        text = new JLabel("Email");
        text.setBounds(160, 290, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_email = new JTextField();
        text_email.setBounds(85, 330, 200, 30);
        add(text_email);

        text = new JLabel("Age");
        text.setBounds(160, 370, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_age = new JTextField();
        text_age.setBounds(85, 410, 200, 30);
        add(text_age);

        petList = new JComboBox(petStrings);
        petList.setBounds(85, 470, 200, 32);
        add(petList);

        text_question = new JTextField();
        text_question.setBounds(85, 530, 200, 30);
        add(text_question);

        registr = new JButton("CREATE ACCOUNT");
        registr.setFont(new Font("Arial", Font.PLAIN, 16));
        registr.setBounds(85, 600, 200, 35);
        registr.setForeground(new Color(255, 255, 255, 255));
        registr.setBackground(new Color(37, 189, 65));
        add(registr);

        back = new JButton("BACK");
        back.setFont(new Font("Arial", Font.PLAIN, 16));
        back.setBounds(300, 5, 80, 25);
        back.setForeground(new Color(255, 255, 255, 255));
        back.setBackground(new Color(241, 7, 7));
        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    AuthmenuFrame.setVisible(true);
                    setVisible(false);
            }
        });

        registr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(
                    text_name.getText().equals("") ||
                    text_login.getText().equals("") ||
                    text_password.getText().equals("") ||
                    text_age.getText().equals("") ||
                    text_email.getText().equals("") ||
                    text_question.getText().equals("")
                )
                    JOptionPane.showMessageDialog(frame, "Please enter all data!!!","Oops, something is wrong", JOptionPane.INFORMATION_MESSAGE);
                else{
                    Registr user = new Registr(text_name.getText(), text_login.getText(), text_password.getText(), Integer.parseInt(text_age.getText()), text_email.getText(), text_question.getText());
                    users.add(user);
                    addUser(users);
                    JOptionPane.showMessageDialog(frame, "The accound is created", "information", JOptionPane.INFORMATION_MESSAGE);
                    AuthmenuFrame.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }

    public static void addUser(List<Registr> users){
        try
        {
            String query = "insert into users(name,login,password,age,email,question) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(Registr user:users) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setInt(4, user.getAge());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getQuestion());
            }
            preparedStatement.executeUpdate();
            //System.out.println("inserted successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}