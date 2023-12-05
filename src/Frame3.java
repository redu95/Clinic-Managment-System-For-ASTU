import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Frame3 extends JFrame {
    private JPanel pane3;
    private JTextArea textArea1;
    private JButton getCardButton;


    public Frame3(){
        setContentPane(pane3);
        Color customColor = new Color(210, 224, 251);
        textArea1.setBackground(customColor);
        setTitle("Profile Patient");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500,500);
        textArea1.setEditable(false);
        setVisible(true);
        getCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(pane3, "Card Succesfully Printed", "Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
//----------------------------------search_--------------------------------
public void searchById(String tid3) {
    String fileName = "clinic_data.txt";
    String searchId = tid3;
    String id = null;
    String name = null;
    String age = null;
    String year = null;
    String description = null;
    String date = null;
    System.out.println(tid3);

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("ID:" + searchId)) {
                id = line.substring(0);
                name = reader.readLine().substring(0);
                age = reader.readLine().substring(0);
                year = reader.readLine().substring(0);
                date = reader.readLine().substring(0);
                description = reader.readLine().substring(0);
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (id != null) {
        readFromFile(id, name, age, year, date, description);
    } else {
        displayNotFound();
        System.out.print(tid3);
    }
}



    //--------------------------------------------------------------------------
    private void readFromFile(String id, String name, String age, String year, String date, String description) {
        textArea1.setText(id + "\n" + name +  "\n" +  year +  "\n" + date +  "\n" + description+  "\n");
    }
    private void displayNotFound() {
        JOptionPane.showMessageDialog(null, "ID not found in the file.", "ID Not Found", JOptionPane.ERROR_MESSAGE);
    }

}
