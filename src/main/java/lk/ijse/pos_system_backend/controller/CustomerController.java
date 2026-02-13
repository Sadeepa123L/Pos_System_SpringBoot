package lk.ijse.pos_system_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.pos_system_backend.dto.CustomerDTO;
import lk.ijse.pos_system_backend.service.impl.CustomerServiceImpl;
import lk.ijse.pos_system_backend.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping(value = "/saveCustomer")
    public ResponseEntity<APIResponse<String>> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerServiceImpl.saveCustomer(customerDTO);
        return new ResponseEntity<>(new APIResponse<>(
                201,"customer saved",null
        ), HttpStatus.CREATED);
    }
    @PutMapping(value = "/updateCustomer")
    public ResponseEntity<APIResponse<String>> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerServiceImpl.updateCustomer(customerDTO);
        return new ResponseEntity<>(new APIResponse<>(
                201,"Customer updated",null
        ), HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteCustomer/{id}")
    public ResponseEntity <APIResponse<String>> deleteCustomer(@Valid @PathVariable Long id) {
        customerServiceImpl.deleteCustomer(id);
        return new ResponseEntity<>(new APIResponse<>(
                200,
                "Customer deleted successfully",
                null),
                HttpStatus.OK);
    }
    @GetMapping(value = "/getAllCustomers")
    public ResponseEntity <APIResponse<List<CustomerDTO>>> getAllCustomers() {
        List<CustomerDTO> customerDTOList = customerServiceImpl.getAllCustomers();
        return new ResponseEntity<>(new APIResponse<>(200,
                "Customers retrieved successfully",
                customerDTOList), HttpStatus.OK);

    }
    @GetMapping(value = "/searchCustomer/{id}")
    public ResponseEntity <APIResponse<String>> searchCustomer(@PathVariable Long id) {
        customerServiceImpl.searchCustomerByID(id);
        return new ResponseEntity<>(new APIResponse<>(
                200,
                "Customer searched successfully",
                null),
                HttpStatus.OK);
    }
}
