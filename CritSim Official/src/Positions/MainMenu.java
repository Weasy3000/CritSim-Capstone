package Positions;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel creditLabel;

    public MainMenu() {
        setTitle("CritSim");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.setBackground(new Color(15, 15, 15));

        JButton resetButton = new JButton("⟳");
        resetButton.setToolTipText("Reset Leaderboard");
        resetButton.setPreferredSize(new Dimension(40, 25));
        resetButton.setBackground(new Color(30, 30, 30));
        resetButton.setForeground(Color.CYAN);
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resetButton.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.CYAN));

        resetButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to reset the leaderboard?",
                "Confirm Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                clearLeaderboardFile();
            }
        });

        adminPanel.add(resetButton);
        add(adminPanel, BorderLayout.NORTH);

        getContentPane().setBackground(new Color(15, 15, 15)); 

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(15, 15, 15));

        
        JPanel resetRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        resetRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        resetRow.setBackground(new Color(15, 15, 15));

        JButton resetButton1 = new JButton("Reset");
        resetButton1.setPreferredSize(new Dimension(80, 25)); 
        resetButton1.setToolTipText("Reset Leaderboard");
        resetButton1.setPreferredSize(new Dimension(40, 25));
        resetButton1.setBackground(new Color(30, 30, 30));
        resetButton1.setForeground(Color.CYAN);
        resetButton1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resetButton1.setFocusPainted(false);
        resetButton1.setBorder(BorderFactory.createLineBorder(Color.CYAN));

        resetButton1.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to reset the leaderboard?",
                "Confirm Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                clearLeaderboardFile();
            }
        });

        resetRow.add(resetButton1);
        textPanel.add(resetRow); 

      
        titleLabel = new JLabel("");
        titleLabel.setForeground(Color.CYAN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 48));

        subtitleLabel = new JLabel("");
        subtitleLabel.setForeground(Color.LIGHT_GRAY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        creditLabel = new JLabel("");
        creditLabel.setForeground(Color.GRAY);
        creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(subtitleLabel);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(creditLabel);
        textPanel.add(Box.createVerticalStrut(20));

        add(textPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(15, 15, 15));
        buttonPanel.setLayout(new GridLayout(4, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton startButton = new JButton("Start Game");
        JButton leaderboardButton = new JButton("Leaderboard");
        JButton optionsButton = new JButton("Options");
        JButton quitButton = new JButton("Quit");

        styleButton(startButton);
        styleButton(leaderboardButton);
        styleButton(optionsButton);
        styleButton(quitButton);

        startButton.addActionListener(e -> {
            String player = UserLogin.getUsername();  

            if (!player.isEmpty()) {
                dispose();             
                new PositionSelect();       
            } else {
                JOptionPane.showMessageDialog(this, "Name not entered. Please restart the program.");
            }
        });


        leaderboardButton.addActionListener(e -> new Leaderboard());

        optionsButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Options menu coming soon!", "Options", JOptionPane.INFORMATION_MESSAGE)
        );

        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
        animateTitle();
    }

    private void clearLeaderboardFile() {
		
	}

	private void styleButton(JButton button) {
        button.setBackground(new Color(26, 26, 26));       
        button.setForeground(Color.CYAN);                   
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    }

    private void animateTitle() {
        new Thread(() -> {
            String titleText = "CritSim";
            String subtitleText = "Interactive Training Done Right.";
            String creditText = "Developed By Michael Katz";

          
            StringBuilder titleBuilder = new StringBuilder();
            for (char c : titleText.toCharArray()) {
                titleBuilder.append(c);
                titleLabel.setText(titleBuilder.toString());
                sleep(150);
            }

         
            StringBuilder subtitleBuilder = new StringBuilder();
            for (char c : subtitleText.toCharArray()) {
                subtitleBuilder.append(c);
                subtitleLabel.setText(subtitleBuilder.toString());
                sleep(40);
            }

           
            StringBuilder creditBuilder = new StringBuilder();
            for (char c : creditText.toCharArray()) {
                creditBuilder.append(c);
                creditLabel.setText(creditBuilder.toString());
                sleep(40);
            }
        }).start();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main1(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
    public static void main(String[] args) {
        UserLogin.getUsername(); 
        SwingUtilities.invokeLater(() -> new MainMenu());
    }


}
