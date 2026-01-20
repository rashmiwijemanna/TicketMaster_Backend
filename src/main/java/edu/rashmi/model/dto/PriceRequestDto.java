package edu.rashmi.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PriceRequestDto {
    private long userId;
    private Long eventId;
}
