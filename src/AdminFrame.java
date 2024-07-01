import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Author:
 * Go, Mardelito Tutor
 * Joshua Famor
 * BSCS - A121
 * BrewAcademy
 */

public class AdminFrame extends JFrame {

    private Database database;
    private DefaultTableModel tableModel;

    public AdminFrame() {
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(800, 800);
        setLocationRelativeTo(null);

        //Window icon
        ImageIcon icon = new ImageIcon("bg_logo.png");
        Image image = icon.getImage();
        setIconImage(image);

        //Initialize
        database = new Database("results.txt");

        //Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Table
        tableModel = new DefaultTableModel(new String[]{"Name", "Score", "Date"}, 0);
        JTable table = new JTable(tableModel);

        loadResultsIntoTable();

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        //Delete btn
        JButton deleteButton = new JButton("DELETE SELECTED");
        deleteButton.setFont(new Font("Roboto", Font.BOLD, 20));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setPreferredSize(new Dimension(150, 70));
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                saveTableData();
            }
        });
        add(deleteButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    //Load quiz results from database into the table
    private void loadResultsIntoTable() {
        ArrayList<String> results = database.loadFromFile();
        for (String result : results) {
            String[] data = result.split("#");
            if (data.length == 3) {
                tableModel.addRow(data);
            }
        }
    }

    //Save current table data to the database
    private void saveTableData() {
        List<String> records = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            StringBuilder record = new StringBuilder();
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                record.append(tableModel.getValueAt(i, j));
                if (j < tableModel.getColumnCount() - 1) {
                    record.append("#");
                }
            }
            records.add(record.toString());
        }
        database.saveAllRecords(records);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminFrame::new);
    }
}
