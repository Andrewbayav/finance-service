package finance.service.api.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TinkoffInfoRequest", description = "Получаем 4 параметра для подключения к Тинькофф инвестициям")
public class TinkoffInfoRequest {

    @ApiModelProperty("Токен")
    private String token;

    @ApiModelProperty("Тикер")
    private String ticker;

    @ApiModelProperty("Разрешающий интервал свечей")
    private String interval;

    @ApiModelProperty("Признак использования песочницы")
    private String isSandboxEnabled;

}
