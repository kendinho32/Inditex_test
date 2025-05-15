package com.inditex.inditex_test.modules.price.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("prices")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder({"brand_id", "name", "price_list", "product_id", "priority", "price", "curr", "start_date", "end_date"})
public class PriceEntity implements Comparable {

  @Id
  @Column("id")
  @JsonProperty("id")
  private Long id;

  @Column("brand_id")
  @JsonProperty("brand_id")
  private Long brandId;

  @JsonProperty("name")
  @Column("name")
  private String name;

  @JsonProperty("price_list")
  @Column("price_list")
  private Long priceList;

  @JsonProperty("product_id")
  @Column("product_id")
  private Long productId;

  @Column("priority")
  @JsonProperty("priority")
  private Long priority;

  @Column("price")
  @JsonProperty("price")
  private Double price;

  @Column("curr")
  @JsonProperty("curr")
  private String curr;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column("created_at")
  private LocalDateTime createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column("start_date")
  private LocalDateTime startDate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column("end_date")
  private LocalDateTime endDate;

  @Override
  public int compareTo(Object o) {
    PriceEntity campaign = (PriceEntity) o;
    return this.brandId.compareTo(campaign.brandId);
  }
}