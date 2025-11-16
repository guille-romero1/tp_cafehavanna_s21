package havanna;

import java.sql.*;

public class CompraDAO {

    public int registrarCompra(int idCliente, double monto) {
        int puntos = (int) Math.floor(monto / 10);
        String sql = "INSERT INTO compra (cliente_id, monto) VALUES (?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setDouble(2, monto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return puntos;
    }
}

