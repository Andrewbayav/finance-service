package invest.service.dto.representation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DictionaryDto {
    private String name;
    private String tcsTicker;
    private String yahooTicker;

    public DictionaryDto(String name, String tcsTicker, String yahooTicker) {
        this.name = name;
        this.tcsTicker = tcsTicker;
        this.yahooTicker = yahooTicker;
    }
}
