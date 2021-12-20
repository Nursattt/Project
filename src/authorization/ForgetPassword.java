package authorization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgetPassword extends JFrame{
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost/dataserver?serverTimezone=Europe/Moscow&useSSL=false";
    private static String user = "root";
    private static String pass = "";

    JFrame frame = new JFrame();
    public static AuthMenu AuthmenuFrame = new AuthMenu();

    String[] petStrings = { "Мой родной город", "Имя моей первой бывшей", "Мой любимый цвет", "Кого я люблю больше всех", "Кто я" };
    JComboBox petList;

    private JLabel text;

    private JButton change_pass;
    private JButton back;

    private JPasswordField text_password;
    private JTextField text_question;
    private JTextField text_login;


    public ForgetPassword(){
        try{
            connection = DriverManager.getConnection(url,user,pass);
            //System.out.println("connection");
        }catch (Exception e){
            e.printStackTrace();
        }

        setSize(400, 600); // размер полотна
        getContentPane().setBackground(new Color(75, 32, 86, 239));
        setLayout(null); // для того чтобы располагать объекты
        getContentPane().setLayout(null);
        setTitle("TECHNODOM");

        text = new JLabel("CHANGE YOUR PASSWORD:");
        text.setBounds(50, 20, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 26));
        add(text);

        text = new JLabel("Login");
        text.setBounds(160, 100, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_login = new JTextField();
        text_login.setBounds(85, 150, 200, 30);
        add(text_login);

        text = new JLabel("Question");
        text.setBounds(140, 200, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        petList = new JComboBox(petStrings);
        petList.setBounds(85, 250, 200, 32);
        add(petList);

        text_question = new JTextField();
        text_question.setBounds(85, 300, 200, 30);
        add(text_question);

        text = new JLabel("New Password");
        text.setBounds(140, 350, 300, 60);
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(text);

        text_password = new JPasswordField();
        text_password.setBounds(85, 400, 200, 30);
        add(text_password);

        change_pass = new JButton("Change the password");
        change_pass.setFont(new Font("Arial", Font.PLAIN, 16));
        change_pass.setBounds(85, 450, 200, 35);
        change_pass.setForeground(new Color(255, 255, 255, 255));
        change_pass.setBackground(new Color(37, 189, 65));
        add(change_pass);

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

        change_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT * FROM users";

                try{
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while(resultSet.next()){
                        if(text_login.getText().equals(resultSet.getString(2)) && text_question.getText().equals(resultSet.getString(6)))
                        {
                            AuthmenuFrame.setVisible(true);
                            setVisible(false);
                            JOptionPane.showMessageDialog(frame, "The password is changed", "information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    editPasswordFromQuestion(text_password.getText(), text_question.getText());
                    editPasswordFromLogin(text_password.getText(), text_question.getText());

                    text_login.setText("");text_password.setText("");text_question.setText("");
                }catch (SQLException c){
                    c.printStackTrace();
                }
            }
        });
    }
    public static void editPasswordFromQuestion(String pass, String question) throws SQLException {
        String queryForPass = "update users set password=? where question = '" + question + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(queryForPass);
        try{
            preparedStatement.setString(1, pass);
            preparedStatement.executeUpdate();
        }catch (SQLException a){
            a.printStackTrace();
        }
    }
    public static void editPasswordFromLogin(String pass, String log) throws SQLException {
        String queryForPass = "update users set password=? where login = '" + log + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(queryForPass);
        try{
            preparedStatement.setString(1, pass);
            preparedStatement.executeUpdate();
        }catch (SQLException a){
            a.printStackTrace();
        }
    }

}