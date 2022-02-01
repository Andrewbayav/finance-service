package finance.service.jdbcMapper;

import finance.service.dto.OverviewDto;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OverviewMapper implements RowMapper<OverviewDto> {

    @Override
    public OverviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OverviewDto(
                rs.getInt("balance"),
                rs.getString("name"),
                rs.getString("ticker"),
                rs.getString("instrument_type"),
                rs.getString("currency"),
                rs.getString("timestamp"),
                rs.getDouble("rate"),
                rs.getDouble("average_position_price"),
                rs.getDouble("expected_yield"),
                rs.getDouble("recommendation_mean"),
                rs.getDouble("return_on_equity"),
                rs.getDouble("price_to_book"),
                rs.getDouble("enterprise_value"),
                rs.getDouble("dividend_yield"),
                rs.getDouble("trailingpe"),
                rs.getDouble("price_to_sales_trailing12months"),
                rs.getDouble("market_cap")
        );
    }
}
