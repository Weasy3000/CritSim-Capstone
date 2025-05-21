package Positions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PositionSelect extends JFrame {

    private static final long serialVersionUID = 1L;

    public PositionSelect() {
        setTitle("Select Your Training Role");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(15, 15, 15));

        
        JLabel title = new JLabel("Choose Your Training Role", SwingConstants.CENTER);
        title.setForeground(Color.CYAN);
        title.setFont(new Font("Bauhaus 93", Font.BOLD, 36));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBackground(new Color(15, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        String[] positions = {"HR", "Operator", "Engineer", "Maintenance", "Management"};

        for (String position : positions) {
            JButton btn = new JButton(position);
            styleButton(btn);
            btn.addActionListener((ActionEvent e) -> {
                dispose();

                switch (position) {
                    case "Engineer":
                        new Engineer();
                        break;
                    case "HR":
                        new HR();
                        break;
                    case "Management":
                        new Management();
                        break;
                    case "Operator":
                        new Operator();
                        break;
                    case "Maintenance":
                        new Maintenance();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                            "Unknown role selected.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);

       
        JButton quitButton = new JButton("Quit");
        styleButton(quitButton);
        quitButton.setPreferredSize(new Dimension(100, 40));
        quitButton.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to quit?",
                "Confirm Quit",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        JPanel quitPanel = new JPanel();
        quitPanel.setBackground(new Color(15, 15, 15));
        quitPanel.add(quitButton);
        add(quitPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(26, 26, 26));
        button.setForeground(Color.CYAN);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    }
}
