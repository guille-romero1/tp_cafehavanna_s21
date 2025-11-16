package havanna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocionDAO {

    public List<Beneficio> listarPromociones() {
        List<Beneficio> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, descripcion, puntos_requeridos, activa FROM promocion";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Beneficio b = new Beneficio(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("puntos_requeridos"),
                    rs.getBoolean("activa")
                );
                lista.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean canjear(int idCliente, int idPromocion) {
        boolean exito = false;
        String sqlP = "SELECT puntos_requeridos FROM promocion WHERE id = ?";
        String sqlC = "SELECT puntos_saldo FROM cliente WHERE id = ?";
        String sqlUpdateCliente = "UPDATE cliente SET puntos_saldo = puntos_saldo - ? WHERE id = ?";
        String sqlInsertCanje = "INSERT INTO canje (cliente_id, promocion_id, puntos_usados) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar()) {
            con.setAutoCommit(false);

            int puntosPromo = 0;
            int saldoCliente = 0;

            try (PreparedStatement ps1 = con.prepareStatement(sqlP)) {
                ps1.setInt(1, idPromocion);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) puntosPromo = rs1.getInt("puntos_requeridos");
            }

            try (PreparedStatement ps2 = con.prepareStatement(sqlC)) {
                ps2.setInt(1, idCliente);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) saldoCliente = rs2.getInt("puntos_saldo");
            }

            if (saldoCliente >= puntosPromo) {
                try (PreparedStatement ps3 = con.prepareStatement(sqlUpdateCliente)) {
                    ps3.setInt(1, puntosPromo);
                    ps3.setInt(2, idCliente);
                    ps3.executeUpdate();
                }

                try (PreparedStatement ps4 = con.prepareStatement(sqlInsertCanje)) {
                    ps4.setInt(1, idCliente);
                    ps4.setInt(2, idPromocion);
                    ps4.setInt(3, puntosPromo);
                    ps4.executeUpdate();
                }

                con.commit();
                exito = true;
            } else {
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exito;
    }
}


