import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePageWithActions extends JFrame {

    private JPanel mainContentPanel; // Khung chính để hiển thị nội dung động
    private CardLayout cardLayout;   // Quản lý các panel nội dung
    private JTextField searchField;  // Trường nhập liệu tìm kiếm

    public HomePageWithActions() {
        setTitle("Trang chủ - Quản lý Trung tâm Tiếng Anh");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color globalBackground = new Color(255, 230, 204); // Màu nền toàn khung

        // ===== Taskbar (Top Action Bar) =====
        JPanel taskbar = new JPanel();
        taskbar.setBackground(globalBackground);
        taskbar.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        // Thêm khung viền cho taskbar
        taskbar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));

        // Thêm các nút hành động
        String[] actionNames = {"➕ Thêm", "✏️ Sửa", "🗑 Xóa"};
        for (String action : actionNames) {
            JButton actionBtn = createAnimatedActionButton(action);
            actionBtn.setBackground(new Color(204, 0, 0)); // Đỏ
            actionBtn.setForeground(Color.WHITE);          // Chữ trắng
            actionBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Bạn vừa chọn: " + action);
            });
            taskbar.add(actionBtn);
        }

        // Thêm trường tìm kiếm và nút tìm kiếm
        searchField = new JTextField(15);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        taskbar.add(searchField);

        JButton searchButton = createAnimatedActionButton("🔍 Tìm kiếm");
        searchButton.setBackground(new Color(204, 0, 0));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Đang tìm kiếm: " + keyword);
                // TODO: Thêm logic tìm kiếm thực tế ở đây
            }
        });
        taskbar.add(searchButton);

        add(taskbar, BorderLayout.NORTH);

        // ===== Sidebar (Menu bên trái) =====
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(globalBackground);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 20));

        String[] menuItems = {
                "Trang chủ", "Quản lý học sinh", "Quản lý khóa học",
                "Quản lý lịch học", "Quản lý giáo viên", "Báo cáo đào tạo",
                "Quản lý tài chính", "Đăng xuất"
        };

        for (String item : menuItems) {
            JButton btn = createAnimatedButton(item);
            btn.addActionListener(new MenuButtonListener(item));
            if (item.equals("Trang chủ")) {
                btn.setBackground(new Color(204, 0, 0));
            } else if (item.equals("Đăng xuất")) {
                btn.setBackground(new Color(153, 0, 0));
            }
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        add(sidebar, BorderLayout.WEST);

        // ===== Main Content Panel (Khung bên phải) =====
        mainContentPanel = new JPanel();
        cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.setBackground(globalBackground);
        // Thêm khung viền cho main content
        mainContentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Thêm các panel nội dung cho từng menu
        mainContentPanel.add(createHomePanel(), "Trang chủ");
        mainContentPanel.add(createStudentPanel(), "Quản lý học sinh");
        mainContentPanel.add(createCoursePanel(), "Quản lý khóa học");
        mainContentPanel.add(createSchedulePanel(), "Quản lý lịch học");
        mainContentPanel.add(createTeacherPanel(), "Quản lý giáo viên");
        mainContentPanel.add(createReportPanel(), "Báo cáo đào tạo");
        mainContentPanel.add(createFinancePanel(), "Quản lý tài chính");

        add(mainContentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // ==== Tạo panel cho Trang chủ ====
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 230, 204));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(153, 0, 0));
        infoPanel.setPreferredSize(new Dimension(500, 200));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));

        JLabel title = new JLabel("ENGLISH CENTER ABC");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subInfo = new JLabel("WELCOME");
        subInfo.setForeground(Color.WHITE);
        subInfo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(title);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        infoPanel.add(subInfo);
        infoPanel.add(Box.createVerticalGlue());

        panel.add(infoPanel);
        return panel;
    }

    // ==== Tạo panel cho các chức năng khác (placeholder) ====
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 230, 204));
        JLabel label = new JLabel("Quản lý học sinh", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 230, 204));
        JLabel label = new JLabel("Quản lý khóa học", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSchedulePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 230, 204));
        JLabel label = new JLabel("Quản lý lịch học", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTeacherPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 230, 204));
        JLabel label = new JLabel("Quản lý giáo viên", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 230, 204));
        JLabel label = new JLabel("Báo cáo đào tạo", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFinancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 230, 204));
        JLabel label = new JLabel("Quản lý tài chính", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // ==== Nút sidebar với hiệu ứng to nhỏ ====
    private JButton createAnimatedButton(String text) {
        return createAnimatedBaseButton(text, new Color(60, 63, 65), Color.WHITE);
    }

    // ==== Nút Thêm/Sửa/Xóa/Tìm kiếm với hiệu ứng to nhỏ ====
    private JButton createAnimatedActionButton(String text) {
        return createAnimatedBaseButton(text, new Color(204, 0, 0), Color.WHITE);
    }

    // ==== Tạo nút hiệu ứng chung ====
    private JButton createAnimatedBaseButton(String text, Color bgColor, Color fgColor) {
        JButton btn = new JButton(text) {
            private float scale = 1.0f;
            private Timer enlargeTimer;
            private Timer shrinkTimer;
            private final float maxScale = 1.15f;
            private final float minScale = 1.0f;
            private final float step = 0.02f;

            {
                setFont(new Font("Segoe UI", Font.BOLD, 14));
                setForeground(fgColor);
                setBackground(bgColor);
                setFocusPainted(false);
                setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                setMaximumSize(new Dimension(150, 40)); // Kích thước nhỏ hơn cho taskbar

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (shrinkTimer != null && shrinkTimer.isRunning()) shrinkTimer.stop();
                        enlargeTimer = new Timer(15, ev -> {
                            if (scale < maxScale) {
                                scale += step;
                                repaint();
                            } else {
                                scale = maxScale;
                                ((Timer) ev.getSource()).stop();
                            }
                        });
                        enlargeTimer.start();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (enlargeTimer != null && enlargeTimer.isRunning()) enlargeTimer.stop();
                        shrinkTimer = new Timer(15, ev -> {
                            if (scale > minScale) {
                                scale -= step;
                                repaint();
                            } else {
                                scale = minScale;
                                ((Timer) ev.getSource()).stop();
                            }
                        });
                        shrinkTimer.start();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int newWidth = (int) (width * scale);
                int newHeight = (int) (height * scale);
                int x = (width - newWidth) / 2;
                int y = (height - newHeight) / 2;

                g2.setColor(getBackground());
                g2.fillRoundRect(x, y, newWidth, newHeight, 20, 20);

                g2.setFont(getFont().deriveFont(getFont().getSize2D() * scale));
                FontMetrics fm = g2.getFontMetrics();
                int stringWidth = fm.stringWidth(getText());
                int stringHeight = fm.getAscent();

                g2.setColor(getForeground());
                g2.drawString(getText(), (width - stringWidth) / 2, (height + stringHeight) / 2 - 4);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(100, 100, 100));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }

            @Override
            public boolean isContentAreaFilled() {
                return false;
            }
        };
        return btn;
    }

    // ==== Xử lý click menu ====
    private class MenuButtonListener implements ActionListener {
        private final String buttonName;

        public MenuButtonListener(String buttonName) {
            this.buttonName = buttonName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Bạn đã chọn: " + buttonName);
            if (buttonName.equals("Đăng xuất")) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else {
                // Hiển thị panel tương ứng với menu được chọn
                cardLayout.show(mainContentPanel, buttonName);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePageWithActions::new);
    }
}