package com.inditex.inditex_test.modules.price.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;

public record PriceRecord(Long brandId,
                          String name,
                          Long priceList,
                          Long productId,
                          Long priority,
                          Double price,
                          String curr,
                          @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                          @JsonSerialize(using = LocalDateTimeSerializer.class)
                          @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                          LocalDateTime startDate,
                          @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                          @JsonSerialize(using = LocalDateTimeSerializer.class)
                          @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                          LocalDateTime endDate) {
}
