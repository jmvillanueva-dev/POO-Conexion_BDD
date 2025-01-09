import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JTextField userEmail;
    private JPasswordField userPass;
    private JButton loginButton;
    public JPanel login;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/universidad";
                String username = "root";
                String password = "1234";

                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    System.out.println("Conexión con la DB exitosa!");

                    String parametroEmail = userEmail.getText();
                    String parametroPass = new String(userPass.getPassword());

                    String query = "SELECT contrasena FROM usuarios WHERE email = ?";
                    try (PreparedStatement stmt = con.prepareStatement(query)) {
                        stmt.setString(1, parametroEmail);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                String passwordDB = rs.getString("contrasena");

                                if (passwordDB.equals(parametroPass)) {
                                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");

                                    String idQuery = "SELECT id_usuario FROM usuarios WHERE email = ?";
                                    try (PreparedStatement idStmt = con.prepareStatement(idQuery)) {
                                        idStmt.setString(1, parametroEmail);
                                        try (ResultSet idRs = idStmt.executeQuery()) {
                                            if (idRs.next()) {
                                                int userId = idRs.getInt("id_usuario");

                                                // Mostrar el panel DataUser
                                                JFrame frame = new JFrame("Datos del Usuario");
                                                frame.setContentPane(new DataUser(userId).getPanel());
                                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                frame.setSize(800, 600);
                                                frame.setLocationRelativeTo(null);
                                                frame.setVisible(true);
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Lo sentimos. Parece que tu email no está registrado.");
                            }
                        }
                    }
                } catch (SQLException error) {
                    JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos");
                }
            }
        });
    }

}
