package com.pb.puenteblancovet.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardClientResponseDto {
    private String fullName;
    private String calendarPath;
    private String paymentPath;
    private String reportPath;
}
