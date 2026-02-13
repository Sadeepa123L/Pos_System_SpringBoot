package lk.ijse.pos_system_backend.controller;


import jakarta.validation.Valid;
import lk.ijse.pos_system_backend.dto.ItemDTO;
import lk.ijse.pos_system_backend.service.impl.ItemServiceImpl;
import lk.ijse.pos_system_backend.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @PostMapping(value = "/saveItem")
    public ResponseEntity<APIResponse<String>> saveItem(@Valid @RequestBody ItemDTO itemDTO) {
        itemServiceImpl.saveItem(itemDTO);
        return new ResponseEntity<>(new APIResponse<>(
                200,
                "Item saved successfully",
                null
        ), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateItem")
    public ResponseEntity<APIResponse<String>> updateItem(@Valid @RequestBody ItemDTO itemDTO) {
        itemServiceImpl.updateItem(itemDTO);
        return new ResponseEntity<>(new APIResponse<>(
                200,
                "Item updated successfully",
                null
        ), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteItem/{id}")
    public ResponseEntity<APIResponse<String>> deleteItem(@ Valid @PathVariable Long id){
        itemServiceImpl.deleteItem(id);
        return new ResponseEntity<>(new APIResponse<>(
                200,
                "item deleted successfully",
                null
        ),HttpStatus.OK);
    }
    @GetMapping(value = "/getAllItems")
    public ResponseEntity <APIResponse<List<ItemDTO>>> getAllItems() {
        List<ItemDTO> itemDTOList = itemServiceImpl.getAllItems();
        return new ResponseEntity<>(new APIResponse<>(
                200,
                "Item retrieved successfully",
                itemDTOList
        ),HttpStatus.OK);
    }

    @GetMapping(value = "/searchItem/{id}")
    public ResponseEntity<APIResponse<String>> searchItem(@Valid @PathVariable Long id) {
        itemServiceImpl.searchItemById(id);
        return new ResponseEntity<>(new APIResponse<>(
                200,
                "Item searched successfully",
                null
        ),HttpStatus.OK);
    }
}
