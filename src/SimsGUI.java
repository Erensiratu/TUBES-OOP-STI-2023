import javax.swing.*;
import java.awt.*;

public class SimsGUI extends JFrame {
    private JTextField nameField;
    private JList<String> inventoryList;

    public SimsGUI() {
        // Create JPanel for main content
        JPanel contentPane = new JPanel(new BorderLayout());

        // Create JPanel for input form
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addItemToList());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(addButton);

        // Create JPanel for inventory list
        JPanel inventoryPanel = new JPanel(new BorderLayout());
        JLabel inventoryLabel = new JLabel("Inventory:");
        inventoryPanel.add(inventoryLabel, BorderLayout.NORTH);
        inventoryList = new JList<>();
        JScrollPane inventoryScrollPane = new JScrollPane(inventoryList);
        inventoryPanel.add(inventoryScrollPane, BorderLayout.CENTER);

        // Add input form and inventory list to main content panel
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(inventoryPanel, BorderLayout.CENTER);

        // Set JFrame properties
        setTitle("Sims Game");
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addItemToList() {
        String itemName = nameField.getText();
        if (!itemName.isEmpty()) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) inventoryList.getModel();
            listModel.addElement(itemName);
            nameField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimsGUI::new);
    }
}
