package DAO;
import DTO.DTOClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.Conexion.getConexion;



public class ClientDAO implements IClientDAO {

    @Override
    public List<DTOClient> listCustomers() {
        List<DTOClient> DTOClients = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var dtoClient = new DTOClient();
                dtoClient.setId(rs.getInt("id"));
                dtoClient.setName(rs.getString("nombre"));
                dtoClient.setLastName(rs.getString("apellido"));
                dtoClient.setMembership(rs.getInt("membresia"));
                DTOClients.add(dtoClient);
            }
        }catch (Exception e){
            System.out.println("Error listing customers: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return DTOClients;
    }

    @Override
    public boolean searchClientById(DTOClient DTOClient) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, DTOClient.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                DTOClient.setName(rs.getString("nombre"));
                DTOClient.setLastName(rs.getString("apellido"));
                DTOClient.setMembership(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL RECUPERAR EL CLIENTE = " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean addCustomers(DTOClient DTOClient) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente(nombre, apellido, membresia)" + " VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, DTOClient.getName());
            ps.setString(2, DTOClient.getLastName());
            ps.setInt(3, DTOClient.getMembership());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar el cliente" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modifyClient(DTOClient DTOClient) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=?" + " WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, DTOClient.getName());
            ps.setString(2, DTOClient.getLastName());
            ps.setInt(3, DTOClient.getMembership());
            ps.setInt(4, DTOClient.getId());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        } finally {
           try {
               con.close();
           }catch (Exception e){
               System.out.println("Error al cerrar la conexion: " + e.getMessage());
           }
        }
        return false;
    }

    @Override
    public boolean deleteClient(DTOClient DTOClient) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE  FROM cliente WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, DTOClient.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al eliminar el error" + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IClientDAO clienteDao = new ClientDAO();
        //Listar clientes
//        System.out.println("=== Listar Clientes ==);
//        var clientes = clienteDao.listCustomers();
//        clientes.forEach(System.out::println);

        //Buscar por id
//        var cliente1 = new Cliente(6);
//        System.out.println("cliente1 = " + cliente1);
//        var encontrado = clienteDao.searchClientById(cliente1);
//        if (encontrado){
//            System.out.println("Cliente encontrado = " + cliente1);
//        }else{
//            System.out.println("No se encontro el cliente = " + cliente1.getId());
//        }

        //Agregar Cliente
//        var nuevoCliente = new Cliente("Daniel", "Ortiz", 200);
//        var agregado = clienteDao.addCustomers(nuevoCliente);
//        if (agregado){
//            System.out.println("agregado = " + nuevoCliente);
//        } else {
//            System.out.println("No se agrego el cliente " + nuevoCliente);
//        }
//        //Listar clientes
//        System.out.println("=== Listar Clientes ==");
//        var clientes = clienteDao.listCustomers();
//        clientes.forEach(System.out::println);

        // Modificar cliente
//        var modifyClient = new Cliente(9, "Carlos Daniel", "Ortiz", 200);
//        var modificado = clienteDao.modifyClient(modifyClient);
//        if(modificado)
//            System.out.println("Cliente modificado : " + modifyClient);
//        else
//            System.out.println("No se modifico cliente: " + modifyClient);
//
//        // Listar clientes
//        System.out.println("*** Listar Clientes ***");
//        var clientes = clienteDao.listCustomers();
//        clientes.forEach(System.out::println);

        //Eliminar cliente
        var clienteEliminar = new DTOClient(9);
        var eliminado = clienteDao.deleteClient(clienteEliminar);

        if(eliminado)
            System.out.println("Cliente eliminado : " + clienteEliminar);
        else
            System.out.println("No se eliminado cliente: " + clienteEliminar);

        // Listar clientes
        System.out.println("*** Listar Clientes ***");
        var clientes = clienteDao.listCustomers();
        clientes.forEach(System.out::println);

    }
}
