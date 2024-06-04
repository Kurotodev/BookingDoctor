package org.example.user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAdmin extends JInternalFrame {
    private UserManager userManager;
    private JTable table;
    private DefaultTableModel tableModel;

    public UserAdmin() {
        userManager = new UserManager();
        setTitle("Administración de Usuarios");
        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Username", "Full Name", "Email", "Phone"}, 0);
        table = new JTable(tableModel);
        loadUserData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton editButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Eliminar");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserForm userForm = new UserForm(null);
                if (userForm.showDialog()) {
                    userManager.addUser(userForm.getUser());
                    loadUserData();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String username = (String) tableModel.getValueAt(selectedRow, 0);
                    User user = userManager.getAllUsers().stream()
                            .filter(u -> u.getUsername().equals(username))
                            .findFirst()
                            .orElse(null);
                    if (user != null) {
                        UserForm userForm = new UserForm(user);
                        if (userForm.showDialog()) {
                            userManager.updateUser(userForm.getUser());
                            loadUserData();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(UserAdmin.this, "Por favor, seleccione un usuario para modificar.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String username = (String) tableModel.getValueAt(selectedRow, 0);
                    if (!username.equals("admin")) {
                        userManager.deleteUser(username);
                        loadUserData();
                    } else {
                        JOptionPane.showMessageDialog(UserAdmin.this, "No se puede eliminar el usuario admin.");
                    }
                } else {
                    JOptionPane.showMessageDialog(UserAdmin.this, "Por favor, seleccione un usuario para eliminar.");
                }
            }
        });
    }

    private void loadUserData() {
        tableModel.setRowCount(0);
        for (User user : userManager.getAllUsers()) {
            tableModel.addRow(new Object[]{user.getUsername(), user.getFullName(), user.getEmail(), user.getPhone()});
        }
    }
}
