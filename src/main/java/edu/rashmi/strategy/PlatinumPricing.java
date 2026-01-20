package edu.rashmi.strategy;

import edu.rashmi.model.dto.PricingResponse;
import edu.rashmi.model.entity.Event;
import edu.rashmi.model.entity.User;
import edu.rashmi.model.entity.UserTier;

public class PlatinumPricing implements PricingStrategy{
    @Override
    public PricingResponse calculate(Event event, User user) {
        PricingResponse response=new PricingResponse(event.getBasePrice(),"Platinum Standard");
        response.setPriorityAccess(true);
        return response;
    }

    @Override
    public UserTier getTier() {
        return UserTier.PLANTINUM;
    }
}
