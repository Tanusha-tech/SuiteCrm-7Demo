package dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class Contact {

    @Builder.Default
    private String firstName = "";
    @Builder.Default
    private String lastName = "test";
    @Builder.Default
    private String officePhone = "";
    @Builder.Default
    private String mobile = "";
    @Builder.Default
    private String jobTitle = "";
    @Builder.Default
    private String department = "";
    @Builder.Default
    private String accountName = "";
    @Builder.Default
    private String fax = "";
    @Builder.Default
    private String address = "";
    @Builder.Default
    private String city = "";
    @Builder.Default
    private String region = "";
    @Builder.Default
    private String postalCode = "";
    @Builder.Default
    private String country = "";
    @Builder.Default
    private String description = "";
    @Builder.Default
    private String leadSource = "";
}