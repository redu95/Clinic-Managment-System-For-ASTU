/*
ASTU Clinic
Section: 02
GRoup: 04
GROUP MEMBERS		               ID.NO
 Rediet Muluken ................ugr/25619/14
Tsehay  Goremes ................ugr/26490/14
Bethel B/meskel ................ugr/25724/14
Faiza Mohammed .................ugr/25768/14

*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AstuClinicPage {
    private JFrame frame;
    private ImageIcon imageIcon;
    private JButton searchButton,addNewButton, bimage;
    public JLabel mainLabel, lRecpt, lname, lid;
    public JTextField  tname, tid;
    public AstuClinicPage() {
        // frame
        frame = new JFrame("ASTU Clinic Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null); // Set null layout

        //Creat Labels
        imageIcon =new ImageIcon("src\\icon.png");
        bimage = new JButton();
        bimage.setIcon(imageIcon);
        mainLabel = new JLabel("WELCOME TO ASTU CLINIC");
        lRecpt = new JLabel("RECEPTION");
        lname = new JLabel(" Patient Name");
        lid = new JLabel("Patient ID");
        bimage.setBounds(30,40,250,250);
        mainLabel.setBounds(50,270,250,110);
        lRecpt.setBounds(100,300,250,110);
        lname.setBounds(350,150,90,20);
        lid.setBounds(350,200,90,20);


        //Create Textfeids
        tname = new JTextField();
        tid = new JTextField();
        tname.setBounds(350,180,90,20);
        tid.setBounds(350,230,90,20);

        // Create buttons
        searchButton = new JButton("Search");
        addNewButton = new JButton("Add New");

        // Set button positions and sizes using absolute coordinates
        searchButton.setBounds(290, 330, 90, 20);
        addNewButton.setBounds(390, 330, 90, 20);
       // Search Button Actions
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tid3 = tid.getText();
                if(!tid3.isEmpty()) {
                    Frame3 obj3 = new Frame3();
                    obj3.setVisible(true);
                    obj3.searchById(tid3);
                }
                else {
                    JOptionPane.showMessageDialog(frame, "ID Field cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //Add Button Actions
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame2 obj2 = new Frame2();
                obj2.setVisible(true);
            }
        });
        // Add buttons to the frame
        frame.add(searchButton);
        frame.add(addNewButton);
        frame.add(mainLabel);
        frame.add(lname);
        frame.add(lid);
        frame.add(tname); frame.add(tid); frame.add(lRecpt);
        Color customColor = new Color(210, 224, 251);
        frame.getContentPane().setBackground(customColor);
        frame.add(bimage);
        frame.setVisible(true);

    }
    //...........................frame 2..............................

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

    //...........................frame 3..............................


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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AstuClinicPage();
            }
        });
    }

}