package br.com.bruno.demo.rabbit.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO implements Serializable {

    private String idPrice;
    private String idProduct;
    private Double value;

}
