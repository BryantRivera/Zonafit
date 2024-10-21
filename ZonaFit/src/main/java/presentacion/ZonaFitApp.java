package presentacion;

import datos.ClienteDAO;
import datos.IClienteDAO;
import dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);
        // Creamos un objeto de la clase clienteDao
        IClienteDAO clienteDao = new ClienteDAO();
        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                *** Zona Fit (GYM)
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elije una opcion:\s""");
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 ->{//Listar clientes
                System.out.println("===== Listado de clientes =====");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 ->{//Buscar Cliente
                System.out.println("===== Buscar Cliente ======");
                System.out.println("Introduce el id del cliente a buscar");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if(encontrado){
                    System.out.println("Cliente encontrado" + cliente);
                }else{
                    System.out.println("Cliente no encontrado" + cliente);
                }
            }
            case 3 ->{//Agregar Cliente
                System.out.println("==== Agregar Cliente ====");
                System.out.println("Ingrese el nombre");
                var nombre = consola.nextLine();
                System.out.println("Ingrese el apellido");
                var apellido = consola.nextLine();
                System.out.println("Ingrese la membresia:");
                int membresia = Integer.parseInt(consola.nextLine());
                // creamos objeto cliente (Sin id)
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println("Cliente Agregado" + cliente);
                }else{
                    System.out.println("Cliente no Agregador" + cliente);
                }
            }
            case 4 ->{//Modificar Cliente
                System.out.println("==== Modificar Cliente ====");
                System.out.println("Ingrese el id a identificar:");
                int id = Integer.parseInt(consola.nextLine());
                System.out.println("Ingrese el nombre nuevo nombre");
                var nombre = consola.nextLine();
                System.out.println("Ingrese el nuevo apellido");
                var apellido = consola.nextLine();
                System.out.println("Ingrese la nueva membresia:");
                int membresia = Integer.parseInt(consola.nextLine());
                // creamos objeto cliente (Sin id)
                var cliente = new Cliente(id, nombre, apellido, membresia);
                var modificar = clienteDAO.modificarCliente(cliente);
                if(modificar){
                    System.out.println("Cliente modificado" + cliente);
                }else{
                    System.out.println("Cliente no modificado" + cliente);
                }
            }
            case 5 ->{//Eliminar cliente
                System.out.println("===== Eliminar Cliente ======");
                System.out.println("Introduce el id del cliente a eliminar");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminar = clienteDAO.eliminarCliente(cliente);
                if(eliminar){
                    System.out.println("Cliente eliminar" + cliente);
                }else{
                    System.out.println("Cliente no eliminado" + cliente);
                }
            }
            case 6 ->{
                System.out.println("Hasta pronto");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida" + opcion);
        }

        return salir;
    }
}
