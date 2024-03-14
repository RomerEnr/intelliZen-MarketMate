package me.romeralvarez.intellizenmarketmate;

import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
    value = {"me.romeralvarez"}

)
public class IntelliZenMarketMateApplication {

  public static void main(String[] args) {
    SpringApplication.run(IntelliZenMarketMateApplication.class, args);
  }

}
