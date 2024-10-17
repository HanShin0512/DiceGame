import javax.swing.*;

public class LoginSystem {

    public boolean login() {
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(10);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(null, panel, "HighSum", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (validateCredentials(username, password)) {
                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                login();
            }
        } else {
            return false;
        }
        return true;
    }

    public static boolean validateCredentials(String username, String password) {
        return username.equals("IcePeak") && password.equals("password");
    }
}
