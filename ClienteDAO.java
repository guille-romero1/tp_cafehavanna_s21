package havanna;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public int registrarCliente(String nombre, String email, String dni, String telefono, LocalDate fechaNac) {
        int id = -1;
        String sql = "INSERT INTO cliente (nombre, email, dni, telefono, fecha_nacimiento, puntos_saldo, activo) VALUES (?, ?, ?, ?, ?, 0, 1)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, dni);
            ps.setString(4, telefono);
            ps.setDate(5, Date.valueOf(fechaNac));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, email, puntos_saldo FROM cliente";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("puntos_saldo")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int consultarPuntos(int idCliente) {
        int puntos = 0;
        String sql = "SELECT puntos_saldo FROM cliente WHERE id = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) puntos = rs.getInt("puntos_saldo");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puntos;
    }
}



