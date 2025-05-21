package Positions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class HR extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<DecisionLog> decisionHistory = new ArrayList<>();
    private String currentScenarioLabel = "";
    private JTextArea storyArea;
    private JPanel optionPanel;
    private int score = 0;
    private final String playerName = UserLogin.getUsername();
    private List<JButton> optionButtons = new ArrayList<>(); 

    public HR() {
        setTitle("HR Simulation");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        storyArea.setBackground(Color.BLACK);
        storyArea.setForeground(Color.WHITE);
        storyArea.setCaretColor(Color.WHITE);
        add(new JScrollPane(storyArea), BorderLayout.CENTER);

        optionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionPanel.setBackground(Color.BLACK);
        add(optionPanel, BorderLayout.SOUTH);

        startIntro();
        setVisible(true);
    }

    private void startIntro() {
        typewriterEffect1("You arrive at the corporate office for your first day as an HR Specialist. " +
                         "The receptionist is busy handling another visitor. " +
                         "The security badge station is unattended. You stand there, unsure what to do next...", 
            () -> presentScenario1());
    }


    private void presentScenario1() {
        currentScenarioLabel = "Scenario 1:";
        optionPanel.removeAll();

        String text = "You're at the corporate office on your first day as an HR Specialist. The receptionist is busy, and the badge station is unattended. What do you do?";

        typewriterEffect1(text, () -> {
            addOption("Wait patiently by the front desk.",
                "You wait. The receptionist notices and assists with onboarding.",
                () -> { score++; presentScenario2(); });

            addOption("Try to enter the secure area without a badge.",
                "You attempt to sneak in. Security reviews footage, and you are formally warned.",
                () -> { score -= 2; presentScenario2(); });

            addOption("Call your manager to inform them you're stuck.",
                "You call your manager. They send someone to escort you in.",
                this::presentScenario2);

            addOption("Leave and go home frustrated.",
                "Terminated: You abandoned your first day of work.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: You abandoned your first day of work.");
                    endSimulation("Terminated: Walked out on first day.");
                });

            shuffleAndDisplayOptions();
        });
    }



    private void presentScenario2() {
        currentScenarioLabel = "Scenario 2:";
        optionPanel.removeAll();

        String text = "You receive an email from an unknown address asking for confidential salary data. What do you do?";

        typewriterEffect1(text, () -> {
            addOption("Forward the email to your supervisor.",
                "You report it to your supervisor. IT confirms it was a phishing attempt.",
                () -> { score++; presentScenario3(); });

            addOption("Reply and ask for identification.",
                "You request ID. The sender never replies — likely a phishing attempt.",
                () -> { score--; presentScenario3(); });

            addOption("Send the requested data immediately.",
                "Terminated: Sharing confidential info without verification.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: You exposed private employee data.");
                    endSimulation("Terminated for security breach.");
                });

            addOption("Delete the email and do nothing.",
                "You delete the email. IT later flags the unreported phishing attempt.",
                () -> { score--; presentScenario3(); });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario3() {
        currentScenarioLabel = "Scenario 3:";
        optionPanel.removeAll();

        String text = "An employee quietly approaches you about suspected harassment but is unsure how to proceed.";

        typewriterEffect1(text, () -> {
            addOption("Listen carefully and document everything neutrally.",
                "You stay professional and record the issue. Follow-up investigation will proceed.",
                () -> { score++; presentScenario4(); });

            addOption("Immediately confront the accused coworker.",
                "You act too fast and escalate the situation. Legal issues arise.",
                () -> { score -= 2; presentScenario4(); });

            addOption("Tell the employee to ignore it and move on.",
                "You dismiss the concern. A formal complaint follows.",
                () -> { score -= 2; presentScenario4(); });

            addOption("Alert the supervisor without discretion.",
                "You break confidentiality. Trust in HR drops.",
                () -> { score--; presentScenario4(); });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario4() {
        currentScenarioLabel = "Scenario 4:";
        optionPanel.removeAll();

        String text = "An employee posts inappropriate content about the company on social media during work hours.";

        typewriterEffect1(text, () -> {
            addOption("Gather evidence discreetly before escalating.",
                "You collect proof and submit it to HR leadership.",
                () -> { score++; presentScenario5(); });

            addOption("Call the employee into your office and reprimand them.",
                "You act too quickly. The employee denies everything and tensions rise.",
                () -> { score--; presentScenario5(); });

            addOption("Ignore it — social media isn't HR’s business.",
                "You ignore the situation. The post goes viral and damages the company.",
                () -> { score -= 2; presentScenario5(); });

            addOption("Delete the post yourself using IT access.",
                "Terminated: Unauthorized access to personal employee accounts.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: You accessed private employee systems.");
                    endSimulation("Terminated for violating digital privacy.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario5() {
        currentScenarioLabel = "Scenario 5:";
        optionPanel.removeAll();

        String text = "Two employees are arguing about harassment accusations during a team meeting. Emotions are high.";

        typewriterEffect1(text, () -> {
            addOption("Separate them and listen to each party individually.",
                "You de-escalate and document both perspectives. Mediation can proceed.",
                () -> { score++; presentScenario6(); });

            addOption("Immediately suspend both employees pending investigation.",
                "You overreact and HR leadership questions your decision.",
                () -> { score--; presentScenario6(); });

            addOption("Ask them to resolve it privately and return to work.",
                "You push the issue aside. The situation worsens later.",
                () -> { score -= 2; presentScenario6(); });

            addOption("Ignore the situation entirely.",
                "Terminated: You failed to intervene during a harassment incident.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Ignored serious workplace conflict.");
                    endSimulation("Terminated for negligence.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario6() {
        setCurrentScenarioLabel("Scenario 6:");
        optionPanel.removeAll();

        typewriterEffect1("You receive a complaint from an employee who feels their performance review was unfairly low and suspects bias from their supervisor.\n\nHow do you respond?", () -> {
            addOption("Listen carefully and investigate the review history.",
                    "You listen thoroughly, check the supervisor's history, and find inconsistencies worth investigating.",
                    () -> { score++; presentScenario7(); });

            addOption("Tell the employee to accept it and work harder.",
                    "You dismiss the complaint, leaving the employee feeling demotivated and unheard.",
                    () -> { score--; presentScenario7(); });

            addOption("Side with the supervisor without reviewing any documents.",
                    "You defend the supervisor without investigation, damaging trust in HR.",
                    () -> { score -= 2; presentScenario7(); });

            addOption("Advise the employee to find another job if they’re unhappy.",
                    "Terminated: Retaliation against a workplace concern.",
                    () -> {
                        score -= 100;
                        JOptionPane.showMessageDialog(this, "Terminated: Retaliation against a workplace concern.");
                        endSimulation("Terminated for mishandling workplace fairness complaints.");
                    });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario7() {
        setCurrentScenarioLabel("Scenario 7:");
        optionPanel.removeAll();

        typewriterEffect1("An employee emails you requesting immediate medical leave for a personal health condition. They have no formal documentation yet.\n\nHow do you respond?", () -> {
            addOption("Acknowledge the request and explain the documentation needed.",
                    "You explain the leave process and request proper forms while respecting their situation.",
                    () -> { score++; presentScenario8(); });

            addOption("Deny the request immediately because paperwork isn't complete.",
                    "You deny the leave request prematurely. The employee files a complaint for lack of accommodation.",
                    () -> { score--; presentScenario8(); });

            addOption("Ignore the email — they can figure it out later.",
                    "You ignore the urgent request. The employee escalates the issue to HR leadership.",
                    () -> { score -= 2; presentScenario8(); });

            addOption("Share their private medical details with their team.",
                    "Terminated: Violated employee medical privacy laws (HIPAA/FMLA).",
                    () -> {
                        score -= 100;
                        JOptionPane.showMessageDialog(this, "Terminated: Violated employee medical privacy laws (HIPAA/FMLA).");
                        endSimulation("Terminated for breach of confidentiality regarding medical leave.");
                    });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario8() {
        setCurrentScenarioLabel("Scenario 8:");
        optionPanel.removeAll();

        typewriterEffect1("Late in the day, you are asked to help prepare layoff documents for several employees. The layoffs have not yet been announced.\n\nWhat should you prioritize first?", () -> {
            addOption("Ensure confidentiality while preparing the documents.",
                    "You handle the paperwork discreetly to prevent rumors and protect company integrity.",
                    () -> { score++; presentScenario9(); });

            addOption("Warn your coworkers about the layoffs privately.",
                    "You leak the information, causing panic across departments before leadership is ready.",
                    () -> { score -= 2; presentScenario9(); });

            addOption("Publicly ask for help with the documents.",
                    "You ask around for help, accidentally revealing confidential layoff plans.",
                    () -> { score--; presentScenario9(); });

            addOption("Refuse to work on the layoff task and walk out.",
                    "Terminated: Failure to fulfill professional HR duties during sensitive operations.",
                    () -> {
                        score -= 100;
                        JOptionPane.showMessageDialog(this, "Terminated: Failure to fulfill professional HR duties during sensitive operations.");
                        endSimulation("Terminated for refusing essential HR duties.");
                    });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario9() {
        setCurrentScenarioLabel("Scenario 9:");
        optionPanel.removeAll();

        typewriterEffect1("Near the end of the day, an employee reports concerns about potential discrimination during a recent promotion decision. They feel overlooked based on personal characteristics unrelated to job performance.\n\nWhat is your response?", () -> {
            addOption("Take detailed notes and escalate to the HR Director immediately.",
                    "You document everything professionally and ensure leadership is aware quickly.",
                    () -> { score++; presentScenario10(); });

            addOption("Tell the employee promotions are purely based on performance — no action needed.",
                    "You dismiss their concern without investigation, risking a formal discrimination complaint.",
                    () -> { score--; presentScenario10(); });

            addOption("Advise the employee to drop it to avoid tension.",
                    "You discourage the report, which later becomes part of a larger legal investigation.",
                    () -> { score -= 2; presentScenario10(); });

            addOption("Publicly question the supervisor about discrimination at the next team meeting.",
                    "Terminated: Mishandling sensitive discrimination complaint publicly.",
                    () -> {
                        score -= 100;
                        JOptionPane.showMessageDialog(this, "Terminated: Mishandling sensitive discrimination complaint publicly.");
                        endSimulation("Terminated for violating workplace investigation protocols.");
                    });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario10() {
        setCurrentScenarioLabel("Scenario 10:");
        optionPanel.removeAll();

        typewriterEffect1("At the end of your shift, HR leadership asks you to provide a summary report of any major incidents handled today. They need accurate and concise information.\n\nHow do you respond?", () -> {
            addOption("Prepare an accurate, neutral summary of all incidents.",
                    "You submit a professional report that helps leadership understand the day's challenges.",
                    () -> { score++; completeSimulation(); });

            addOption("Only report the positive events to make yourself look better.",
                    "You omit key problems. Leadership later discovers missing information, hurting your credibility.",
                    () -> { score--; completeSimulation(); });

            addOption("Exaggerate minor issues to seem more important.",
                    "You exaggerate events. Leadership becomes skeptical of your judgment going forward.",
                    () -> { score -= 2; completeSimulation(); });

            addOption("Refuse to submit a report because you're tired.",
                    "Terminated: Failure to complete critical end-of-day HR duties.",
                    () -> {
                        score -= 100;
                        JOptionPane.showMessageDialog(this, "Terminated: Failure to complete critical end-of-day HR duties.");
                        endSimulation("Terminated for insubordination and neglect of responsibilities.");
                    });

            shuffleAndDisplayOptions();
        });
    }


    private void typewriterEffect1(String text, Runnable afterText) {
        storyArea.setText("");
        new Thread(() -> {
            for (char c : text.toCharArray()) {
                try {
                    Thread.sleep(20); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(() -> storyArea.append(String.valueOf(c)));
            }
            try {
                Thread.sleep(1500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(afterText); 
        }).start();
    }

    private void shuffleAndDisplayOptions() {
        java.util.Collections.shuffle(optionButtons);
        for (JButton b : optionButtons) {
            optionPanel.add(b);
        }
        optionButtons.clear();
        optionPanel.revalidate();
        optionPanel.repaint();
    }


    private void addOption(String label, String outcomeDescription, Runnable nextScenario) {
        JButton button = new JButton("<html><center>" + label + "</center></html>");
        button.setBackground(new Color(26, 26, 26));
        button.setForeground(Color.CYAN);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY); 
            }
        });

        button.addActionListener((ActionEvent e) -> {
            for (Component c : optionPanel.getComponents()) {
                c.setEnabled(false);
            }
            decisionHistory.add(new DecisionLog(currentScenarioLabel, label, outcomeDescription));
            storyArea.setText(""); 
            typewriterEffect1(outcomeDescription, nextScenario);
        });

        optionButtons.add(button);
    }


    private void endSimulation(String message) {
        JOptionPane.showMessageDialog(this, message + "\nScore: " + score + "\nPerformance: " + getRating());
        saveScoreToLeaderboard();
        returnToMainMenu();
    }

    private void completeSimulation() {
        StringBuilder summary = new StringBuilder("Simulation Complete.\nFinal Score: " + score + "\nPerformance: " + getRating() + "\n\nDecision Summary:\n\n");

        for (DecisionLog decision : decisionHistory) {
            summary.append(decision.toString()).append("\n");
        }

        JTextArea summaryArea = new JTextArea(summary.toString());
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(summaryArea);
        scroll.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scroll, "Summary Report", JOptionPane.INFORMATION_MESSAGE);
        saveScoreToLeaderboard();
        returnToMainMenu();
    }

    private String getRating() {
        if (score >= 5) return "Excellent";
        if (score >= 3) return "Good";
        if (score >= 1) return "Fair";
        return "Poor";
    }

    private String getRoleTitle() {
        return "HR";
    }

    private void saveScoreToLeaderboard() {
        try (FileWriter fw = new FileWriter("leaderboard.txt", true)) {
            fw.write(playerName + " (" + getRoleTitle() + ") - Score: " + score + " - Rating: " + getRating() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to leaderboard.");
        }
    }

    private void returnToMainMenu() {
        dispose();
        new MainMenu();
    }

	public String getCurrentScenarioLabel() {
		return currentScenarioLabel;
	}

	public void setCurrentScenarioLabel(String currentScenarioLabel) {
		this.currentScenarioLabel = currentScenarioLabel;
	}
}
