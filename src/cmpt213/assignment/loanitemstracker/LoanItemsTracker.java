package cmpt213.assignment.loanitemstracker;

import cmpt213.assignment.loanitemstracker.model.loanItemManager;
import cmpt213.assignment.loanitemstracker.textui.TextUI;

/**
 * Entry point for the Loan Items Tracker application.
 * Creates the application components, loads saved data,
 * and starts the text-based user interface.
 */
public class LoanItemsTracker {

    /**
     * Starts the Loan Items Tracker application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        loanItemManager manager = new loanItemManager();
        manager.loadFromFile();
        TextUI ui = new TextUI(manager);
        ui.start();
    }
}
