package presentation;

import DAO.ClientDAO;
import DAO.IClientDAO;
import DTO.DTOClient;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        var exit = false;
        var console = new Scanner(System.in);
        // Creamos un objeto de la clase clientDAO
        IClientDAO clientDAO = new ClientDAO();
        while (!exit) {
            try {
                var option = showMenu(console);
                exit = runOptions(console, option, clientDAO);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int showMenu(Scanner console) {
        System.out.print("""
                *** Fit Zone (GYM)
                1. List Clients
                2. Search Client
                3. Add Client
                4. Modify Client
                5. Delete Client
                6. Exit
                Choose an option:\s""");
        var option = Integer.parseInt(console.nextLine());
        return option;
    }

    private static boolean runOptions(Scanner console, int option, IClientDAO clientDAO){
        var exit = false;
        switch (option){
            case 1 ->{//List DTOClient
                System.out.println("===== List DTOClient =====");
                var DTOClients = clientDAO.listCustomers();
                DTOClients.forEach(System.out::println);
            }
            case 2 ->{//Search Cliente
                System.out.println("===== Search Client ======");
                System.out.println("Enter the id of the Client to search for");
                var idClient = Integer.parseInt(console.nextLine());
                var DTOClient = new DTOClient(idClient);
                var found = clientDAO.searchClientById(DTOClient);
                if(found){
                    System.out.println("Client found" + DTOClient);
                }else{
                    System.out.println("Client no found" + DTOClient);
                }
            }
            case 3 ->{//Add Client
                System.out.println("==== add Client ====");
                System.out.println("Insert the name");
                var name = console.nextLine();
                System.out.println("Insert the last name");
                var lastName = console.nextLine();
                System.out.println("Insert the membership:");
                int membership = Integer.parseInt(console.nextLine());
                // creamos objeto DTOClient (Sin id)
                var DTOClient = new DTOClient(name, lastName, membership);
                var add = clientDAO.addCustomers(DTOClient);
                if(add){
                    System.out.println("Aggregate Client" + DTOClient);
                }else{
                    System.out.println("Client not Aggregator\n" + DTOClient);
                }
            }
            case 4 ->{//Modify Client
                System.out.println("==== Modify Client ====");
                System.out.println("Enter the id to identify\n:");
                int id = Integer.parseInt(console.nextLine());
                System.out.println("Enter the new name");
                var name = console.nextLine();
                System.out.println("Enter the new lastName");
                var lastName = console.nextLine();
                System.out.println("Enter the new membership:");
                int membership = Integer.parseInt(console.nextLine());
                // create object DTOClient (without id)
                var DTOClient = new DTOClient(id, name, lastName, membership);
                var modify = clientDAO.modifyClient(DTOClient);
                if(modify){
                    System.out.println("Modified customer\n" + DTOClient);
                }else{
                    System.out.println("Modified not customer\n" + DTOClient);
                }
            }
            case 5 ->{//Delete DTOClient
                System.out.println("===== Delete Client ======");
                System.out.println("Enter the id to delete");
                var idClient = Integer.parseInt(console.nextLine());
                var DTOClient = new DTOClient(idClient);
                var delete = clientDAO.deleteClient(DTOClient);
                if(delete){
                    System.out.println("Client delete" + DTOClient);
                }else{
                    System.out.println("Client not delete" + DTOClient);
                }
            }
            case 6 ->{
                System.out.println("Good bye");
                exit = true;
            }
            default -> System.out.println("Opcion not found" + option);
        }

        return exit;
    }
}
