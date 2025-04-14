import java.awt.*;
import javax.swing.*;

public class WelcomeFrame extends JFrame {
    private boolean isPasswordVisible = false;

    private JPanel loginPanel;
    private JPanel registerPanel;

    public WelcomeFrame() {
        setTitle("Quản lý Trung tâm Tiếng Anh");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel titleLabel = new JLabel("🎓 Quản lý Trung tâm Tiếng Anh");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(33, 37, 41));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        mainPanel.add(titleLabel);

        // Login Panel
        loginPanel = createLoginPanel();
        mainPanel.add(loginPanel);

        // Register Panel
        registerPanel = createRegisterPanel();
        registerPanel.setVisible(false);
        mainPanel.add(registerPanel);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 250));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(panel.getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Tài khoản:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField userField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userField, gbc);

        JLabel passLabel = new JLabel("Mật khẩu:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JPasswordField passField = new JPasswordField(15);
        passField.setEchoChar('\u2022');

        JButton eyeButton = new JButton("👁");
        eyeButton.setPreferredSize(new Dimension(45, 30));
        eyeButton.setFocusPainted(false);
        eyeButton.setBackground(Color.WHITE);
        eyeButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel passPanel = new JPanel(new BorderLayout());
        passPanel.add(passField, BorderLayout.CENTER);
        passPanel.add(eyeButton, BorderLayout.EAST);
        passPanel.setBackground(Color.WHITE);
        passPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passPanel, gbc);

        eyeButton.addActionListener(e -> {
            if (isPasswordVisible) {
                passField.setEchoChar('\u2022');
                eyeButton.setText("👁");
                isPasswordVisible = false;
            } else {
                passField.setEchoChar((char) 0);
                eyeButton.setText("🙈");
                isPasswordVisible = true;
            }
        });

        JButton loginBtn = new JButton("Đăng nhập");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(loginBtn, new Color(0, 123, 255));

        JButton goToRegisterBtn = new JButton("Đăng ký");
        goToRegisterBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(goToRegisterBtn, new Color(40, 167, 69));

        goToRegisterBtn.addActionListener(e -> {
            loginPanel.setVisible(false);
            registerPanel.setVisible(true);
        });

        panel.add(formPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(goToRegisterBtn);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 250));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(panel.getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Tên đăng nhập:");
        JTextField userField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Số điện thoại:");
        JTextField phoneField = new JTextField(15);

        JLabel passLabel = new JLabel("Mật khẩu:");
        JPasswordField passField = new JPasswordField(15);

        JLabel confirmLabel = new JLabel("Nhập lại mật khẩu:");
        JPasswordField confirmField = new JPasswordField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(confirmLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(confirmField, gbc);

        JButton submitBtn = new JButton("Hoàn tất đăng ký");
        styleButton(submitBtn, new Color(23, 162, 184));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitBtn.addActionListener(e -> {
            String password = new String(passField.getPassword());
            String confirm = new String(confirmField.getPassword());

            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                // TODO: lưu tài khoản vào DB
                JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
                registerPanel.setVisible(false);
                loginPanel.setVisible(true);
            }
        });

        JButton backBtn = new JButton("← Quay lại đăng nhập");
        styleButton(backBtn, Color.GRAY);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        backBtn.addActionListener(e -> {
            registerPanel.setVisible(false);
            loginPanel.setVisible(true);
        });

        panel.add(formPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(submitBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backBtn);

        return panel;
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeFrame());
    }
}
