package edu.rashmi.strategy;

import edu.rashmi.model.dto.PricingResponse;
import edu.rashmi.model.entity.Event;
import edu.rashmi.model.entity.User;
import edu.rashmi.model.entity.UserTier;

public class RegularPricing implements PricingStrategy{
    @Override
    public PricingResponse calculate(Event event, User user) {
        return new PricingResponse(event.getBasePrice(), "Standard price");

    }

    @Override
    public UserTier getTier() {
        return UserTier.REGULAR;
    }
}
