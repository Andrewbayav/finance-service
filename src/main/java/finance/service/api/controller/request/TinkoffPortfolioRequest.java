package finance.service.api.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TinkoffPortfolioRequest", description = "Получаем 4 параметра для подключения к Тинькофф инвестициям")
public class TinkoffPortfolioRequest {

    @ApiModelProperty("Токен")
    private String token;

}
