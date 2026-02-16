package lk.ijse.pos_system_backend.service.impl;

import lk.ijse.pos_system_backend.dto.OrderDTO;
import lk.ijse.pos_system_backend.dto.OrderDetailsDTO;
import lk.ijse.pos_system_backend.entity.Customer;
import lk.ijse.pos_system_backend.entity.Item;
import lk.ijse.pos_system_backend.entity.OrderDetails;
import lk.ijse.pos_system_backend.entity.Orders;
import lk.ijse.pos_system_backend.exception.CustomException;
import lk.ijse.pos_system_backend.repo.CustomerRepo;
import lk.ijse.pos_system_backend.repo.ItemRepo;
import lk.ijse.pos_system_backend.repo.OrderDetailsRpo;
import lk.ijse.pos_system_backend.repo.OrderRepo;
import lk.ijse.pos_system_backend.service.custom.OrderService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private OrderDetailsRpo orderDetailsRpo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void placeOrder(OrderDTO orderDTO) {
        Customer customer = customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new CustomException("Customer not found"));

        Orders order = new Orders();
        order.setOrderDate(orderDTO.getOrderDate());
        order.setCustomer(customer);

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDetailsDTO dto : orderDTO.getOrderDetails()) {

            Item item = itemRepo.findById(dto.getItemId())
                    .orElseThrow(() -> new CustomException("Item not found"));

            if (item.getQtyOnHand() < dto.getQty()) {
                throw new CustomException("Not enough quantity for Item ID: " + item.getId());
            }

            item.setQtyOnHand(item.getQtyOnHand() - dto.getQty());
            itemRepo.save(item);

            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrder(order);
            orderDetail.setItem(item);
            orderDetail.setQty(dto.getQty());
            orderDetail.setPrice(dto.getUnitPrice());

            orderDetailsList.add(orderDetail);
        }

        order.setOrderDetails(orderDetailsList);
        orderRepo.save(order);
    }
}
