package invest.service.websocket.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import invest.service.dto.representation.AnalysisDto;
import invest.service.service.RepositoryService;
import invest.service.service.YahooStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class KafkaService {

    @Value("${kafka.input.topic}")
    private String analyticsTopic;

    private static final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final YahooStockService yahooStockService;
    private final RepositoryService repositoryService;


    @Autowired
    public KafkaService(
            KafkaTemplate<String, String> kafkaTemplate,
            YahooStockService yahooStockService,
            RepositoryService repositoryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.yahooStockService = yahooStockService;
        this.repositoryService = repositoryService;
    }

    private KafkaThread kafkaThread;


    public void sendToKafka(AnalysisDto obj) {
        String json = "";
        try {
            json = ow.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(analyticsTopic, json);
    }

    public void startKafka(Double recommendations) {
        kafkaThread = new KafkaThread();
        kafkaThread.setRecommendations(recommendations);
        Thread thread = new Thread(kafkaThread);
        thread.start();
    }

    public void stopKafka() {
        if (kafkaThread != null) {
            kafkaThread.setStopped(true);
        }
    }

    class KafkaThread implements Runnable {
        private boolean stopped;
        private double recommendations;

        public boolean isStopped() {
            return stopped;
        }
        public void setStopped(boolean stopped) {
            this.stopped = stopped;
        }
        public void setRecommendations(double recommendations) {
            this.recommendations = recommendations;
        }

        @Override
        public void run() {
            Map<String, String> map = repositoryService.getAllTickersFromDictionary();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sleep();
                if (stopped) break;
                AnalysisDto dto = yahooStockService.getQuickAnalysisDto(entry.getKey(), entry.getValue());
                if (dto.getRecommendationMean() >= recommendations) {
                    sendToKafka(dto);
                }
            }
        }

        private void sleep() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
