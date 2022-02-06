package invest.service.websocket.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import invest.service.dto.representation.QuickAnalysisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.input.topic}")
    private String analyticsTopic;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public void sendToKafka(QuickAnalysisDto obj) {
        String json = "";
        try {
            json = ow.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(analyticsTopic, json);
    }

}
