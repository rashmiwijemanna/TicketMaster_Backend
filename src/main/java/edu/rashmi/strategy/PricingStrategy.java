package edu.rashmi.strategy;

import edu.rashmi.model.dto.PricingResponse;
import edu.rashmi.model.entity.Event;
import edu.rashmi.model.entity.User;
import edu.rashmi.model.entity.UserTier;

public interface PricingStrategy {
    PricingResponse calculate(Event event, User user);
    UserTier getTier();

}
