package invest.service.api.controller;

import invest.service.utils.Utils;
import invest.service.websocket.kafka.KafkaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/kafka", produces = {MediaType.APPLICATION_JSON_VALUE})
public class KafkaController {

    private final KafkaService kafkaService;

    @Autowired
    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Зауск потока отправки сообщений о всех акциях")
    public HttpStatus requestKafkaStream(@RequestParam String recommendations) {
        try {
            kafkaService.startKafka(Utils.parseDouble(recommendations));
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/stop")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Остановка потока отправки сообщений о всех акциях")
    public HttpStatus pauseKafkaStream() {
        try {
            kafkaService.stopKafka();
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
