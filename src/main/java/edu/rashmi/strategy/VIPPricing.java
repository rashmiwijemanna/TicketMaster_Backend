package edu.rashmi.strategy;

import edu.rashmi.model.dto.PricingResponse;
import edu.rashmi.model.entity.Event;
import edu.rashmi.model.entity.User;
import edu.rashmi.model.entity.UserTier;

public class VIPPricing implements PricingStrategy{
    @Override
    public PricingResponse calculate(Event event, User user) {
        if (event.isHighDemand()){
            return new PricingResponse(event.getBasePrice(), "VIP(High Demand - No Discount)");

        }
        return new PricingResponse(event.getBasePrice() * 0.90, "VIP 10% Discount Applied");
    }

    @Override
    public UserTier getTier() {
        return UserTier.VIP;
    }
}
