import javax.swing.* ;
import java.awt.*;
import java.awt.event.* ;

public class ToDoApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("To Do App");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel inputPanel = new JPanel();
        JTextField taskField = new JTextField(20);
        JButton addButton = new JButton("Submit");
        JButton deleteButton = new JButton("Delete Selected Task");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = taskField.getText().trim();

                if(!text.isEmpty()) {
                    listModel.addElement("😎 "+text) ;
                    taskField.setText("") ;
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Please enter a task name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }) ;

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex() ;

                if(selectedIndex != -1) {
                    listModel.remove(selectedIndex) ;
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Please select a task before deleting!") ;
                }
            }
        }) ;

        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true) ;
    }
}
