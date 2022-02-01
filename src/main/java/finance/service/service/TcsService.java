package finance.service.service;

import finance.service.dto.TcsTickerDto;
import finance.service.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


/* TODO: 1. Реализовать логику сохранения данных о ценных бумагах в БД

 * */


@Slf4j
@Service
@RequiredArgsConstructor
public class TcsService {

    @Value("${tinkoff.api.url}")
    private String tinkoffApiUrl;

    @Value("${tinkoff.api.url.portfolio}")
    private String portfolioEndpoint;

    public ArrayList<TcsTickerDto> getPortfolio(String token) throws JSONException {
        String jsonData = HttpUtil.sendTinkoffPortfolioRequest(tinkoffApiUrl + portfolioEndpoint, token);
        ArrayList<TcsTickerDto> list = new ArrayList<>();

        JSONArray portfolio = new JSONObject(jsonData).getJSONObject("payload").getJSONArray("positions");
        for (int i = 0; i < portfolio.length(); i++) {
            JSONObject entry = portfolio.getJSONObject(i);
            String instrumentType = entry.getString("instrumentType");

            // На этом этапе мы обрабатывем только акции
            if (!instrumentType.equals("Stock")) continue;
            list.add(new TcsTickerDto(
                    entry.getString("name"),
                    entry.getString("ticker"),
                    instrumentType,
                    entry.getJSONObject("averagePositionPrice").getString("currency"),
                    entry.getInt("balance"),
                    entry.getJSONObject("expectedYield").getDouble("value"),
                    entry.getJSONObject("averagePositionPrice").getDouble("value")
            ));
        }
        return list;
    }
}

