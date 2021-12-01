package finance.service.api.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TinkoffPortfolioRequest", description = "Получаем параметры для подключения к Тинькофф инвестициям")
public class PortfolioRequest {

    @ApiModelProperty("Токен")
    private String token;

    @ApiModelProperty("Требуется ли обновление данных")
    private Boolean refresh;
}
