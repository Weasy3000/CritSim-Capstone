package Positions;

class DecisionLog {
    String scenarioTitle;
    String userChoice;
    String outcome;

    public DecisionLog(String scenarioTitle, String userChoice, String outcome) {
        this.scenarioTitle = scenarioTitle;
        this.userChoice = userChoice;
        this.outcome = outcome;
    }

    public String toString() {
        return scenarioTitle + ":\n- You chose: " + userChoice + "\n- Outcome: " + outcome + "\n";
    }
}
