import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JTextField userName;
    private JPasswordField userPass;
    private JButton iniciarButton;
    public JPanel login;

    public Login() {
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/estudiantes2024b"; // jdbc = java database conector
                String username = "root";
                String password = "1234";

                try {
                    String parametro = userPass.getText();
                    Connection con = DriverManager.getConnection(url,username,password);
                    System.out.println("Se ha conectado con la base de datos");
                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM estudiantes where cedula=" + parametro;

                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        System.out.println(rs.getString("cedula"));
                        System.out.println(rs.getString("nombre"));
                        System.out.println(rs.getString("b1"));
                        System.out.println(rs.getString("b2"));
                    }
                    con.close();
                } catch (SQLException error) {
                    throw new RuntimeException(error);
                }
            }
        });
    }
}
