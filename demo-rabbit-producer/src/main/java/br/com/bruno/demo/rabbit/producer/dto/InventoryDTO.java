package br.com.bruno.demo.rabbit.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO implements Serializable {

    private String idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private Integer quantityInInventory;

}
