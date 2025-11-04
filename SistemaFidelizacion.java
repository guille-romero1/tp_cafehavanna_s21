package havanna;

import java.util.*;
import java.time.LocalDate;

public class SistemaFidelizacion {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Compra> compras = new ArrayList<>();
    private List<Beneficio> beneficios = new ArrayList<>();
    private List<String> movimientos = new ArrayList<>();
    private int nextClientId = 1;
    private int nextPromoId = 1;

    public SistemaFidelizacion() {
        crearPromocion("Café gratis", 10);
        crearPromocion("Caja de alfajores", 30);
        crearPromocion("Descuento 20%", 20);
    }

    public int registrarCliente(String nombre, String email) {
        Cliente c = new Cliente(nombre, email);
        clientes.add(c);
        int id = nextClientId++;
        movimientos.add("Cliente creado id=" + id + " -> " + email + " (" + nombre + ")");
        return id;
    }

    public Cliente getClientId(int id) {
        int index = id - 1;
        if (index >=0 && index < clientes.size()) return clientes.get(index);
        return null;
    }

    public int registrarCompra(int idCliente, double monto) {
        Cliente c = getClientId(idCliente);
        if (c == null) throw new NoSuchElementException("Cliente no encontrado");
        Compra comp = new Compra(idCliente, monto);
        compras.add(comp);
        int puntos = (int)Math.floor(monto / 100.0); 
        if (puntos > 0) c.sumaPuntos (puntos);
        movimientos.add("Compra idCliente=" + idCliente + " monto=" + monto + " -> +" + puntos + " pts");
        return puntos;
    }

    public int consultarPuntos(int idCliente) {
        Cliente c = getClientId(idCliente);
        if (c == null) throw new NoSuchElementException("Cliente no encontrado");
        return c.getPuntos();
    }

    public boolean canjearPuntos(int idCliente, int promoId) {
        Cliente c = getClientId(idCliente);
        if (c == null) throw new NoSuchElementException("Cliente no encontrado");
        Beneficio promo = null;
        for (Beneficio p : beneficios) if (p.getId() == promoId && p.isActivo()) promo = p;
        if (promo == null) return false;
        if (c.canjearPuntos(promo.getPuntosRequeridos())) {
            movimientos.add("Canje cliente=" + idCliente + " promo=" + promoId + " -> -" + promo.getPuntosRequeridos() + " pts");
            return true;
        }
        return false;
    }

    public int crearPromocion(String desc, int puntos) {
        Beneficio b = new Beneficio(nextPromoId++, desc, puntos, true);
        beneficios.add(b);
        return b.getId();
    }

    public List<Beneficio> listaPromocion() {
        return Collections.unmodifiableList(beneficios);
    }

    public List<Cliente> listaClientes() {
        return Collections.unmodifiableList(clientes);
    }

    public List<String> getMovimiento() {
        return Collections.unmodifiableList(movimientos);
    }
}