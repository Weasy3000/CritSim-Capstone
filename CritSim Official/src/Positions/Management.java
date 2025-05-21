package Positions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Management extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<DecisionLog> decisionHistory = new ArrayList<>();
    private String currentScenarioLabel = "";
    private JTextArea storyArea;
    private JPanel optionPanel;
    private List<JButton> optionButtons = new ArrayList<>();
    private int score = 0;
    private final String playerName = UserLogin.getUsername();

    public Management() {
        setTitle("Management Simulation");
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

        presentScenario1();

        setVisible(true);
    }

    private void presentScenario1() {
        currentScenarioLabel = "Scenario 1:";
        optionPanel.removeAll();

        String text = "You're called into a morning meeting where the Operations Lead suggests skipping the daily safety briefing to speed up production. Everyone in the room is silent, waiting for your response as the new manager.";

        typewriterEffect(text, () -> {
        	addOption("Insist on holding the safety briefing and cite its compliance importance.",
        		    "You emphasize the importance of routine safety. The team follows your lead, avoiding potential liability.",
        		    () -> { score++; presentScenario2(); });

        		addOption("Compromise by sending a summary email instead of a meeting.",
        		    "You try to save time with a summary email. Later that week, an incident occurs and the lack of briefing is questioned.",
        		    () -> { score--; presentScenario2(); });

        		addOption("Agree to skip it this once and keep the decision between you and the lead.",
        		    "You agree to skip it. An auditor arrives for a surprise check and discovers non-compliance.",
        		    () -> { score -= 2; presentScenario2(); });

        		addOption("Ask the team to vote anonymously on skipping it.",
        		    "You leave it to the team. Mixed results follow, and you appear indecisive in leadership.",
        		    () -> { score--; presentScenario2(); });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario2() {
        currentScenarioLabel = "Scenario 2:";
        optionPanel.removeAll();

        String text = "A senior technician has been arriving late multiple times this month. He's your top performer and very well-liked, but others are starting to take notice.";

        typewriterEffect(text, () -> {
        	addOption("Have a private conversation to understand his reasons and set expectations.",
        		    "You speak privately and learn he’s caring for a sick relative. You arrange a flexible schedule. Morale improves.",
        		    () -> { score++; presentScenario3(); });

        		addOption("Publicly call him out during a team meeting.",
        		    "You embarrass him in front of others. It sours your relationship and the team becomes tense.",
        		    () -> { score -= 2; presentScenario3(); });

        		addOption("Ignore the issue due to his good performance.",
        		    "You overlook it. Soon others start arriving late too, claiming favoritism is in play.",
        		    () -> { score--; presentScenario3(); });

        		addOption("Escalate to HR immediately without speaking to him first.",
        		    "You bypass a conversation and file a report. HR processes it, but you’re seen as lacking people skills.",
        		    () -> { score--; presentScenario3(); });

            shuffleAndDisplayOptions();
        });
    }


    private void presentScenario3() {
        currentScenarioLabel = "Scenario 3:";
        optionPanel.removeAll();

        String text = "Midday, a new intern mistakenly shares an internal-only performance chart with a client. The chart contains sensitive metrics, but no customer data.";

        typewriterEffect(text, () -> {
        	addOption("Call the client immediately and explain the error transparently.",
        		    "You address it proactively. The client appreciates your honesty and agrees to disregard the internal data.",
        		    () -> { score++; presentScenario4(); });

        		addOption("Blame the intern publicly to distance yourself.",
        		    "You deflect blame. The intern feels humiliated, and your team becomes wary of you.",
        		    () -> { score -= 2; presentScenario4(); });

        		addOption("Quietly remove the chart and pretend it didn’t happen.",
        		    "You delete the evidence and stay silent. Later, the client questions inconsistencies, damaging trust.",
        		    () -> { score--; presentScenario4(); });

        		addOption("Report the intern to HR and recommend termination.",
        		    "You escalate to HR without review. Other interns feel unsupported and request reassignments.",
        		    () -> { score--; presentScenario4(); });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario4() {
        currentScenarioLabel = "Scenario 4:";
        optionPanel.removeAll();

        String text = "Later, you notice a department under your supervision is consistently behind schedule. You’ve already asked them twice to submit weekly progress reports, but they keep missing deadlines.";

        typewriterEffect(text, () -> {
        	addOption("Schedule a formal meeting and request an action plan from their team lead.",
        		    "You arrange a structured meeting. The team lead clarifies resource issues and proposes a solution.",
        		    () -> { score++; presentScenario5(); });

        		addOption("Request that your own assistant monitor them daily.",
        		    "You assign oversight. The team feels micromanaged, and productivity drops due to morale.",
        		    () -> { score--; presentScenario5(); });

        		addOption("Send a strongly worded email demanding better performance.",
        		    "Your email causes confusion and fear. The team leader complains to HR about your tone.",
        		    () -> { score -= 2; presentScenario5(); });

        		addOption("Do nothing and wait to see if things improve.",
        		    "You wait. Performance continues slipping, and upper management begins asking questions.",
        		    () -> { score--; presentScenario5(); });
            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario5() {
        currentScenarioLabel = "Scenario 5:";
        optionPanel.removeAll();

        String text = "During a team sync, your logistics coordinator brings up that the supply vendor’s delays are now affecting production timelines. The purchasing department hasn’t flagged this as a risk.";

        typewriterEffect(text, () -> {
        	addOption("Call an immediate meeting between logistics and purchasing to align communication.",
        		    "You facilitate a quick sync. Both teams identify where the breakdown occurred and correct their reporting process.",
        		    () -> { score++; presentScenario6(); });

        		addOption("Replace the vendor without informing purchasing.",
        		    "You go around purchasing. This causes procurement to flag you for policy violation and budget overruns.",
        		    () -> { score -= 2; presentScenario6(); });

        		addOption("Instruct logistics to handle it on their own.",
        		    "You delegate the issue downward. Logistics delays grow, and upper management asks why no mitigation occurred.",
        		    () -> { score--; presentScenario6(); });

        		addOption("Send a warning email to purchasing about their lack of updates.",
        		    "You escalate harshly via email. The purchasing team becomes defensive and communication worsens.",
        		    () -> { score--; presentScenario6(); });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario6() {
        currentScenarioLabel = "Scenario 6:";
        optionPanel.removeAll();

        String text = "You’ve been asked to evaluate which of three employees should be promoted to a new team lead position. One is highly popular, one is highly efficient but blunt, and one is newer but shows initiative.\n\nHow do you make your decision?";

        typewriterEffect(text, () -> {
        	addOption("Conduct one-on-one interviews and compare performance reviews.",
        		    "You gather data and insights directly. Your decision is respected by the team as fair and balanced.",
        		    () -> { score++; presentScenario7(); });

        		addOption("Pick the most popular to keep morale high.",
        		    "You choose based on popularity. The efficient candidate raises a concern about favoritism.",
        		    () -> { score--; presentScenario7(); });

        		addOption("Choose the newer employee to give them a chance.",
        		    "You make a risky bet. The team questions your rationale, and the new lead struggles under pressure.",
        		    () -> { score -= 2; presentScenario7(); });

        		addOption("Let HR pick for you.",
        		    "You defer entirely to HR. Leadership questions your ownership of the team’s direction.",
        		    () -> { score--; presentScenario7(); });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario7() {
        currentScenarioLabel = "Scenario 7:";
        optionPanel.removeAll();

        String text = "A client calls late in the day with a critical complaint. They claim their project deliverables were not met and threaten to pull the contract. Your team insists they followed the agreed scope.\n\nHow do you respond?";

        typewriterEffect(text, () -> {
        	addOption("Ask the client for documented concerns and offer a meeting to resolve it.",
        		    "You de-escalate the issue and agree to meet the next morning. The client is still concerned, but appreciates the professional response.",
        		    () -> { score++; presentScenario8(); });

        		addOption("Blame the misunderstanding on your team and offer free service to appease the client.",
        		    "You placate the client but undermine your team. Morale drops and your department questions your loyalty.",
        		    () -> { score -= 2; presentScenario8(); });

        		addOption("Deny everything and insist they misread the contract.",
        		    "You push back hard. The client forwards documentation and escalates to your executive leadership.",
        		    () -> { score--; presentScenario8(); });

        		addOption("Ignore it until the morning—your day is already full.",
        		    "Terminated: Ignoring urgent client relations cost the company a major contract.",
        		    () -> {
        		        score -= 100;
        		        JOptionPane.showMessageDialog(this, "Terminated: Ignoring urgent client relations cost the company a major contract.");
        		        endSimulation("Terminated due to failure to respond to a client crisis.");
        		    });

            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario8() {
        currentScenarioLabel = "Scenario 8:";
        optionPanel.removeAll();

        String text = "Finance sends you a report showing that your department is running 12% over budget this quarter. You're given 24 hours to submit a corrective action plan or face resource cuts.\n\nWhat do you do first?";

        typewriterEffect(text, () -> {
        	addOption("Meet with your team to review spending and identify controllable overruns.",
        		    "You identify several items that can be reduced or delayed. Finance appreciates your transparency and collaboration.",
        		    () -> { score++; presentScenario9(); });

        		addOption("Cut all training and development funds immediately.",
        		    "You hit your budget target, but at the cost of long-term growth and morale.",
        		    () -> { score--; presentScenario9(); });

        		addOption("Submit a false projection to make the numbers look better.",
        		    "Terminated: Submitting false financials triggered an internal audit and led to your dismissal.",
        		    () -> {
        		        score -= 100;
        		        JOptionPane.showMessageDialog(this, "Terminated: Submitting false financials triggered an internal audit and led to your dismissal.");
        		        endSimulation("Terminated due to financial misreporting.");
        		    });

        		addOption("Blame another department for shared expenses.",
        		    "You push the blame externally. Interdepartmental trust suffers and finance opens an inquiry.",
        		    () -> { score -= 2; presentScenario9(); });


            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario9() {
        currentScenarioLabel = "Scenario 9:";
        optionPanel.removeAll();

        String text = "Midway through the week, one of your senior employees is caught shouting at a junior coworker during a team meeting. The incident is witnessed by others, but no one has filed a formal complaint.\n\nHow do you respond?";

        typewriterEffect(text, () -> {
        	addOption("Privately meet with both employees to understand the incident before acting.",
        		    "You defuse tension by gathering facts calmly. You recommend mediation and review conflict protocol with the team.",
        		    () -> { score++; presentScenario10(); });

        		addOption("Publicly reprimand the senior employee to send a message.",
        		    "You address the behavior in front of everyone, which embarrasses the senior employee and causes friction.",
        		    () -> { score--; presentScenario10(); });

        		addOption("Ignore it since no complaint was filed.",
        		    "You take no action. Word spreads that management tolerates toxic behavior, and team morale drops.",
        		    () -> { score -= 2; presentScenario10(); });

        		addOption("Immediately suspend the senior employee without asking questions.",
        		    "Terminated: Premature suspension without due process triggered HR intervention.",
        		    () -> {
        		        score -= 100;
        		        JOptionPane.showMessageDialog(this, "Terminated: Premature suspension without due process triggered HR intervention.");
        		        endSimulation("Terminated due to failure in personnel due process.");
        		    });


            shuffleAndDisplayOptions();
        });
    }

    private void presentScenario10() {
        currentScenarioLabel = "Scenario 10:";
        optionPanel.removeAll();

        String text = "End of the week. Your division has completed all major deliverables, but a major error was discovered in a client document sent out earlier.\n\nLeadership requests a brief but honest end-of-week report for the board. You only have time to submit a summary.\n\nHow do you report it?";

        typewriterEffect(text, () -> {
        	addOption("Submit a transparent report noting the error and what will be done to fix it.",
        		    "The board appreciates your honesty and initiative to address the issue.",
        		    () -> { score++; completeSimulation(); });

        		addOption("Leave the error out—it was minor and already resolved.",
        		    "You omit the incident. When the client mentions it, the board is caught off guard.",
        		    () -> { score--; completeSimulation(); });

        		addOption("Blame the client for misunderstanding the document.",
        		    "You shift blame. The board later learns the truth and questions your leadership integrity.",
        		    () -> { score -= 2; completeSimulation(); });

        		addOption("Delay the report entirely and ask for an extension Monday.",
        		    "Terminated: Failing to report on a critical issue showed a lack of urgency and accountability.",
        		    () -> {
        		        score -= 100;
        		        JOptionPane.showMessageDialog(this, "Terminated: Failing to report on a critical issue showed a lack of urgency and accountability.");
        		        endSimulation("Terminated for failure to report to executive leadership.");
        		    });


            shuffleAndDisplayOptions();
        });
    }
    
    private void typewriterEffect(String text, Runnable afterText) {
        storyArea.setText("");
        new Thread(() -> {
            for (char c : text.toCharArray()) {
                try { Thread.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                SwingUtilities.invokeLater(() -> storyArea.append(String.valueOf(c)));
            }
            try { Thread.sleep(800); } catch (InterruptedException e) { e.printStackTrace(); }
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
            typewriterEffect("\n" + outcomeDescription, nextScenario); 
        });

        optionButtons.add(button);
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

    private String getRating() {
        if (score >= 3) return "Excellent";
        if (score == 2) return "Good";
        if (score == 1) return "Fair";
        return "Poor";
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

    private String getRoleTitle() {
        return "Manager";
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
