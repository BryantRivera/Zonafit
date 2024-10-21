package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);
	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		//Levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp() {
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir) {
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}
	}

	private int mostrarMenu(Scanner consola) {
		logger.info(nl +"""
				            ==== Zona Fit (GYM) ====
				            1. Listar Clientes
				            2. Buscar Cliente
				            3. Agregar Cliente
				            4. Modificar Cliente
				            5. Eliminar Cliente
				            6. Salir
				            Elije una opcion:\s
				""" + nl);
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}
	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		var salir = false;
		switch (opcion) {
			case 1 -> {
				logger.info(nl + "==== Listado de clientes ====" + nl);
				List<Cliente> clientes = clienteServicio.listarCliente();
				clientes.forEach(cliente -> logger.info(clientes.toString() + nl));
			}
			case 2 ->{//Buscar Cliente
				logger.info(nl + "===== Buscar Cliente ======" + nl);
				logger.info(nl + "Introduce el id del cliente a buscar" + nl);
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					logger.info(nl + "Cliente encontrado!!!" + cliente + nl);
				}else{
					logger.info(nl + "Cliente No encontrado!!!" + cliente + nl);
				}
			}
			case 3 ->{//Agregar Cliente
				logger.info(nl + "===== Agregar Cliente ======" + nl);
				logger.info(nl + "Introduce el Nombre del cliente" + nl);
				var nombre = consola.nextLine();
				logger.info(nl + "Introduce el apellido" + nl);
				var apellido = consola.nextLine();
				logger.info(nl + "Introduce la membresia" + nl);
				var membresia = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				if(cliente != null){
					logger.info(nl + "Cliente Agregado!!!" + cliente + nl);
				}else{
					logger.info(nl + "Cliente No Agregado!!!" + cliente + nl);
				}
			}
			case 4 ->{//Eliminar Cliente
				logger.info(nl + "===== Modificar Cliente ======" + nl);
				logger.info(nl + "Introduce el id" + nl);
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
				logger.info(nl + "Introduce el Nombre del cliente" + nl);
				var nombre = consola.nextLine();
				logger.info(nl + "Introduce el apellido" + nl);
				var apellido = consola.nextLine();
				logger.info(nl + "Introduce la membresia" + nl);
				var membresia = Integer.parseInt(consola.nextLine());
				cliente.setId(idCliente);
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				logger.info(nl + "Cliente Modificado!!!" + cliente + nl);
				}else{
					logger.info(nl + "Cliente No Modificado!!!" + cliente + nl);
				}
			}
			case 5 ->{//Eliminar cliente
				logger.info("--- Eliminar Cliente ---" + nl);
				logger.info("Id Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				var cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					clienteServicio.eliminarCliente(cliente);
					logger.info("Cliente eliminado: " + cliente + nl);
				}
				else
					logger.info("Cliente No encontrado: " + cliente + nl);

			}
			case 6 ->{
				logger.info("Hasta pronto");
				salir = true;
			}
			default -> logger.info("Opcion no reconocida" + opcion);
		}
		return salir;
	}
}
