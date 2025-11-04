package havanna;

import java.time.LocalDate;

public class Compra {
    private int idCliente;
    private double monto;
    private LocalDate fecha;

    public Compra(int idCliente, double monto) {
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = LocalDate.now();
    }

    public int getIdCliente() { return idCliente; }
    public double getMonto() { return monto; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        return "Compra{cliente=" + idCliente + ", monto=" + monto + ", fecha=" + fecha + "}";
    }
}

