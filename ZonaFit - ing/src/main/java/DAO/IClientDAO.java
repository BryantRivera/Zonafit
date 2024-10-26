package DAO;

import DTO.DTOClient;

import java.util.List;

public interface IClientDAO {
    List<DTOClient> listCustomers();
    boolean searchClientById(DTOClient DTOClient);
    boolean addCustomers(DTOClient DTOClient);
    boolean modifyClient(DTOClient DTOClient);
    boolean deleteClient(DTOClient DTOClient);

}
