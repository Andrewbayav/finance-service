package invest.service.dto.yahoo;

import lombok.Data;

@Data
public class ExchangeDto {

    private String ticker;
    private String name;
    private double rate;

    public ExchangeDto(String ticker) {
        this.ticker = ticker;
        this.name = "";
        this.rate = 0.0;
    }

    public ExchangeDto(String ticker, String name, double rate) {
        this.ticker = ticker;
        this.name = name;
        this.rate = rate;
    }
}
