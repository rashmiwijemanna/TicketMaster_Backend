package edu.rashmi.controller;

import edu.rashmi.exception.SeatLockedException;
import edu.rashmi.model.dto.PriceRequestDto;
import edu.rashmi.model.dto.PricingResponse;
import edu.rashmi.model.entity.*;
import edu.rashmi.repository.EvenRepository;
import edu.rashmi.repository.SeatRepository;
import edu.rashmi.repository.UserRepository;
import edu.rashmi.service.PriceService;
import edu.rashmi.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TicketController {
    @Autowired private SeatService seatService;
    @Autowired private PriceService priceService;
    @Autowired private UserRepository userRepo;
    @Autowired private EvenRepository evenRepository;
    @Autowired private SeatRepository seatRepository;

    @PostMapping("/seats/{seatId}/hold")
    public ResponseEntity<?> holdSeat(@PathVariable Long seatId, @RequestParam Long userId){
        try{
            seatService.holdSeat(seatId, userId);
            return ResponseEntity.ok("seat held successfully for 10 minutes");
            
        }catch (SeatLockedException e){
            return ResponseEntity.status(409).body(e.getMessage());
            
        }
    }

    @PostMapping("/calculate-price")
    public ResponseEntity<PricingResponse> calculatePrice(@RequestBody PriceRequestDto request){
        User user=userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = evenRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Even not found"));
        PricingResponse response = priceService.calculatePrice(user, event);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/setup")
    public String setupData(){
        User u=new User();
        u.setName("Test User");
        u.setEmail("test@test.com");
        u.setTier(UserTier.VIP);
        userRepo.save(u);

        Event e = new Event();
        e.setName("Concert");
        e.setBasePrice(100.0);
        e.setHighDemand(false);
      evenRepository.save(e);

        Seat s = new Seat();
        s.setSeatNumber("A1");
        s.setEvent(e);
        s.setStatus(SeatStatus.AVAILABLE);
        seatRepository.save(s);
        return "Dummy Data Created: User("+u.getId()+"), Event("+e.getId()+"), Seat("+s.getId()+")";


    }


}
