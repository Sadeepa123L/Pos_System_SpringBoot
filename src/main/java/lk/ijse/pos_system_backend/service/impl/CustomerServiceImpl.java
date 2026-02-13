package lk.ijse.pos_system_backend.service.impl;

import lk.ijse.pos_system_backend.dto.CustomerDTO;
import lk.ijse.pos_system_backend.entity.Customer;
import lk.ijse.pos_system_backend.exception.CustomException;
import lk.ijse.pos_system_backend.repo.CustomerRepo;
import lk.ijse.pos_system_backend.service.custom.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (customerDTO==null) {
            throw new CustomException("CustomerDTO is null");
        }
        customerRepo.save(modelMapper.map( customerDTO, Customer.class));

    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if(customerDTO==null) {
            throw new CustomException("CustomerDTO is null");
        }
        customerRepo.save(modelMapper.map( customerDTO, Customer.class));

    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return modelMapper.map(customers, new TypeToken<ArrayList<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public void searchCustomerByID(Long id) {
        customerRepo.existsById(id);
    }
}
