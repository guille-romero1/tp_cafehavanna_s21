package havanna;

import java.util.*;
import java.time.LocalDate;

public class PrincipalDAO {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaFidelizacion sistema = new SistemaFidelizacion();
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opt = leerEntero("Opción: ");

            switch (opt) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("DNI: ");
                    String dni = sc.nextLine().trim();
                    System.out.print("Teléfono: ");
                    String tel = sc.nextLine().trim();
                    LocalDate fechaNac = leerFecha("Fecha de nacimiento (YYYY-MM-DD): ");
                    int id = sistema.registrarCliente(nombre, email, dni, tel, fechaNac);
                    System.out.println("✅ Cliente registrado con ID: " + id);
                    break;

                case 2:
                    int idc = leerEntero("ID cliente: ");
                    double monto = leerDouble("Monto de la compra: ");
                    int pts = sistema.registrarCompra(idc, monto);
                    System.out.println("Compra registrada. Puntos ganados: " + pts);
                    break;

                case 3:
                    int idq = leerEntero("ID cliente: ");
                    int saldo = sistema.consultarPuntos(idq);
                    System.out.println("Puntos acumulados: " + saldo);
                    break;

                case 4:
                    int idCan = leerEntero("ID cliente: ");
                    System.out.println("Promociones disponibles:");
                    for (Beneficio p : sistema.listarPromociones())
                        System.out.println(p);
                    int idPromo = leerEntero("ID promoción a canjear: ");
                    boolean ok = sistema.canjearPuntos(idCan, idPromo);
                    System.out.println(ok ? "✅ Canje exitoso" : "❌ Canje fallido (saldo insuficiente o promo inválida)");
                    break;

                case 5:
                    System.out.println("Clientes registrados:");
                    for (Cliente c : sistema.listarClientes())
                        System.out.println(c.getId() + " - " + c.getNombre() + " (" + c.getEmail() + ")");
                    break;

                case 6:
                    System.out.println("Movimientos recientes:");
                    for (String m : sistema.getMovimientos())
                        System.out.println(m);
                    break;

                case 7:
                    salir = true;
                    break;

                default:
                    System.out.println("❌ Opción inválida. Intentá de nuevo.");
            }
            System.out.println();
        }
        sc.close();
        System.out.println("Aplicación finalizada.");
    }


    private static void mostrarMenu() {
        System.out.println("===== Sistema de Fidelización - Café Havanna =====");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar compra");
        System.out.println("3. Consultar puntos");
        System.out.println("4. Canjear puntos");
        System.out.println("5. Listar clientes");
        System.out.println("6. Ver movimientos");
        System.out.println("7. Salir");
    }

    private static int leerEntero(String mensaje) {
        int valor = -1;
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                valor = Integer.parseInt(linea);
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debe ser un número entero.");
            }
        }
        return valor;
    }

    private static double leerDouble(String mensaje) {
        double valor = -1;
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                valor = Double.parseDouble(linea);
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debe ser un número decimal.");
            }
        }
        return valor;
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                fecha = LocalDate.parse(linea);
                break;
            } catch (Exception e) {
                System.out.println("❌ Fecha inválida. Formato esperado: YYYY-MM-DD");
            }
        }
        return fecha;
    }
}



