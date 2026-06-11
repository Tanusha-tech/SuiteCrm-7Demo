package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Account {

   @Builder.Default
   private String name = "test";
   @Builder.Default
   private String numberPhone = "";
   @Builder.Default
   private String fax = "";
   @Builder.Default
   private String website = "";
   @Builder.Default
   private String street = "";
   @Builder.Default
   private String city = "";
   @Builder.Default
   private String region = "";
   @Builder.Default
   private String postal = "";
   @Builder.Default
   private String country = "";
   @Builder.Default
   private String type = "";
   @Builder.Default
   private String industry = "";
   @Builder.Default
   private String description = "";
}
