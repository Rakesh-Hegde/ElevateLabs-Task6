import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame(" To-Do List ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My To-Do List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));

        JTextField taskField = new JTextField();
        taskField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JButton addButton = createStyledButton("Add Task", new Color(72, 201, 176));
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        taskListPanel.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setBorder(null);

        addButton.addActionListener(e -> {
            String taskText = taskField.getText().trim();
            if (!taskText.isEmpty()) {
                addTask(taskListPanel, taskText);
                taskField.setText("");
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task!");
            }
        });

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.BEFORE_FIRST_LINE);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void addTask(JPanel taskListPanel, String taskText) {
        JPanel taskPanel = new JPanel(new BorderLayout(10, 0));
        taskPanel.setBackground(Color.WHITE);
        taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        String dateTime = sdf.format(new Date());
        JLabel taskLabel = new JLabel(taskText + "  (" + dateTime + ")");
        taskLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton deleteButton = createStyledButton("Delete", new Color(231, 76, 60));
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        deleteButton.setPreferredSize(new Dimension(80, 30));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskListPanel.remove(taskPanel);
                taskListPanel.revalidate();
                taskListPanel.repaint();
            }
        });

        taskPanel.add(taskLabel, BorderLayout.CENTER);
        taskPanel.add(deleteButton, BorderLayout.EAST);

        taskListPanel.add(taskPanel);
    }

    private static JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        return button;
    }
}