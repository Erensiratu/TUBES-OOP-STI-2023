import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimPlicityGUI {
    private SimPlicity simPlicity;

    public SimPlicityGUI() {
        simPlicity = SimPlicity.getInstance();

        // create the main window
        JFrame frame = new JFrame("SimPlicity");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the panel that will hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // create the buttons
        JButton startButton = new JButton("Start Game");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");
        JButton addSimButton = new JButton("Add Sim");

        // add the buttons to the panel
        panel.add(startButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(helpButton);
        panel.add(exitButton);
        panel.add(addSimButton);

        // add the panel to the main window
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // add action listeners to the buttons
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simPlicity.startGame();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simPlicity.save();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simPlicity.load();
            }
        });
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simPlicity.help();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simPlicity.exit();
            }
        });
        addSimButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simPlicity.addSim();
            }
        });

        // display the main window
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimPlicityGUI();
    }
}
