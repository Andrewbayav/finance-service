package finance.service.api.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "YahooRequest", description = "Получаем тикер, по которому запрашивать данные с Yahoo Finance Api")
public class YahooRequest {

    @ApiModelProperty("Тикер")
    private String ticker;

}
