package finance.service.dto;

public class YahooPriceDto {
    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    private String ticker;
    private String name;
    private double price;

    public YahooPriceDto(String ticker, String name, double price) {
        this.ticker = ticker;
        this.name = name;
        this.price = price;
    }

    public boolean isStockDtoCorrect() {
        return (ticker != "" && name != "" && price != 0.0);
    }

    @Override
    public String toString() {
        return "StockDto{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
