package Positions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Maintenance extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<DecisionLog> decisionHistory = new ArrayList<>();
    private String currentScenarioLabel = "";
    private JTextArea storyArea;
    private JPanel optionPanel;
    private List<JButton> optionButtons = new ArrayList<>();
    private int score = 0;
    private final String playerName = UserLogin.getUsername();

    public Maintenance() {
        setTitle("Maintenance Simulation");
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
        add(new JScrollPane(storyArea), BorderLayout.CENTER);

        optionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionPanel.setBackground(Color.BLACK);
        add(optionPanel, BorderLayout.SOUTH);

        typewriterEffect("You clock into the maintenance shop and check the board. A lathe in Bay 3 is out of alignment and marked 'Urgent'. You grab your toolkit and head over.", this::presentScenario1);

        setVisible(true);
    }

    private void presentScenario1() {
        currentScenarioLabel = "Scenario 1:";
        optionPanel.removeAll();
        String text = "You're on the shop floor when a machine alarm goes off. It’s still running but louder than usual.\nWhat do you do?";

        typewriterEffect(text, () -> {
            addOption("Pause the machine and inspect it personally.",
                "You identify a loose panel and tighten it. The alarm stops. Minimal downtime. Good call.",
                () -> {
                    score++;
                    presentScenario2();
                });

            addOption("Ignore it since it's still running.",
                "Hours later, the machine jams and production halts. The supervisor notes the alarm was ignored.",
                () -> {
                    score -= 2;
                    presentScenario2();
                });

            addOption("Call another tech to look at it instead.",
                "The other tech fixes the issue but notes you didn’t report or log the alarm.",
                () -> {
                    score--;
                    presentScenario2();
                });

            addOption("Slap the panel to make it stop.",
                "You cause more damage. Safety logs show improper response. You're written up.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Unsafe behavior caused mechanical damage.");
                    endSimulation("Terminated due to reckless machine handling.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario2() {
        currentScenarioLabel = "Scenario 2:";
        optionPanel.removeAll();
        String text = "You're assigned to clean hydraulic oil spills near the press line. There’s a backlog of other tasks.\nWhat’s your approach?";

        typewriterEffect(text, () -> {
            addOption("Clean the spill immediately.",
                "You prevent a potential slip hazard. Safety team thanks you for following procedure.",
                () -> {
                    score++;
                    presentScenario3();
                });

            addOption("Mark the area with cones but delay cleanup.",
                "Cones help but someone slips anyway. You’re questioned for delaying cleanup.",
                () -> {
                    score--;
                    presentScenario3();
                });

            addOption("Assign the task to someone else without telling them why.",
                "The task is skipped. Management asks who ignored the safety hazard.",
                () -> {
                    score -= 2;
                    presentScenario3();
                });

            addOption("Ignore the spill—it’s been there all week.",
                "The next shift reports it and names you as having passed by. You’re held responsible.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Negligent behavior led to a safety incident.");
                    endSimulation("Terminated for neglecting workplace safety.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario3() {
        currentScenarioLabel = "Scenario 3:";
        optionPanel.removeAll();
        String text = "You’re sent to replace a worn-out bearing. The new one in inventory looks slightly different.\n\nWhat do you do?";

        typewriterEffect(text, () -> {
            addOption("Stop and check the part number with engineering.",
                "They confirm it’s a revised spec part. You install it correctly with confidence.",
                () -> {
                    score++;
                    presentScenario4();
                });

            addOption("Install it anyway—it probably fits.",
                "It fits but fails in operation. Engineering traces the error to incorrect part use.",
                () -> {
                    score -= 2;
                    presentScenario4();
                });

            addOption("Wait until someone else is free to confirm.",
                "Delays mount while you wait. Management notes the stall and lack of initiative.",
                () -> {
                    score--;
                    presentScenario4();
                });

            addOption("File it back in inventory and pretend the ticket wasn’t yours.",
                "QA runs an audit and sees the wrong part was logged. You’re cited for process tampering.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Attempted to cover up incorrect maintenance ticket.");
                    endSimulation("Terminated for falsifying maintenance actions.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario4() {
        currentScenarioLabel = "Scenario 4:";
        optionPanel.removeAll();
        String text = "A junior tech begins working on a machine without locking it out. You notice from across the bay.\n\nHow do you respond?";

        typewriterEffect(text, () -> {
            addOption("Immediately stop them and explain the danger.",
                "They thank you after you explain the proper lockout-tagout steps. Supervisor commends your intervention.",
                () -> {
                    score++;
                    presentScenario5();
                });

            addOption("Report them anonymously after your shift.",
                "They continue their work and nothing goes wrong, but your delayed report is questioned.",
                () -> {
                    score--;
                    presentScenario5();
                });

            addOption("Yell across the bay to get their attention.",
                "You stop them, but your method causes confusion and embarrassment.",
                () -> {
                    score--;
                    presentScenario5();
                });

            addOption("Let them finish—you assume they know what they’re doing.",
                "They shock themselves on the live panel. A report is filed. You’re listed as a witness who said nothing.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: You failed to prevent a major safety violation.");
                    endSimulation("Terminated due to failure to intervene in a lockout-tagout breach.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario5() {
        currentScenarioLabel = "Scenario 5:";
        optionPanel.removeAll();
        String text = "A contractor is wiring in a new motor. You overhear them ask another tech, 'Does it matter which terminal gets grounded?'\n\nWhat’s your next move?";

        typewriterEffect(text, () -> {
            addOption("Step in and review the wiring diagram with them.",
                "They thank you. You prevent a reversed polarity issue and reinforce teamwork expectations.",
                () -> {
                    score++;
                    presentScenario6();
                });

            addOption("Tell them to ask a supervisor—you’re not responsible.",
                "They delay work until a supervisor arrives. The job gets done, but the delay is noted.",
                () -> {
                    score--;
                    presentScenario6();
                });

            addOption("Say nothing. You assume they'll figure it out.",
                "They wire it wrong. The motor fails a test. Your silence gets flagged in the investigation.",
                () -> {
                    score -= 2;
                    presentScenario6();
                });

            addOption("Laugh and say 'you’ll find out when it sparks!'",
                "Their mistake causes a short circuit. Safety logs the incident as preventable.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Reckless conduct contributed to system failure.");
                    endSimulation("Terminated for encouraging negligent behavior.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario6() {
        currentScenarioLabel = "Scenario 6:";
        optionPanel.removeAll();

        String text = "While repairing a conveyor belt, you notice a bolt missing from the support bracket. The system runs fine for now.\n\nWhat do you do?";

        typewriterEffect(text, () -> {
            addOption("Stop the line and install a new bolt immediately.",
                "You prevent long-term misalignment. Maintenance logs reflect your attention to detail.",
                () -> {
                    score++;
                    presentScenario7();
                });

            addOption("Log it for the next shift since it’s minor.",
                "Next shift misses the log. The bracket fails. Management tracks the origin to your report.",
                () -> {
                    score -= 2;
                    presentScenario7();
                });

            addOption("Ignore it—everything seems stable.",
                "The belt tears during a later run. You're questioned for ignoring an obvious mechanical issue.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Neglected safety-critical mechanical damage.");
                    endSimulation("Terminated for ignoring a bracket failure warning.");
                });

            addOption("Tighten nearby bolts and call it a day.",
                "It holds a while, but the core issue wasn’t fixed. Your inspection notes are marked incomplete.",
                () -> {
                    score--;
                    presentScenario7();
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario7() {
        currentScenarioLabel = "Scenario 7:";
        optionPanel.removeAll();

        String text = "A maintenance request ticket shows a coolant leak in a compressor. You check it and only find residue—no active leak.\n\nWhat do you do?";

        typewriterEffect(text, () -> {
            addOption("Run a dye test to check for active leaks.",
                "You detect a slow leak and patch it properly. The system stays online without further downtime.",
                () -> {
                    score++;
                    presentScenario8();
                });

            addOption("Mark it as resolved and close the ticket.",
                "A few days later, pressure drops. Your name is attached to the closed report.",
                () -> {
                    score -= 2;
                    presentScenario8();
                });

            addOption("Clean the residue and watch it for 15 minutes.",
                "Nothing leaks in that time. You mark it as deferred. Later, the leak worsens.",
                () -> {
                    score--;
                    presentScenario8();
                });

            addOption("Ignore it—the ticket is probably old.",
                "The compressor overheats by morning. You’re questioned why no inspection notes exist.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Ignored maintenance ticket led to critical failure.");
                    endSimulation("Terminated due to failure to act on active maintenance ticket.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario8() {
        currentScenarioLabel = "Scenario 8:";
        optionPanel.removeAll();

        String text = "A supervisor asks if you can get a faulty motor back online temporarily while a replacement is on the way. It’s outside of standard repair procedure.\n\nWhat do you do?";

        typewriterEffect(text, () -> {
            addOption("Explain the risks and suggest a formal bypass approval.",
                "The supervisor agrees. You complete a safe workaround that meets compliance.",
                () -> {
                    score++;
                    presentScenario9();
                });

            addOption("Rig a temporary bypass without documentation.",
                "It works but fails overnight. The audit log shows no approval for your action.",
                () -> {
                    score -= 2;
                    presentScenario9();
                });

            addOption("Refuse to touch it without paperwork.",
                "You hold your ground. The delay frustrates your supervisor but avoids liability.",
                () -> {
                    score--;
                    presentScenario9();
                });

            addOption("Call someone else to take the job.",
                "They do it and take credit. Your team leader asks why you passed the request off.",
                () -> {
                    score--;
                    presentScenario9();
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario9() {
        currentScenarioLabel = "Scenario 9:";
        optionPanel.removeAll();

        String text = "You walk into the maintenance breakroom and overhear two techs talking about skipping required checklist items.\n\nWhat is your response?";

        typewriterEffect(text, () -> {
            addOption("Privately report it to your supervisor.",
                "The supervisor follows up discreetly. Policy reminders go out to all techs.",
                () -> {
                    score++;
                    presentScenario10();
                });

            addOption("Confront them loudly in the room.",
                "The room gets quiet. They deny everything and lodge a complaint about your tone.",
                () -> {
                    score--;
                    presentScenario10();
                });

            addOption("Let it go—it’s not your business.",
                "The trend continues and an incident happens. Cameras reveal you were present.",
                () -> {
                    score -= 2;
                    presentScenario10();
                });

            addOption("Ask them casually what they meant by 'skipping steps.'",
                "They explain more. You later forward the info to leadership. You're credited for improving oversight.",
                () -> {
                    score++;
                    presentScenario10();
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario10() {
        currentScenarioLabel = "Scenario 10:";
        optionPanel.removeAll();

        String text = "You're assigned to prep machines for an audit. You’re behind schedule, and one machine still shows a minor sensor fault.\n\nWhat’s your call?";

        typewriterEffect(text, () -> {
            addOption("Tag the machine as needing review and prioritize others.",
                "Auditors appreciate your honesty and diligence in the logbook. You pass the audit.",
                () -> {
                    score++;
                    completeSimulation();
                });

            addOption("Reset the system and hope it clears the fault code.",
                "The fault resets, but the machine throws an error during the audit. You’re questioned.",
                () -> {
                    score -= 2;
                    completeSimulation();
                });

            addOption("Skip it—it’s not a critical station.",
                "Auditors find the issue and flag your department for skipping inspections.",
                () -> {
                    score--;
                    completeSimulation();
                });

            addOption("Fake the logs so it looks like the machine passed.",
                "Audit catches the forgery. You're immediately dismissed.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Forged audit documentation.");
                    endSimulation("Terminated due to falsifying inspection records.");
                });

            shuffleAndDisplayOptions();
        });
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

    private void shuffleAndDisplayOptions() {
        java.util.Collections.shuffle(optionButtons);
        for (JButton b : optionButtons) {
            optionPanel.add(b);
        }
        optionButtons.clear();
        optionPanel.revalidate();
        optionPanel.repaint();
    }

    private void typewriterEffect(String text, Runnable afterText) {
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
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(afterText);
        }).start();
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

        button.addActionListener((ActionEvent e) -> {
            for (Component c : optionPanel.getComponents()) {
                c.setEnabled(false);
            }
            decisionHistory.add(new DecisionLog(currentScenarioLabel, label, outcomeDescription));
            nextScenario.run();
            
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(70, 70, 70));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(26, 26, 26));
                }
            });

        });

        optionButtons.add(button);
    }

    private String getRating() {
        if (score >= 3) return "Excellent";
        if (score == 2) return "Good";
        if (score == 1) return "Fair";
        return "Poor";
    }

    private void endSimulation(String message) {
        JOptionPane.showMessageDialog(this, message + "\nScore: " + score + "\nPerformance: " + getRating());
        saveScoreToLeaderboard();
        returnToMainMenu();
    }

    private String getRoleTitle() {
        return "Maintenance";
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
}
