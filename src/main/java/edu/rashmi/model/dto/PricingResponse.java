package edu.rashmi.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
@Data
public class PricingResponse {
    private Double finalPrice;
    private String messages;
    private boolean priorityAccess;

    public PricingResponse(Double finalPrice, String messages){
        this.finalPrice=finalPrice;
        this.messages=messages;
        this.priorityAccess=false;
    }

}
