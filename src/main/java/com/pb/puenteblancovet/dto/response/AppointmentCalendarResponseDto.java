package com.pb.puenteblancovet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentCalendarResponseDto {
    private String title;
    private String start; // ISO format: 2025-05-22T10:00
}
