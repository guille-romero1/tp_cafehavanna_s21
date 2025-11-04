package havanna;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        SistemaFidelizacion sistema = new SistemaFidelizacion();
        boolean salir = false;

        while (!salir) {
            printMenu();
            String line = sc.nextLine();
            int opt = -1;
            try { opt = Integer.parseInt(line.trim()); } catch (Exception e) { opt = -1; }

            switch (opt) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    int id = sistema.registrarCliente(nombre, email);
                    System.out.println("Cliente registrado con ID: " + id);
                    break;
                case 2:
                    System.out.print("ID cliente: ");
                    int idc = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Monto: ");
                    double monto = Double.parseDouble(sc.nextLine().trim());
                    int pts = sistema.registrarCompra(idc, monto);
                    System.out.println("Compra registrada. Puntos ganados: " + pts);
                    break;
                case 3:
                    System.out.print("ID cliente: ");
                    int idq = Integer.parseInt(sc.nextLine().trim());
                    int saldo = sistema.consultarPuntos(idq);
                    System.out.println("Puntos acumulados: " + saldo);
                    break;
                case 4:
                    System.out.print("ID cliente: ");
                    int idCan = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Promociones disponibles:");
                    List<Beneficio> promos = sistema.listaPromocion();
                    for (Beneficio p : promos) System.out.println(p);
                    System.out.print("ID promoción a canjear: ");
                    int idPromo = Integer.parseInt(sc.nextLine().trim());
                    boolean ok = sistema.canjearPuntos(idCan, idPromo);
                    System.out.println(ok ? "Canje exitoso" : "Canje fallido (saldo insuficiente o promo inválida)");
                    break;
                case 5:
                    System.out.println("Clientes registrados:");
                    List<Cliente> cls = sistema.listaClientes();
                    for (int i=0;i<cls.size();i++) {
                        System.out.println((i+1) + " - " + cls.get(i).getNombre());
                    }
                    break;
                case 6:
                    System.out.println("Movimientos recientes:");
                    for (String m : sistema.getMovimiento()) System.out.println(m);
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intentá de nuevo.");
            }
            System.out.println();
        }
        sc.close();
        System.out.println("Aplicación finalizada.");
    }

    private static void printMenu() {
        System.out.println("===== Sistema Fidelización - Café Havanna =====");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar compra");
        System.out.println("3. Consultar puntos");
        System.out.println("4. Canjear puntos");
        System.out.println("5. Listar clientes");
        System.out.println("6. Ver movimientos");
        System.out.println("7. Salir");
        System.out.print("Opción: ");
    }
}
