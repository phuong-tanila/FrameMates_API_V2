package fu.training.FrameMates_BE.share.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class ObjectMappingConfig {
//    @Bean
//    public ObjectMapper objectMapper() {
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        // Set date format
//        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
//
//        return mapper;
//    }
@Bean
public DateTimeFormatter dateTimeFormatter() {
    return DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
}
