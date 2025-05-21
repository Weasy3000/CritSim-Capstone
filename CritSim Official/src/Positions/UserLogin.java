package Positions;

import javax.swing.*;
import java.awt.*;

public class UserLogin {
    private static String username = "";

    public static String getUsername() {
        if (username.isEmpty()) {
           
            JDialog dialog = new JDialog((Frame) null, "User Login", true);
            dialog.setSize(400, 200);
            dialog.setLayout(new BorderLayout());
            dialog.getContentPane().setBackground(Color.BLACK);

            JLabel label = new JLabel("Enter your name to begin:", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(Color.WHITE);

            JTextField nameField = new JTextField();
            nameField.setHorizontalAlignment(JTextField.CENTER);
            nameField.setFont(new Font("Arial", Font.PLAIN, 14));
            nameField.setBackground(Color.DARK_GRAY);
            nameField.setForeground(Color.WHITE);
            nameField.setCaretColor(Color.WHITE);

            JButton startButton = new JButton("Start");
            startButton.setFont(new Font("Arial", Font.BOLD, 14));
            startButton.setBackground(Color.GRAY);
            startButton.setForeground(Color.WHITE);
            startButton.setFocusPainted(false);

            startButton.addActionListener(e -> {
                String input = nameField.getText().trim();
                if (!input.isEmpty()) {
                    username = input;
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please enter your name.");
                }
            });

            JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
            centerPanel.setBackground(Color.BLACK);
            centerPanel.add(label);
            centerPanel.add(nameField);

            dialog.add(centerPanel, BorderLayout.CENTER);
            dialog.add(startButton, BorderLayout.SOUTH);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);  
        }

        return username;
    }
}
