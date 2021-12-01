package finance.service.entity;

import finance.service.dto.YahooStockStatisticsDto;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@Data
public class PortfolioEntry {

    // portfolio block
    private double purchasePrice; // цена покупки
    private int quantity; // кол-во в портфеле
    private double expectedYield; // прибыль сегодня
    private double priceNowUSD; // цена сейчас
    private double priceNowEUR; // цена сейчас
    private double priceNowRUB; // цена сейчас
    private String currency;
    private String ticker;
    private String type;
    private String name;
    private double USD_RUB;
    private double EUR_RUB;
    private double USD_EUR;
    private double sumUSD; // суммарная стоимость позиции
    private double sumEUR; // суммарная стоимость позиции
    private double sumRUB; // суммарная стоимость позиции

    // statistics block
    private double enterpriseValue;
    private double forwardPE;
    private double priceToBook;
    private double enterpriseToEbitda;

    public PortfolioEntry(JSONObject entry, double USD_RUB, double EUR_RUB, double USD_EUR) throws JSONException {
        this.USD_RUB = USD_RUB;
        this.EUR_RUB = EUR_RUB;
        this.USD_EUR = USD_EUR;
        this.purchasePrice = entry.getJSONObject("averagePositionPrice").getDouble("value");
        this.currency = entry.getJSONObject("averagePositionPrice").getString("currency");
        this.expectedYield = entry.getJSONObject("expectedYield").getDouble("value");
        this.quantity = entry.getInt("balance");
        // TODO: продумать конвертацию, вынести из конструктора
        switch (this.currency) {
            case "USD":
                this.priceNowUSD = this.purchasePrice + this.expectedYield / this.quantity;
                this.priceNowEUR = this.priceNowUSD * this.USD_EUR;
                this.priceNowRUB = this.priceNowUSD * this.USD_RUB;
                break;
            case "EUR":
                this.priceNowEUR = this.purchasePrice + this.expectedYield / this.quantity;
                this.priceNowUSD = this.priceNowEUR / this.USD_EUR;
                this.priceNowRUB = this.priceNowEUR * this.EUR_RUB;
                break;
            case "RUB":
                this.priceNowRUB = this.purchasePrice + this.expectedYield / this.quantity;
                this.priceNowUSD = this.priceNowRUB / this.USD_RUB;
                this.priceNowEUR = this.priceNowRUB * this.EUR_RUB;
                break;
        }
        this.sumUSD = this.priceNowUSD * this.quantity;
        this.sumEUR = this.priceNowEUR * this.quantity;
        this.sumRUB = this.priceNowRUB * this.quantity;
        this.type = entry.getString("instrumentType");
        this.name = entry.getString("name");
        this.ticker = entry.getString("ticker");
    }

    public void addStatistics(YahooStockStatisticsDto yahooStockStatisticsDto) {
        this.enterpriseValue = yahooStockStatisticsDto.getEnterpriseValue();
        this.forwardPE = yahooStockStatisticsDto.getForwardPE();
        this.priceToBook = yahooStockStatisticsDto.getPriceToBook();
        this.enterpriseToEbitda = yahooStockStatisticsDto.getEnterpriseToEbitda();
    }
}
