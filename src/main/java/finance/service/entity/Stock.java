package finance.service.entity;


import finance.service.dto.YahooPriceDto;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ticker;

    @Column
    private String name;

    @Column
    private double price;

    public Stock(YahooPriceDto stockDto) {
        this.ticker = stockDto.getTicker();
        this.name = stockDto.getName();
        this.price = stockDto.getPrice();
    }

}




