package Positions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Engineer extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<DecisionLog> decisionHistory = new ArrayList<>();
    private String currentScenarioLabel = "";
    private JTextArea storyArea;
    private JPanel optionPanel;
    private int score = 0;
    private final String playerName = UserLogin.getUsername();
    private List<JButton> optionButtons = new ArrayList<>();

    public Engineer() {
        setTitle("Engineer Simulation");
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

        startIntro();
        setVisible(true);
    }

    private void startIntro() {
        typewriterEffect("You arrive at the engineering facility for your shift. You notice the badge scanner is unresponsive, and the front door is locked. You stand by the door, unsure what to do next...", this::presentScenario1);
    }

    private void presentScenario1() {
        currentScenarioLabel = "Scenario 1:";
        optionPanel.removeAll();

        addOption("Call your supervisor..",
                  "You call your supervisor. They send someone to let you in and appreciate your initiative.",
                  () -> {
                      score++;
                      typewriterEffect("\nYou call your supervisor. They send someone to let you in and appreciate your initiative.", this::presentScenario2);
                  });

        addOption("Try to open the door with a bit more force. Considering it looks broken, it couldn't get worse.",
                  "You attempt to force the door and damage the lock. Security reviews the footage and sees you causing the issue. Management isn't happy.",
                  () -> {
                      score -= 2;
                      typewriterEffect("\nYou attempt to force the door and damage the lock. Security reviews the footage and sees you causing the issue. Management isn't happy.", this::presentScenario2);
                  });

        addOption("Wait for another worker to arrive and see what they do.",
                  "You wait patiently. Another employee calls their supervisor. You're let in, but your lack of action isn't praised.",
                  () -> {
                      typewriterEffect("\nYou wait patiently. Eventually, another employee arrives and calls their supervisor to let you in. You inform them you were waiting awhile which doesn’t reflect well on your critical thinking skills.", this::presentScenario2);
                  });

        addOption("Walk to and wait in your car.",
                  "You left your shift without checking in.",
                  () -> {
                      score -= 100;
                      JOptionPane.showMessageDialog(this, "Terminated: You left your shift without checking in.");
                      endSimulation("Terminated: Walked out on first day.");
                  });

        shuffleAndDisplayOptions();
    }

    private void presentScenario2() {
        currentScenarioLabel = "Scenario 2:";
        optionPanel.removeAll();

        typewriterEffect("A critical machine suddenly goes offline. Your manager is out of office, and only one technician is available. The equipment’s manual is outdated, and downtime costs thousands per hour.\n\nWhat is your first course of action?", () -> {
            addOption("Try to isolate the failure through system BIOS settings.",
                      "You run BIOS diagnostics and isolate the fault to a power fluctuation in the relay board. You’ve narrowed the problem for a safe repair.",
                      () -> {
                          score++;
                          typewriterEffect("You run BIOS diagnostics and isolate the fault to a power fluctuation in the relay board. You’ve narrowed the problem for a safe repair.", this::presentScenario3);
                      });

            addOption("Call the head of the department to see if they have dealt with a similar issue before.",
                      "You spend over an hour calling management, but they are out. Eventually the system reboots itself but time and credibility are lost.",
                      () -> {
                          score -= 2;
                          typewriterEffect("You spend over an hour calling management, but they are out working on something else. Eventually, the system reboots itself, but time and credibility are lost.", this::presentScenario3);
                      });

            addOption("Inform the technician they need to fix the issue.",
                      "The technician struggles and misdiagnoses the issue, worsening the problem.",
                      () -> {
                          score -= 2;
                          typewriterEffect("The technician struggles alone and misdiagnoses the issue, making it worse.", this::presentScenario3);
                      });

            addOption("Shut the system down completely and go leave it to reboot on its own.",
                      "Walked away from a critical incident.",
                      () -> {
                          score -= 100;
                          JOptionPane.showMessageDialog(this, "Terminated: Walked away from a critical incident.");
                          endSimulation("Terminated for abandoning crisis resolution.");
                      });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario3() {
        currentScenarioLabel = "Scenario 3:";
        optionPanel.removeAll();

        typewriterEffect("You’re assigned to test a new software tool controlling motor speeds. The documentation is sparse and there's no test plan. Your team looks to you to figure out what to do.\n\nWhat approach do you take?", () -> {
            addOption("Draft your own test cases based on system requirements through the manual.",
                      "You write structured tests and discover a critical bug. Your initiative prevents future failures.",
                      () -> {
                          score++;
                          typewriterEffect("You write structured tests and discover a critical bug. Your initiative prevents future failures.", this::presentScenario4);
                      });

            addOption("Run as many tests as possible.",
                      "You catch one issue, but miss deeper flaws. The software later causes an incident in production.",
                      () -> {
                          score--;
                          typewriterEffect("You catch one issue, but miss deeper flaws. The software later causes an incident in production.", this::presentScenario4);
                      });

            addOption("Refuse to test without proper documentation.",
                      "You stall the rollout. Management is frustrated by the lack of progress.",
                      () -> {
                          score -= 2;
                          typewriterEffect("You stall the rollout, and management is frustrated by the lack of progress.", this::presentScenario4);
                      });

            addOption("Copy test procedures from reddit.",
                      "The tests are irrelevant and don’t meet standards. Tool passes falsely and causes issues post-deployment.",
                      () -> {
                          score -= 2;
                          typewriterEffect("The tests are irrelevant and do not meet production standards. This results in the tool passing falsely. Issues emerge post-deployment.", this::presentScenario4);
                      });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario4() {
        currentScenarioLabel = "Scenario 4:";
        optionPanel.removeAll();

        typewriterEffect("A supplier sends you a component that fails your inspection, but the team insists on installing it anyway to meet delivery deadlines.\n\nDo you:", () -> {
            addOption("Document your concerns and make a quick walk to quality control.",
                      "You notify QC. They halt installation and thank you for preventing liability.",
                      () -> {
                          score++;
                          typewriterEffect("You notify QC. They halt installation and thank you for preventing liability.", this::presentScenario5);
                      });

            addOption("Install it, as this production run had to be done a few hours ago.",
                      "The system fails during operation. You're questioned for approving faulty parts.",
                      () -> {
                          score -= 2;
                          typewriterEffect("The system fails during operation. You're questioned for approving faulty parts.", this::presentScenario5);
                      });

            addOption("Inform coworkers about the system not working well, but don’t file a report.",
                      "You vent frustration but take no action. The faulty part is installed.",
                      () -> {
                          score--;
                          typewriterEffect("You vent your frustration but the part goes in due to waiting. Later, no record exists showing you flagged it.", this::presentScenario5);
                      });

            addOption("Refuse to touch the part, it's QC’s job to deal with this.",
                      "Unprofessional conduct in critical inspection role.",
                      () -> {
                          score -= 100;
                          JOptionPane.showMessageDialog(this, "Terminated: Unprofessional conduct in critical inspection role.");
                          endSimulation("Terminated for abandoning duty.");
                      });

            shuffleAndDisplayOptions();
        });
    }


    private void presentScenario5() {
    	currentScenarioLabel = "Scenario 5:";
        optionPanel.removeAll();

        typewriterEffect("You are running a diagnostic tool when an unfamiliar alert code appears.\n\nHow would you proceed?", () -> {
            optionPanel.removeAll();

            JTextField inputField = new JTextField();
            inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
            inputField.setBackground(Color.DARK_GRAY);
            inputField.setForeground(Color.WHITE);
            inputField.setCaretColor(Color.WHITE);
            inputField.setBorder(BorderFactory.createLineBorder(Color.CYAN));

            JButton submit = new JButton("Submit Response");
            submit.setBackground(new Color(26, 26, 26));
            submit.setForeground(Color.CYAN);
            submit.setFont(new Font("Segoe UI", Font.BOLD, 16));
            submit.setBorder(BorderFactory.createLineBorder(Color.CYAN));

            submit.addActionListener((ActionEvent e) -> {
                String response = inputField.getText().toLowerCase().trim();
                optionPanel.removeAll();

                if (response.contains("log") || response.contains("reference") || response.contains("manual")) {
                    score++;
                    typewriterEffect("You look up the alert in the system logs and reference manual. You identify it as a sensor calibration fault.", this::presentScenario6);
                } else if (response.contains("restart") || response.contains("reboot")) {
                    score -= 2;
                    typewriterEffect("You restart the system blindly. The issue returns and now includes an additional fault code.", this::presentScenario6);
                } else {
                    score--;
                    typewriterEffect("Your approach doesn’t resolve the issue. Next shift has to investigate the alert properly.", this::presentScenario6);
                }
            });

            optionPanel.setLayout(new BorderLayout(10, 10));
            optionPanel.add(inputField, BorderLayout.CENTER);
            optionPanel.add(submit, BorderLayout.SOUTH);
            optionPanel.revalidate();
            optionPanel.repaint();
        });
    }

    private void presentScenario6() {
        currentScenarioLabel = "Scenario 6:";
        optionPanel.removeAll();
        resetToGridLayout();


        String text = "\nYou're reviewing the torque settings for a robotic arm that will operate under varying loads. The documentation says the standard torque should be 150 Nm, but during testing you notice the readings fluctuate between 142 and 160 Nm.\n\nWhat do you do?";

        typewriterEffect(text, () -> {
            addOption("Proceed as normal—the average is within acceptable range.",
                "The average is close, but the inconsistency later causes the arm to jam during production. You're asked why the variance was overlooked.",
                () -> {
                    score--;
                    typewriterEffect("The average is close, but the inconsistency later causes the arm to jam during production. You're asked why the variance was overlooked.", this::presentScenario7);
                });

            addOption("Pause deployment and recommend recalibration.",
                "You recommend recalibration. The technician adjusts the servo parameters, and performance stabilizes.",
                () -> {
                    score++;
                    typewriterEffect("You recommend recalibration. The technician adjusts the servo parameters, and performance stabilizes.", this::presentScenario7);
                });

            addOption("Change the torque setting to 160 Nm manually to match the high end.",
                "You change the torque without documentation. It overcorrects, causing wear on critical joints.",
                () -> {
                    score -= 2;
                    typewriterEffect("You change the torque without documentation. It overcorrects, causing wear on critical joints.", this::presentScenario7);
                });

            addOption("Submit a report but do not halt testing.",
                "You file a report but testing continues. Management is appreciative of your notes but questions why operations weren’t paused.",
                () -> {
                    typewriterEffect("You file a report but testing continues. Management is appreciative of your notes but questions why operations weren’t paused.", this::presentScenario7);
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario7() {
        currentScenarioLabel = "Scenario 7:";
        optionPanel.removeAll();

        String text = "\nYou're testing a temperature-sensitive device and notice that one of the sensors is reading 3°C higher than expected. It still falls within the operating threshold, but the other sensors are consistent with each other.\n\nWhat do you do?";

        typewriterEffect(text, () -> {
            addOption("Flag the sensor for inspection before continuing tests.",
                "You flag the issue and swap out the sensor. The new one shows correct readings and improves the reliability of your test.",
                () -> {
                    score++;
                    typewriterEffect("You flag the issue and swap out the sensor. The new one shows correct readings and improves the reliability of your test.", this::presentScenario8);
                });

            addOption("Average all sensor data and continue testing.",
                "You average the data and continue. Later, discrepancies in thermal response cause performance delays.",
                () -> {
                    score--;
                    typewriterEffect("You average the data and continue. Later, discrepancies in thermal response cause performance delays.", this::presentScenario8);
                });

            addOption("Ignore the deviation—3°C is not a big deal.",
                "The heat affects critical components, and you’re called into a post-test failure analysis meeting.",
                () -> {
                    score -= 2;
                    typewriterEffect("The heat affects critical components, and you’re called into a post-test failure analysis meeting.", this::presentScenario8);
                });

            addOption("Disable the outlier sensor and continue with remaining ones.",
                "You remove the outlier, but during peer review, your decision is questioned. Lack of redundancy becomes a concern.",
                () -> {
                    score--;
                    typewriterEffect("You remove the outlier, but during peer review, your decision is questioned. Lack of redundancy becomes a concern.", this::presentScenario8);
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario8() {
        currentScenarioLabel = "Scenario 8:";
        optionPanel.removeAll();

        String text = "\nYou’re helping estimate cooling time for a metal shaft coming out of a forging press. The shaft is heated to 900°C and cools at a rate modeled by the equation:\n\nT(t) = 900 * e^(-0.05t) + 25\n\nWhere T(t) is the temperature in °C and t is time in minutes.\n\nWhen is the shaft safe to handle, assuming a safe temp is below 60°C?";

        typewriterEffect(text, () -> {
            addOption("About 50 minutes",
                "You guess too soon. The shaft is still dangerously hot and causes a minor safety incident.",
                () -> {
                    score--;
                    typewriterEffect("You guess too soon. The shaft is still dangerously hot and causes a minor safety incident.", this::presentScenario9);
                });

            addOption("About 70 minutes",
                "You estimate correctly. After around 70 minutes, the temperature approaches safe handling conditions.",
                () -> {
                    score++;
                    typewriterEffect("You estimate correctly. After around 70 minutes, the temperature approaches safe handling conditions.", this::presentScenario9);
                });

            addOption("About 120 minutes",
                "You overestimate, causing delays in production but keeping safety intact.",
                () -> {
                    typewriterEffect("You overestimate, causing delays in production but keeping safety intact.", this::presentScenario9);
                });

            addOption("About 10 minutes",
                "Terminated: Premature handling led to serious injury.",
                () -> {
                    score -= 100;
                    JOptionPane.showMessageDialog(this, "Terminated: Premature handling led to serious injury.");
                    endSimulation("Terminated due to unsafe temperature assessment.");
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario9() {
        currentScenarioLabel = "Scenario 9:";
        optionPanel.removeAll();

        String text = "\nA vendor who provides components for your subsystem invites you to a private dinner. They've been late on recent deliveries, but they hint that they can 'expedite things' if the relationship stays positive.\n\nHow do you respond?";

        typewriterEffect(text, () -> {
            addOption("Decline politely and document the interaction to your manager.",
                "You document the invite and your manager appreciates the transparency.",
                () -> {
                    score++;
                    typewriterEffect("You document the invite and your manager appreciates the transparency.", this::presentScenario10);
                });

            addOption("Accept the dinner, but don’t discuss anything work-related.",
                "You attend. HR later raises concerns about possible favoritism. Your judgment is questioned.",
                () -> {
                    score--;
                    typewriterEffect("You attend. HR later raises concerns about possible favoritism. Your judgment is questioned.", this::presentScenario10);
                });

            addOption("Accept and mention your preference for faster delivery.",
                "You imply a favor-for-favor deal. Procurement investigates your communication logs.",
                () -> {
                    score -= 2;
                    typewriterEffect("You imply a favor-for-favor deal. Procurement investigates your communication logs.", this::presentScenario10);
                });

            addOption("Ignore it and delete the invite email.",
                "You delete the invite and say nothing. When the vendor brags to others, you’re included in the report.",
                () -> {
                    score -= 2;
                    typewriterEffect("You delete the invite and say nothing. When the vendor brags to others, you’re included in the report.", this::presentScenario10);
                });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario10() {
        currentScenarioLabel = "Scenario 10:";
        optionPanel.removeAll();

        String text = "\nYou’re asked to install a new sensor array using a vendor’s manual. The instructions are vague, and a few key diagrams are missing. Management wants it completed by the end of the day.\n\nWhat’s your approach?";

        typewriterEffect(text, () -> {
            addOption("Call the vendor and ask for clarification or updated documentation.",
                "You get updated files from the vendor and complete the task correctly the next morning.",
                () -> {
                    score++;
                    typewriterEffect("You get updated files from the vendor and complete the task correctly the next morning.", this::completeSimulation);
                });

            addOption("Do your best with what you have and make assumptions.",
                "The sensor array malfunctions during testing. You’re questioned on why you didn’t escalate the unclear instructions.",
                () -> {
                    score -= 2;
                    typewriterEffect("The sensor array malfunctions during testing. You’re questioned on why you didn’t escalate the unclear instructions.", this::completeSimulation);
                });

            addOption("Submit a delay notice explaining the manual is unusable.",
                "The delay is noted. Management isn’t thrilled, but they understand after reviewing the manual.",
                () -> {
                    typewriterEffect("The delay is noted. Management isn’t thrilled, but they understand after reviewing the manual.", this::completeSimulation);
                });

            addOption("Ask another technician who’s ‘done it before’ to do it for you.",
                "The technician installs it differently than spec. QA later flags the installation as non-compliant.",
                () -> {
                    score--;
                    typewriterEffect("The technician installs it differently than spec. QA later flags the installation as non-compliant.", this::completeSimulation);
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
        });

        optionButtons.add(button);
    }
    
    private void resetToGridLayout() {
        optionPanel.removeAll();
        optionPanel.setLayout(new GridLayout(2, 2, 10, 10));
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
        return "Engineer";
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
