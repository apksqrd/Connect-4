package main.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    /*
     * Set my code to use openai's panel naming conventions:
     * 
     * "mainPanel" or "primaryPanel": These names are often used for the central or
     * primary panel in an application, which serves as a hub or control center for
     * other elements of the application.
     * 
     * "settingsPanel" or "preferencesPanel": These names are often used for panels
     * that contain settings or preferences for an application.
     * 
     * "activityPanel" or "functionalityPanel": These names are often used for
     * panels that contain the main functionality or interactive elements of an
     * application.
     * 
     * "controlPanel" or "commandPanel": These names are often used for panels that
     * contain controls or commands that allow users to interact with an
     * application.
     * 
     * "statusPanel" or "statusBar": These names are often used for panels that
     * display status information or messages to users.
     */

    private static MainFrame mainFrame = null;
    private final JPanel mainPanelHolder; // just complelety breaks without this

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public MainPanel getMainPanel() {
        return (MainPanel) mainPanelHolder.getComponent(0); // ! weird that this isn't showing a warning...
    }

    public MainFrame() {
        super("Brick");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanelHolder = new JPanel(new BorderLayout()); // so that mainPanel fills up the entire screen

        mainPanelHolder.add(new HomePanel());

        add(mainPanelHolder);

        // pack();
        setSize(400, 400);
        setVisible(true);

        if (mainFrame == null) {
            mainFrame = this;
        }
    }

    public void setMainPanel(JPanel newPanel) {
        for (Component c : mainPanelHolder.getComponents()) {
            c.setVisible(false);
        }
        mainPanelHolder.removeAll();
        mainPanelHolder.add(newPanel);

        repaint();
    }
}