package com.github.wenqiglantz.service.customerservice.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"customerId", "firstName", "lastName"})
public class CustomerVO {

    //@Schema(required = false, description = "The ID for the customer. Should be a Unique ID with max 36 Characters. If not provided, the system will assign one.")
    private UUID customerId;

    //@Schema(required = true, description = "The first name. The length cannot exceed 50.")
    private String firstName;

    //@Schema(required = true, description = "The last name. The length cannot exceed 50.")
    private String lastName;
}
