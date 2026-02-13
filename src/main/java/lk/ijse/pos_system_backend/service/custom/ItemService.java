package lk.ijse.pos_system_backend.service.custom;

import lk.ijse.pos_system_backend.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(ItemDTO itemDTO);
    void deleteItem(Long id);
    List<ItemDTO> getAllItems();
    void searchItemById(Long id);
}
