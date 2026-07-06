import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton loginButton;

    public LoginFrame() {

        setTitle("Login");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel uLabel = new JLabel("Username:");
        uLabel.setBounds(30, 30, 100, 25);
        add(uLabel);

        userField = new JTextField();
        userField.setBounds(120, 30, 120, 25);
        add(userField);

        JLabel pLabel = new JLabel("Password:");
        pLabel.setBounds(30, 70, 100, 25);
        add(pLabel);

        passField = new JPasswordField();
        passField.setBounds(120, 70, 120, 25);
        add(passField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 80, 25);
        loginButton.addActionListener(this);
        add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (user.equals(User.validUsername) && pass.equals(User.validPassword)) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            new QuizFrame();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Login");
        }
    }
}