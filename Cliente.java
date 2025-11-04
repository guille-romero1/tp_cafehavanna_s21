package havanna;

public class Cliente extends Persona {
    private int puntos;

    public Cliente(String nombre, String email) {
        super(nombre, email);
        this.puntos = 0;
    }

    public int getPuntos() { return puntos; }
    public void sumaPuntos(int p) { this.puntos += p; }
    public boolean canjearPuntos(int puntosCanje) {
        if (puntos >= puntosCanje) {
            puntos -= puntosCanje;
            return true;
        }
        return false;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Cliente: " + nombre + " | Email: " + email + " | Puntos: " + puntos);
    }
}

