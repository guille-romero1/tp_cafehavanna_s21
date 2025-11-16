package havanna;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class SistemaFidelizacion {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CompraDAO compraDAO = new CompraDAO();
    private PromocionDAO promoDAO = new PromocionDAO();

    public int registrarCliente(String nombre, String email, String dni, String tel, LocalDate fechaNac) {
        return clienteDAO.registrarCliente(nombre, email, dni, tel, fechaNac);
    }

    public int registrarCompra(int idCliente, double monto) {
        return compraDAO.registrarCompra(idCliente, monto);
    }

    public int consultarPuntos(int idCliente) {
        return clienteDAO.consultarPuntos(idCliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    public List<Beneficio> listarPromociones() {
        return promoDAO.listarPromociones();
    }

    public boolean canjearPuntos(int idCliente, int idPromo) {
        return promoDAO.canjear(idCliente, idPromo);
    }

    public List<String> getMovimientos() {
        List<String> movimientos = new ArrayList<>();
        String sql = "SELECT t.fecha, c.nombre, t.tipo, t.puntos, t.descripcion " +
                     "FROM transaccion_puntos t " +
                     "JOIN cliente c ON t.cliente_id = c.id " +
                     "ORDER BY t.fecha DESC";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String m = rs.getTimestamp("fecha") + " - " +
                           rs.getString("nombre") + " - " +
                           rs.getString("tipo") + " - " +
                           rs.getInt("puntos") + " pts - " +
                           rs.getString("descripcion");
                movimientos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (movimientos.isEmpty()) {
            movimientos.add("No hay movimiento aún");
        }

        return movimientos;
    }
}

