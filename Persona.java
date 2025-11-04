package havanna;

public abstract class Persona {
    protected String nombre;
    protected String email;

    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public abstract void mostrarDatos();
}

