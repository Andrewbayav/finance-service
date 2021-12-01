CREATE or REPLACE VIEW financial_overview
AS SELECT
    t.id, t.uuid, t.name, t.ticker, t.average_position_price, t.lots, t.currency, t.expected_yield, t.instrument_type,
    f.recommendation_mean, f. return_on_equity,
    st.price_to_book, st.enterprise_value,
    s.dividend_yield, s.trailingpe, s.price_to_sales_trailing12months, s.market_cap
FROM
    tcs t
JOIN yahoo_financial f on t.ticker = f.ticker and t.uuid = f.uuid
JOIN yahoo_statistics st on st.ticker = f.ticker and st.uuid = f.uuid
JOIN yahoo_summary s on s.ticker = st.ticker and s.uuid = st.uuid;
