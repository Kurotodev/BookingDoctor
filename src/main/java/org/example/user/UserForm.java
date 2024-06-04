package org.example.user;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private boolean confirmed;
    private User user;

    public UserForm(User user) {
        this.user = user;
        setTitle(user == null ? "Agregar Usuario" : "Modificar Usuario");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        add(new JLabel("Full Name:"));
        fullNameField = new JTextField(20);
        add(fullNameField);

        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField(20);
        add(phoneField);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        add(okButton);
        add(cancelButton);

        if (user != null) {
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            fullNameField.setText(user.getFullName());
            emailField.setText(user.getEmail());
            phoneField.setText(user.getPhone());
            usernameField.setEnabled(false); // Username no editable
        }

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                UserForm.this.setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                UserForm.this.setVisible(false);
            }
        });
    }

    public boolean showDialog() {
        setVisible(true);
        return confirmed;
    }

    public User getUser() {
        if (user == null) {
            user = new User(
                    usernameField.getText(),
                    new String(passwordField.getPassword()),
                    fullNameField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            );
        } else {
            user.setPassword(new String(passwordField.getPassword()));
            user.setFullName(fullNameField.getText());
            user.setEmail(emailField.getText());
            user.setPhone(phoneField.getText());
        }
        return user;
    }
}
