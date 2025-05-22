package shoppingcart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import javax.swing.SwingUtilities;

public class ShoppingCartGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<User> registeredUsers = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Cart cart = new Cart();
    private String currentUser;

    public ShoppingCartGUI() {
        setTitle("Shopping Cart System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeProducts();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(), "Login");
        mainPanel.add(new RegistrationPanel(), "Register");
        mainPanel.add(new UserDashboardPanel(), "UserDashboard");
        mainPanel.add(new AdminPanel(), "Admin");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    private void initializeProducts() {
        products.add(new Product("Laptop", "Electronics", "Dell", "15-inch screen, i5 CPU", 800, 5));
        products.add(new Product("Phone", "Electronics", "Samsung", "Galaxy A52, 128GB", 500, 3));
        products.add(new Product("Headphones", "Accessories", "Sony", "Noise Cancelling", 100, 10));
        products.add(new Product("Keyboard", "Accessories", "Logitech", "Wireless, mechanical", 50, 8));
        products.add(new Product("Mouse", "Accessories", "HP", "Wireless optical mouse", 30, 15));
        products.add(new Product("Monitor", "Electronics", "LG", "24-inch FHD", 200, 4));
        products.add(new Product("Tablet", "Electronics", "Lenovo", "10-inch Android tablet", 300, 6));
        products.add(new Product("Charger", "Accessories", "Anker", "Fast Charging", 25, 20));
        products.add(new Product("Smartwatch", "Electronics", "Fitbit", "Fitness tracker", 150, 5));
        products.add(new Product("USB Cable", "Accessories", "Baseus", "1m Type-C", 10, 25));
    }

    class LoginPanel extends JPanel {
        public LoginPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            JLabel titleLabel = new JLabel("Login");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(10, 10, 10, 10);
            add(titleLabel, gbc);

            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.gridy++;
            add(new JLabel("Username: "), gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            JTextField usernameField = new JTextField(15);
            add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel("Password: "), gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            JPasswordField passwordField = new JPasswordField(15);
            add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton loginButton = new JButton("Login");
            add(loginButton, gbc);

            gbc.gridy++;
            JButton registerButton = new JButton("Register");
            add(registerButton, gbc);

            loginButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("admin123")) {
                    cardLayout.show(mainPanel, "Admin");
                } else {
                    boolean found = false;
                    for (User u : registeredUsers) {
                        if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                            currentUser = username;
                            cardLayout.show(mainPanel, "UserDashboard");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    }
                }
            });

            registerButton.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        }
    }

    class RegistrationPanel extends JPanel {
        public RegistrationPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            JLabel titleLabel = new JLabel("Register");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(10, 10, 10, 10);
            add(titleLabel, gbc);

            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.gridy++;
            add(new JLabel("Username: "), gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            JTextField usernameField = new JTextField(15);
            add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel("Password: "), gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            JPasswordField passwordField = new JPasswordField(15);
            add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton registerButton = new JButton("Register");
            add(registerButton, gbc);

            gbc.gridy++;
            JButton backButton = new JButton("Back to Login");
            add(backButton, gbc);

            registerButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.length() < 4 || password.length() < 4) {
                    JOptionPane.showMessageDialog(null, "Username and Password must be at least 4 characters.");
                    return;
                }

                for (User u : registeredUsers) {
                    if (u.getUsername().equals(username)) {
                        JOptionPane.showMessageDialog(null, "Username already exists.");
                        return;
                    }
                }

                registeredUsers.add(new User(username, password));
                JOptionPane.showMessageDialog(null, "Registration successful! Please login.");
                cardLayout.show(mainPanel, "Login");
            });

            backButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        }
    }

    class UserDashboardPanel extends JPanel {
        public UserDashboardPanel() {
            setLayout(new FlowLayout());
            JButton viewProductsButton = new JButton("View Products");
            JButton viewCartButton = new JButton("View Cart");
            JButton checkoutButton = new JButton("Checkout");
            JButton logoutButton = new JButton("Logout");

            add(viewProductsButton);
            add(viewCartButton);
            add(checkoutButton);
            add(logoutButton);

            viewProductsButton.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Product p : products) {
                    sb.append(p.getName()).append(" - $").append(p.getPrice()).append(" - Stock: ").append(p.getStock()).append("\n");
                }
                String input = JOptionPane.showInputDialog(null, sb.toString() + "\nEnter product name to add to cart:");
                if (input != null) {
                    for (Product p : products) {
                        if (p.getName().equalsIgnoreCase(input)) {
                            cart.addProduct(p);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Product not found.");
                }
            });

            viewCartButton.addActionListener(e -> cart.displayCart());
            checkoutButton.addActionListener(e -> cart.checkout());
            logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        }
    }

    class AdminPanel extends JPanel {
        public AdminPanel() {
            setLayout(new BorderLayout());
            JTextArea userListArea = new JTextArea();
            userListArea.setEditable(false);
            JButton refreshButton = new JButton("Refresh");
            JButton logoutButton = new JButton("Logout");

            JPanel topPanel = new JPanel();
            topPanel.add(refreshButton);
            topPanel.add(logoutButton);

            add(topPanel, BorderLayout.NORTH);
            add(new JScrollPane(userListArea), BorderLayout.CENTER);

            refreshButton.addActionListener(e -> {
                StringBuilder sb = new StringBuilder("Registered Users:\n");
                for (User u : registeredUsers) {
                    sb.append("- ").append(u.getUsername()).append("\n");
                }
                userListArea.setText(sb.toString());
            });

            logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingCartGUI());
    }
}
