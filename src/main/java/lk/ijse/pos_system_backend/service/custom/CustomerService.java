package lk.ijse.pos_system_backend.service.custom;

import lk.ijse.pos_system_backend.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    List<CustomerDTO> getAllCustomers();
    void searchCustomerByID(Long id);
}
