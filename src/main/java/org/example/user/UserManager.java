package org.example.user;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UserManager {
    private static final String FILE_PATH = "users.scm";
    private List<User> users;

    public UserManager() {
        users = loadUsers();
        if (users.isEmpty()) {
            users.add(new User("admin", "admin", "Admin", "admin@example.com", "123456789"));
            saveUsers();
        }
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        if (isUsernameUnique(user.getUsername())) {
            users.add(user);
            saveUsers();
        } else {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                saveUsers();
                break;
            }
        }
    }

    public void deleteUser(String username) {
        if (!username.equals("admin")) {
            users.removeIf(user -> user.getUsername().equals(username));
            saveUsers();
        } else {
            throw new IllegalArgumentException("Admin user cannot be deleted");
        }
    }

    private boolean isUsernameUnique(String username) {
        return users.stream().noneMatch(user -> user.getUsername().equals(username));
    }

    public boolean validateCredentials(String username, String password) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    private void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH)))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getFullName() + "," + user.getEmail() + "," + user.getPhone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        users.add(new User(parts[0], parts[1], parts[2], parts[3], parts[4]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
