package havanna;

public class Beneficio {
    private int id;
    private String descripcion;
    private int puntosRequeridos;
    private boolean activo;

    public Beneficio(int id, String descripcion, int puntosRequeridos, boolean activo) {
        this.id = id;
        this.descripcion = descripcion;
        this.puntosRequeridos = puntosRequeridos;
        this.activo = activo;
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public int getPuntosRequeridos() { return puntosRequeridos; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean a) { this.activo = a; }

    @Override
    public String toString() {
        return "Promo[" + id + "] " + descripcion + " (" + puntosRequeridos + " pts) - " + (activo ? "ACTIVA" : "INACTIVA");
    }
}


