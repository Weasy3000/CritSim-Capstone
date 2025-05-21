package Positions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Operator extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<DecisionLog> decisionHistory = new ArrayList<>();
    private String currentScenarioLabel = "";
    private JTextArea storyArea;
    private JPanel optionPanel;
    private List<JButton> optionButtons = new ArrayList<>();
    private int score = 0;
    private final String playerName = UserLogin.getUsername();

    public Operator() {
        setTitle("Operator Simulation");
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

        presentScenario1();
        setVisible(true);
    }

    private void presentScenario1() {
    	    currentScenarioLabel = "Scenario 1:";
    	    optionPanel.removeAll();

    	    String text = "You're scheduled to operate a CNC machine for a new part batch. As you arrive, you notice the tool hasn't been calibrated yet and the print is slightly smudged.\n\nWhat’s your first move?";

    	    typewriterEffect(text, () -> {
    	        addOption("Request a clean print and verify calibration records before running.",
    	            "You check the records and reprint the job file. Setup takes a bit longer, but the run goes smoothly.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You check the records and reprint the job file. Setup takes a bit longer, but the run goes smoothly.", this::presentScenario2);
    	            });

    	        addOption("Run the program at low speed and monitor it closely.",
    	            "The part looks okay at first but fails QA inspection due to early tool misalignment.",
    	            () -> {
    	                score--;
    	                typewriterEffect("The part looks okay at first but fails QA inspection due to early tool misalignment.", this::presentScenario2);
    	            });

    	        addOption("Use last week's settings and proceed.",
    	            "The machine scrapes the blank due to updated tolerances. A minor delay turns into rework.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("The machine scrapes the blank due to updated tolerances. A minor delay turns into rework.", this::presentScenario2);
    	            });

    	        addOption("Ask a nearby operator to do it instead.",
    	            "You pass the job off, and management asks why you didn’t follow standard procedures.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You pass the job off, and management asks why you didn’t follow standard procedures.", this::presentScenario2);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario2() {
    	    currentScenarioLabel = "Scenario 2:";
    	    optionPanel.removeAll();

    	    String text = "Midway through your shift, you hear an unfamiliar ticking sound from the spindle during each pass.\n\nWhat do you do?";

    	    typewriterEffect(text, () -> {
    	        addOption("Pause the machine and inspect for loose tool mounts.",
    	            "You find the tool holder was slightly loose and prevent further damage. Maintenance thanks you.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You find the tool holder was slightly loose and prevent further damage. Maintenance thanks you.", this::presentScenario3);
    	            });

    	        addOption("Turn the radio down and hope the sound fades.",
    	            "The noise grows louder. A minor misalignment becomes a spindle shaft failure.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("The noise grows louder. A minor misalignment becomes a spindle shaft failure.", this::presentScenario3);
    	            });

    	        addOption("Ignore it—it’s probably just material resonance.",
    	            "A few minutes later, the cutter breaks. The next operator reports it, but your name’s on the log.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("A few minutes later, the cutter breaks. The next operator reports it, but your name’s on the log.", this::presentScenario3);
    	            });

    	        addOption("Lower feed rate to reduce stress.",
    	            "You buy a little time, but damage continues unnoticed until final parts fail QA.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You buy a little time, but damage continues unnoticed until final parts fail QA.", this::presentScenario3);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario3() {
    	    currentScenarioLabel = "Scenario 3:";
    	    optionPanel.removeAll();

    	    String text = "Your control screen shows a green light, but you hear an unusual humming sound from the hydraulic bay. Everything appears normal, and no alarms are triggered.\n\nWhat do you do?";

    	    typewriterEffect(text, () -> {
    	        addOption("Trust your instincts and request a floor inspection.",
    	            "You follow your instincts and a loose valve is discovered—averting a fluid spill.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You follow your instincts and a loose valve is discovered—averting a fluid spill.", this::presentScenario4);
    	            });

    	        addOption("Ignore the sound—no alarm means no problem.",
    	            "You assume it's fine. Later, the hydraulic unit overheats, causing downtime.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("You assume it's fine. Later, the hydraulic unit overheats, causing downtime.", this::presentScenario4);
    	            });

    	        addOption("Log it as a note for maintenance and continue your task.",
    	            "You document the sound. Maintenance finds the issue next shift, but your delayed report slows response time.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You document the sound. Maintenance finds the issue next shift, but your delayed report slows response time.", this::presentScenario4);
    	            });

    	        addOption("Turn the system off as a precaution.",
    	            "You shut the system down without authorization. Management questions your escalation method.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You shut the system down without authorization. Management questions your escalation method.", this::presentScenario4);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario4() {
    	    currentScenarioLabel = "Scenario 4:";
    	    optionPanel.removeAll();

    	    String text = "During your shift, you're asked to train a new operator while monitoring three systems yourself. This is not part of your usual duties.\n\nWhat’s the best course of action?";

    	    typewriterEffect(text, () -> {
    	        addOption("Ask for help or redistribution of tasks to stay within safe limits.",
    	            "You request help. The supervisor reassigns one station, keeping operations safe.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You request help. The supervisor reassigns one station, keeping operations safe.", this::presentScenario5);
    	            });

    	        addOption("Train the new hire and try to manage all stations at once.",
    	            "You multitask, but miss an alert during training. The mistake delays a process cycle.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You multitask, but miss an alert during training. The mistake delays a process cycle.", this::presentScenario5);
    	            });

    	        addOption("Ignore the training request—it’s not your job.",
    	            "You refuse without discussion. Leadership notes it as poor team engagement.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("You refuse without discussion. Leadership notes it as poor team engagement.", this::presentScenario5);
    	            });

    	        addOption("Let the new hire shadow silently without explanation.",
    	            "They shadow you, but without guidance, they absorb incorrect behaviors.",
    	            () -> {
    	                score--;
    	                typewriterEffect("They shadow you, but without guidance, they absorb incorrect behaviors.", this::presentScenario5);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario5() {
    	    currentScenarioLabel = "Scenario 5:";
    	    optionPanel.removeAll();

    	    String text = "You receive a printed checklist that’s missing verification signatures from the last shift. This checklist is required before the next process begins.\n\nHow do you proceed?";

    	    typewriterEffect(text, () -> {
    	        addOption("Call the previous shift operator to verify verbally, then document it yourself.",
    	            "You confirm and document the steps. It’s not best practice, but keeps flow going responsibly.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You confirm and document the steps. It’s not best practice, but keeps flow going responsibly.", this::presentScenario6);
    	            });

    	        addOption("Start the next process without checking—it’s probably fine.",
    	            "You skip verification. Later, a failed step causes contamination. QA initiates a formal investigation.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("You skip verification. Later, a failed step causes contamination. QA initiates a formal investigation.", this::presentScenario6);
    	            });

    	        addOption("Stop the process and escalate to the supervisor.",
    	            "You halt production and notify the supervisor. They appreciate your attention to compliance.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You halt production and notify the supervisor. They appreciate your attention to compliance.", this::presentScenario6);
    	            });

    	        addOption("Sign it yourself to avoid delays.",
    	            "Falsifying signatures is discovered in an audit. You're held responsible for the breach.",
    	            () -> {
    	                score -= 100;
    	                JOptionPane.showMessageDialog(this, "Terminated: Forging signatures resulted in immediate disciplinary action.");
    	                endSimulation("Terminated due to falsifying documentation.");
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario6() {
    	    currentScenarioLabel = "Scenario 6:";
    	    optionPanel.removeAll();

    	    String text = "Mid-shift, your screen freezes during a software update pushed remotely by IT. You can’t monitor temperatures on your system panel.\n\nWhat's your immediate action?";

    	    typewriterEffect(text, () -> {
    	        addOption("Switch to manual override and monitor readings locally.",
    	            "You use analog gauges to monitor the system. Later, IT confirms the update stalled.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You use analog gauges to monitor the system. Later, IT confirms the update stalled.", this::presentScenario7);
    	            });

    	        addOption("Wait for the system to reboot while doing nothing else.",
    	            "You wait for 20 minutes while the system runs blind. A spike goes unnoticed.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("You wait for 20 minutes while the system runs blind. A spike goes unnoticed.", this::presentScenario7);
    	            });

    	        addOption("Call IT and report the issue immediately.",
    	            "You call IT. They patch the issue in 5 minutes. You logged it and kept things transparent.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You call IT. They patch the issue in 5 minutes. You logged it and kept things transparent.", this::presentScenario7);
    	            });

    	        addOption("Restart the workstation manually without IT approval.",
    	            "Restarting without approval causes data corruption. Your lead reprimands you.",
    	            () -> {
    	                score--;
    	                typewriterEffect("Restarting without approval causes data corruption. Your lead reprimands you.", this::presentScenario7);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario7() {
    	    currentScenarioLabel = "Scenario 7:";
    	    optionPanel.removeAll();

    	    String text = "You’re asked to take over a machine mid-cycle after a coworker leaves suddenly. They left no notes, and the screen shows a critical warning.\n\nWhat’s your move?";

    	    typewriterEffect(text, () -> {
    	        addOption("Pause the cycle and review the machine manual before continuing.",
    	            "You take a pause to verify the warning. It turns out a minor parameter was missing, which you fix safely.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You take a pause to verify the warning. It turns out a minor parameter was missing, which you fix safely.", this::presentScenario8);
    	            });

    	        addOption("Continue operating and hope it was a false alarm.",
    	            "The warning was real. A jam occurs and delays the next batch. You’re held partly responsible.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("The warning was real. A jam occurs and delays the next batch. You’re held partly responsible.", this::presentScenario8);
    	            });

    	        addOption("Call a supervisor to reassign the task.",
    	            "You flag the risk. The supervisor commends your caution and finds someone certified.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You flag the risk. The supervisor commends your caution and finds someone certified.", this::presentScenario8);
    	            });

    	        addOption("Ignore the message and reset the warning.",
    	            "The warning resets, but the system fails after restart. You bypassed safety protocols.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("The warning resets, but the system fails after restart. You bypassed safety protocols.", this::presentScenario8);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario8() {
    	    currentScenarioLabel = "Scenario 8:";
    	    optionPanel.removeAll();

    	    String text = "The conveyor belt sensor begins flickering between ON and OFF states, but production isn’t stopping. You suspect it’s a false signal, but the backup sensor was removed for repair.\n\nWhat do you do?";

    	    typewriterEffect(text, () -> {
    	        addOption("Pause the system and report the issue before continuing.",
    	            "You halt operations and notify maintenance. They confirm a short in the sensor wiring, preventing possible damage.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You halt operations and notify maintenance. They confirm a short in the sensor wiring, preventing possible damage.", this::presentScenario9);
    	            });

    	        addOption("Continue operations until the issue becomes more consistent.",
    	            "You keep running. Moments later, the belt jams and damages a drive roller, causing a costly delay.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("You keep running. Moments later, the belt jams and damages a drive roller, causing a costly delay.", this::presentScenario9);
    	            });

    	        addOption("Tape the sensor wire in place to stabilize it temporarily.",
    	            "You stabilize the wire manually. The system runs, but safety compliance is violated.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You stabilize the wire manually. The system runs, but safety compliance is violated.", this::presentScenario9);
    	            });

    	        addOption("Disable the sensor completely and watch the system yourself.",
    	            "You bypass the sensor. A QA inspector catches the violation and shuts down your line.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("You bypass the sensor. A QA inspector catches the violation and shuts down your line.", this::presentScenario9);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario9() {
    	    currentScenarioLabel = "Scenario 9:";
    	    optionPanel.removeAll();

    	    String text = "You’re nearing the end of your shift and your replacement hasn’t arrived. The line is still running, but your overtime has not been approved.\n\nWhat do you do?";

    	    typewriterEffect(text, () -> {
    	        addOption("Stay at your post and notify your supervisor immediately.",
    	            "You remain on duty and inform your supervisor. They thank you for keeping the operation safe and note the issue for payroll.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You remain on duty and inform your supervisor. They thank you for keeping the operation safe and note the issue for payroll.", this::presentScenario10);
    	            });

    	        addOption("Clock out and leave since your time is up.",
    	            "You leave the machine unattended. A fault occurs, and you're cited for abandonment.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("You leave the machine unattended. A fault occurs, and you're cited for abandonment.", this::presentScenario10);
    	            });

    	        addOption("Ask a coworker to monitor your station while you leave.",
    	            "Your coworker isn’t trained for the station. An error occurs and both of you are brought in for review.",
    	            () -> {
    	                score--;
    	                typewriterEffect("Your coworker isn’t trained for the station. An error occurs and both of you are brought in for review.", this::presentScenario10);
    	            });

    	        addOption("Shut down the machine and leave a note.",
    	            "You stop the machine without proper handoff. Restart procedures delay the next shift by 30 minutes.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You stop the machine without proper handoff. Restart procedures delay the next shift by 30 minutes.", this::presentScenario10);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}

    	private void presentScenario10() {
    	    currentScenarioLabel = "Scenario 10:";
    	    optionPanel.removeAll();

    	    String text = "You’re asked to help a newly hired operator configure their station for an upcoming changeover. They’re eager but keep skipping steps to save time.\n\nHow do you handle this?";

    	    typewriterEffect(text, () -> {
    	        addOption("Walk them through each step and explain why each one matters.",
    	            "You coach them through it patiently. They gain confidence and begin following protocol more carefully.",
    	            () -> {
    	                score++;
    	                typewriterEffect("You coach them through it patiently. They gain confidence and begin following protocol more carefully.", this::completeSimulation);
    	            });

    	        addOption("Let them do it their way—everyone has their own style.",
    	            "They skip a calibration check. The setup fails during production and your involvement is questioned.",
    	            () -> {
    	                score -= 2;
    	                typewriterEffect("They skip a calibration check. The setup fails during production and your involvement is questioned.", this::completeSimulation);
    	            });

    	        addOption("Do the changeover yourself to save time.",
    	            "You complete it for them. They don’t learn the process and remain dependent on others.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You complete it for them. They don’t learn the process and remain dependent on others.", this::completeSimulation);
    	            });

    	        addOption("Report them to the supervisor for negligence.",
    	            "You report them, but the supervisor feels it could’ve been a coachable moment instead.",
    	            () -> {
    	                score--;
    	                typewriterEffect("You report them, but the supervisor feels it could’ve been a coachable moment instead.", this::completeSimulation);
    	            });

    	        shuffleAndDisplayOptions();
    	    });
    	}


    private void shuffleAndDisplayOptions() {
        Collections.shuffle(optionButtons);
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
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(26, 26, 26));
            }
        });


        button.addActionListener((ActionEvent e) -> {
            for (Component c : optionPanel.getComponents()) {
                c.setEnabled(false);
            }
            decisionHistory.add(new DecisionLog(currentScenarioLabel, label, outcomeDescription));
            nextScenario.run();
        });

        optionButtons.add(button);
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
    
    private void endSimulation(String message) {
        JOptionPane.showMessageDialog(this, message + "\nScore: " + score + "\nPerformance: " + getRating());
        saveScoreToLeaderboard();
        returnToMainMenu();
    }


    private String getRating() {
        if (score >= 4) return "Excellent";
        if (score >= 2) return "Good";
        if (score >= 1) return "Fair";
        return "Poor";
    }

    private String getRoleTitle() {
        return "Operator";
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
