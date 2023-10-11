package Containers;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;

public class AmharicDatabaseApp extends JFrame {

    private static final String DB_URL ="jdbc:sqlite:assets/database/GMS.sqlite";
    private static java.sql.Connection connection = null;
    static {
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static java.sql.Connection getConnection() {
        return connection;
    }

    public AmharicDatabaseApp() {
            // Create an instance of the application
           // AmharicDatabaseApp app = new AmharicDatabaseApp();
            // Set up JFrame properties and display it
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setSize(400, 200);


        try {
            // Load the Amharic font
            Font amharicFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\Nahom\\Desktop\\projects\\GMS\\src\\fonts\\Tera-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(amharicFont);

            // Set up UI components
            JLabel label = new JLabel("አማርኛ");
            label.setFont(amharicFont);

            JTextField textField = new JTextField();
            textField.setSize(20,5);
            textField.setFont(amharicFont);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> {
                String amhText = textField.getText();

                // Insert the Amharic text into the database
                try (PreparedStatement pst = getConnection().prepareStatement("INSERT INTO Customers(Name, Address, PhoneNumber) VALUES (?,?,?)")){
                    pst.setString(1, amhText);
                    pst.setString(2,"09502462");
                    pst.setString(3, "Adiss");
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to save Amh Text!");
                }
            });

            // Set up layout
            setLayout(new GridLayout());
            add(label);
            add(textField);
            add(saveButton);

        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
    }
}
