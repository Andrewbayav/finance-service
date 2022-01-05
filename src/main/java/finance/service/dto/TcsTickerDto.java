package finance.service.dto;

import lombok.Data;

@Data
public class TcsTickerDto {
    private String name;
    private String ticker;
    private String instrumentType;
    private String currency;
    private int lots;
    private double expectedYield;
    private double averagePositionPrice;


    public TcsTickerDto(String name,
                        String ticker,
                        String instrumentType,
                        String currency,
                        int lots,
                        double expectedYield,
                        double averagePositionPrice) {
        this.name = name;
        this.ticker = ticker;
        this.instrumentType = instrumentType;
        this.currency = currency;
        this.lots = lots;
        this.expectedYield = expectedYield;
        this.averagePositionPrice = averagePositionPrice;
    }
}
