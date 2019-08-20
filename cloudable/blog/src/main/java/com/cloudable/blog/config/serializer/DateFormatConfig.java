package com.cloudable.blog.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>
 * 全局日期格式化
 * </p>
 *
 * @author CloudSen
 */
@JsonComponent
public class DateFormatConfig {

    /**
     * <p>
     * 序列化时，对Date日期格式化
     * </p>
     */
    public static class DateJsonSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
    }

    /**
     * <p>
     * 序列化时，对LocalDateTime日期格式化
     * </p>
     */
    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }
    }
}
