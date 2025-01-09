import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DataUser {
    private JPanel dataUser;
    private JLabel userName;
    private JTable dataTable;

    DefaultTableModel model = new DefaultTableModel(
            new String[]{"Dirección", "Teléfono", "Celular", "Facultad", "Carrera", "Semestre"}, 0);

    public DataUser(int userId) {
        dataTable.setModel(model);

        String url = "jdbc:mysql://localhost:3306/universidad";
        String username = "root";
        String password = "1234";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            // Obtener el nombre completo del usuario
            String nameQuery = "SELECT nombre, apellido_paterno, apellido_materno FROM nombres_usuario WHERE id_usuario = ?";
            try (PreparedStatement stmt = con.prepareStatement(nameQuery)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String apellidoPaterno = rs.getString("apellido_paterno");
                        String apellidoMaterno = rs.getString("apellido_materno");
                        userName.setText(nombre + " " + apellidoPaterno + (apellidoMaterno != null ? " " + apellidoMaterno : ""));
                    }
                }
            }

            // Obtener los datos del usuario
            String dataQuery = "SELECT direccion, telefono, celular, facultad, carrera, semestre FROM datos_usuario WHERE id_usuario = ?";
            try (PreparedStatement stmt = con.prepareStatement(dataQuery)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getString("direccion"),
                                rs.getString("telefono"),
                                rs.getString("celular"),
                                rs.getString("facultad"),
                                rs.getString("carrera"),
                                rs.getString("semestre")
                        });
                    }

                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos del usuario: " + e.getMessage());
        }
    }

    public JPanel getPanel() {
        return dataUser;
    }
}