package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  org.example.user.UserManager;
public class LoginSystem extends JFrame {
    private int attemptCount = 0;
    private static final int MAX_ATTEMPTS = 3;
    private UserManager userManager;

    public LoginSystem() {
        userManager = new UserManager();
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                if (authenticate(user, password)) {
                    JOptionPane.showMessageDialog(panel, "Login Successful");
                    dispose();
                    new MainFrame();
                } else {
                    attemptCount++;
                    if (attemptCount >= MAX_ATTEMPTS) {
                        JOptionPane.showMessageDialog(panel, "Too many attempts. System will exit.");
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid credentials. Attempts left: " + (MAX_ATTEMPTS - attemptCount));
                    }
                }
            }
        });
    }

    private boolean authenticate(String user, String password) {
        return userManager.validateCredentials(user, password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginSystem::new);
    }
}
