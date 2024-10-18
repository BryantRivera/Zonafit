package datos;
import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.Conexion.getConexion;



public class ClienteDAO implements  IClienteDAO{

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
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
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente(nombre, apellido, membresia)" + " VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar el cliente" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args) {
        //Listar clientes
//        System.out.println("=== Listar Clientes ===");
          IClienteDAO clienteDao = new ClienteDAO();
//        var clientes = clienteDao.listarClientes();
//        clientes.forEach(System.out::println);

        //Buscar por id
//        var cliente1 = new Cliente(6);
//        System.out.println("cliente1 = " + cliente1);
//        var encontrado = clienteDao.buscarClientePorId(cliente1);
//        if (encontrado){
//            System.out.println("Cliente encontrado = " + cliente1);
//        }else{
//            System.out.println("No se encontro el cliente = " + cliente1.getId());
//        }

        //Agregar Cliente
        var nuevoCliente = new Cliente("Daniel", "Ortiz", 300);
        var agregado = clienteDao.agregarCliente(nuevoCliente);
        if (agregado){
            System.out.println("agregado = " + agregado);
        }
    }
}
