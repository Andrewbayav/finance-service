package finance.service.entity;

import finance.service.dto.YahooFinancialDto;
import finance.service.dto.YahooStatisticsDto;
import finance.service.dto.YahooSummaryDto;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

@Data
@Entity
@Table(name = "portfolio")
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "timestamp")
    LocalDateTime timestamp;

    public PortfolioEntity(UUID uuid) {
        this.uuid = uuid;
        this.timestamp = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
    }

//
//    private double priceNowEUR; // цена сейчас
//    private double priceNowRUB; // цена сейчас
//    private double sumEUR; // суммарная стоимость позиции
//    private double sumRUB; // суммарная стоимость позиции
//
//
//    public PortfolioEntry(JSONObject entry, double USD_RUB, double EUR_RUB, double USD_EUR) throws JSONException {
//        this.USD_RUB = USD_RUB;
//        this.EUR_RUB = EUR_RUB;
//        this.USD_EUR = USD_EUR;
//        this.purchasePrice = entry.getJSONObject("averagePositionPrice").getDouble("value");
//        this.currency = entry.getJSONObject("averagePositionPrice").getString("currency");
//        this.expectedYield = entry.getJSONObject("expectedYield").getDouble("value");
//        this.quantity = entry.getInt("balance");
//        // TODO: продумать конвертацию, вынести из конструктора
//        switch (this.currency) {
//            case "USD":
//                this.priceUsd = this.purchasePrice + this.expectedYield / this.quantity;
//                this.priceNowEUR = this.priceUsd * this.USD_EUR;
//                this.priceNowRUB = this.priceUsd * this.USD_RUB;
//                break;
//            case "EUR":
//                this.priceUsd = this.priceNowEUR / this.USD_EUR;
//                this.priceNowEUR = this.purchasePrice + this.expectedYield / this.quantity;
//                this.priceNowRUB = this.priceNowEUR * this.EUR_RUB;
//                break;
//            case "RUB":
//                this.priceUsd = this.priceNowRUB / this.USD_RUB;
//                this.priceNowRUB = this.purchasePrice + this.expectedYield / this.quantity;
//                this.priceNowEUR = this.priceNowRUB * this.EUR_RUB;
//                break;
//        }
//        this.sumUsd = this.priceUsd * this.quantity;
////        this.sumEUR = this.priceNowEUR * this.quantity;
////        this.sumRUB = this.priceNowRUB * this.quantity;
//        this.type = entry.getString("instrumentType");
//        this.name = entry.getString("name");
//        this.ticker = entry.getString("ticker");
//    }
//
//    public void addStatistics(YahooStatisticsDto yahooStatisticsDto) {
//        this.enterpriseValue = yahooStatisticsDto.getEnterpriseValue();
//        this.priceToBook = yahooStatisticsDto.getPriceToBook();
//        this.enterpriseToEbitda = yahooStatisticsDto.getEnterpriseToEbitda();
//    }
//
//    public void addFinancialData(YahooFinancialDto yahooFinancialDto) {
//        this.recommendationMean = yahooFinancialDto.getRecommendationMean();
//        this.returnOnEquity = yahooFinancialDto.getReturnOnEquity();
//    }
//
//    public void addSummaryDetails(YahooSummaryDto yahooSummaryDto) {
//        this.dividendYield = yahooSummaryDto.getDividendYield();
//        this.trailingPE = yahooSummaryDto.getTrailingPE();
//        this.forwardPE = yahooSummaryDto.getForwardPE();
//        this.marketCap = yahooSummaryDto.getMarketCap();
//        this.priceToSalesTrailing12Months = yahooSummaryDto.getPriceToSalesTrailing12Months();
//    }
}
