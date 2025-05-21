package Positions;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;


public class Leaderboard extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

    public Leaderboard() {
        setTitle("Leaderboard");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.BLACK);

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(Color.DARK_GRAY);
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> dispose());

        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(30, 30, 30));
        resetButton.setForeground(Color.CYAN);
        resetButton.setFocusPainted(false);
        resetButton.setPreferredSize(new Dimension(80, 25));
        resetButton.setToolTipText("Clear all leaderboard scores");

        resetButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to reset the leaderboard?",
                "Reset Leaderboard",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                clearLeaderboardFile();
                textArea.setText("");
                JOptionPane.showMessageDialog(this, "Leaderboard has been reset.");
            }
        });

        bottomPanel.add(resetButton);
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
        displayLeaderboardWithEffect();
    }

    private void clearLeaderboardFile() {
		
	}

	private void displayLeaderboardWithEffect() {
        new Thread(() -> {
            List<String> lines = loadAndSortLeaderboard();
            for (String line : lines) {
                typeLine(line + "\n");
            }
        }).start();
    }

    private void typeLine(String line) {
        for (char c : line.toCharArray()) {
            try {
                Thread.sleep(30); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> textArea.append(String.valueOf(c)));
        }
    }

    private List<String> loadAndSortLeaderboard() {
        List<String> entries = new ArrayList<>();

        File file = new File("leaderboard.txt");
        System.out.println("📁 Reading leaderboard from: " + file.getAbsolutePath()); 

        if (!file.exists()) {
            entries.add("No leaderboard data found.");
            return entries;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                entries.add(line);
            }
        } catch (IOException e) {
            entries.add("Error reading leaderboard.");
        }

        entries.sort((a, b) -> {
            try {
                int scoreA = extractScore(a);
                int scoreB = extractScore(b);
                return Integer.compare(scoreB, scoreA);
            } catch (Exception e) {
                return 0;
            }
        });

        return entries;
    }


    private int extractScore(String entry) {
        try {
            String[] parts = entry.split("Score: ");
            if (parts.length < 2) return 0;
            String[] afterScore = parts[1].split(" ");
            return Integer.parseInt(afterScore[0]);
        } catch (Exception e) {
            return 0;
        }
    }




    }

