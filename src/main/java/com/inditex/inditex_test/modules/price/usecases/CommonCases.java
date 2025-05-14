package com.inditex.inditex_test.modules.price.usecases;

import com.inditex.inditex_test.modules.price.entities.PriceEntity;
import com.inditex.inditex_test.modules.price.records.PriceRecord;
import com.inditex.inditex_test.modules.price.records.PricesRecord;
import java.util.List;
import java.util.stream.Collectors;

public class CommonCases {

  static PricesRecord buildPriceRecord(List<PriceEntity> priceEntity) {
    return new PricesRecord(priceEntity.stream()
        .map(price -> new PriceRecord(price.getBrandId(),
            price.getName(),
            price.getPriceList(),
            price.getProductId(),
            price.getPriority(),
            price.getPrice(),
            price.getCurr(),
            price.getStartDate(),
            price.getEndDate()))
        .collect(Collectors.toList()));
  }
}
