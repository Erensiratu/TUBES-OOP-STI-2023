 
import javax.swing.*;
import java.awt.event.*;

public class SimPlicityGUI {
    private JFrame frame;
    private JPanel panel;
    private SimPlicity simPlicity;
    private JTextArea outputTextArea;
    private JTextField inputTextField;

    public static void main(String[] args) {
        SimPlicityGUI gui = new SimPlicityGUI();
        gui.start();
    }

    public void start() {
        simPlicity = SimPlicity.getInstance();

        frame = new JFrame("SimPlicity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("SimPlicity");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(title);

        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane);

        JLabel inputLabel = new JLabel("Enter command:");
        inputLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(inputLabel);

        inputTextField = new JTextField(20);
        inputTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText().trim();
                inputTextField.setText("");
                executeCommand(input);
            }
        });
        panel.add(inputTextField);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void executeCommand(String command) {
        String output = "";
        switch (command) {
            case "add sim":
                simPlicity.addSim();
                output = "New sim added.";
                break;
            case "change sim":
                simPlicity.changeSim();
                output = "Current sim changed.";
                break;
            case "view sim info":
                simPlicity.viewSimInfo();
                break;
            case "action":
                simPlicity.action();
                break;
            case "view current location":
                simPlicity.viewCurrentLocation();
                break;
            case "move room":
                simPlicity.moveRoom();
                output = "Sim moved to new room.";
                break;
            case "help":
                output = "Available commands:\n" +
                         "add sim\n" +
                         "change sim\n" +
                         "view sim info\n" +
                         "action\n" +
                         "view current location\n" +
                         "move room\n" +
                         "help\n" +
                         "exit";
                break;
            case "exit":
                System.exit(0);
            default:
                output = "Invalid command. Type 'help' to see available commands.";
                break;
        }
        outputTextArea.append("> " + command + "\n" + output + "\n\n");
    }
}
