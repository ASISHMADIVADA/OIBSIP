import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel titleLabel;
    JLabel userLabel;
    JLabel passLabel;

    JTextField usernameField;
    JPasswordField passwordField;

    JButton loginButton;
    JButton clearButton;

    ReservationDAO dao;

    public LoginFrame() {

        dao = new ReservationDAO();

        setTitle("Online Reservation System - Login");
        setSize(450,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("ONLINE RESERVATION SYSTEM");
        titleLabel.setBounds(70,20,320,30);
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));

        userLabel = new JLabel("Username");
        userLabel.setBounds(50,80,100,25);

        passLabel = new JLabel("Password");
        passLabel.setBounds(50,130,100,25);

        usernameField = new JTextField();
        usernameField.setBounds(160,80,180,25);

        passwordField = new JPasswordField();
        passwordField.setBounds(160,130,180,25);

        loginButton = new JButton("Login");
        loginButton.setBounds(80,190,100,30);

        clearButton = new JButton("Clear");
        clearButton.setBounds(220,190,100,30);

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);

        add(titleLabel);
        add(userLabel);
        add(passLabel);
        add(usernameField);
        add(passwordField);
        add(loginButton);
        add(clearButton);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==loginButton){

            String username=usernameField.getText().trim();
            String password=String.valueOf(passwordField.getPassword());

            if(username.isEmpty() || password.isEmpty()){

                JOptionPane.showMessageDialog(this,
                        "Please enter Username and Password");

                return;
            }

            boolean valid=dao.validateLogin(username,password);

            if(valid){

                JOptionPane.showMessageDialog(this,
                        "Login Successful");

                dispose();

                new ReservationFrame();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password");

            }

        }

        if(e.getSource()==clearButton){

            usernameField.setText("");
            passwordField.setText("");

        }

    }

}