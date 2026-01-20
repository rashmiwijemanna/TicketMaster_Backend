package edu.rashmi.service;

import edu.rashmi.model.dto.PricingResponse;
import edu.rashmi.model.entity.Event;
import edu.rashmi.model.entity.User;
import edu.rashmi.model.entity.UserTier;
import edu.rashmi.strategy.PricingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PriceService {
    private final Map<UserTier, PricingStrategy> strategies;
    public PriceService(List<PricingStrategy> strategyList){
        this.strategies=strategyList.stream()
                .collect(Collectors.toMap(PricingStrategy::getTier, Function.identity()));

    }
    public PricingResponse calculatePrice(User user, Event event){
        return strategies.get(user.getTier()).calculate(event, user);
    }

}
