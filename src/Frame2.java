import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame2 extends JFrame{
    private JPanel panel2 ;
    private JTextField nameField1;
    private  JTextField idField;
    private JTextField ageField;
    private JTextField yearField;
    private JLabel name2;
    private JLabel id2;
    private JTextField dateField;
    private JTextField discriptField;
    private JLabel age2;
    private JLabel year2;
    private JLabel date2;
    private JLabel discript2;
    private JButton saveButton;
    private ArrayList<String> dataEntries; // Collection to store data entries


    // nameField1 =new JTextField();

    private void addDataEntry() {
        String dataEntry = "Name:" + nameField1.getText() +
                "\nID:" + idField.getText() +
                "\nAge:" + ageField.getText() +
                "\nYear:" + yearField.getText() +
                "\nDate:" + dateField.getText() +
                "\nDescription:" + discriptField.getText() +
                "\n";
        dataEntries.add(dataEntry);
    }
   public Frame2(){
       setContentPane(panel2);
       Color customColor = new Color(210, 224, 251);
       panel2.setBackground(customColor);
       setTitle("New Patient");
       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       setSize(500,500);
       setVisible(true);
       dataEntries = new ArrayList<>(); // Initialize dataEntries collection

       saveButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e){
               writeToFile();
               JOptionPane.showMessageDialog(panel2, "Card Succesfully Saved", "Successful", JOptionPane.INFORMATION_MESSAGE);
           }
       });
   }

    private void writeToFile() {
        String fileName = "clinic_data.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName,true))) {
            writer.println("-----------------------------------------------------");
            writer.println("ID:" + idField.getText());
            writer.println("Name:" + nameField1.getText());
            writer.println("Age:" + ageField.getText());
            writer.println("Year:" + yearField.getText());
            writer.println("Date:" + dateField.getText());
            writer.println("Description:" + discriptField.getText());
            System.out.println("Data written to file:" + fileName);
            for (String dataEntry : dataEntries) {
                writer.println(dataEntry);

            }
            System.out.println("Data appended to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
