package lk.ijse.pos_system_backend.service.impl;



import lk.ijse.pos_system_backend.dto.ItemDTO;

import lk.ijse.pos_system_backend.entity.Item;
import lk.ijse.pos_system_backend.exception.CustomException;
import lk.ijse.pos_system_backend.repo.ItemRepo;
import lk.ijse.pos_system_backend.service.custom.ItemService;
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
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        if (itemDTO==null) {
            throw new CustomException("CustomerDTO is null");
        }
       itemRepo.save(modelMapper.map(itemDTO, Item.class));
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        if (itemDTO==null) {
            throw new CustomException("CustomerDTO is null");
        }
     itemRepo.save(modelMapper.map(itemDTO, Item.class));
    }

    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        return modelMapper.map(items, new TypeToken<ArrayList<ItemDTO>>() {
        }.getType());
    }

    @Override
    public void searchItemById(Long id) {
        itemRepo.existsById(id);
    }
}
