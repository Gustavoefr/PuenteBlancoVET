package com.pb.puenteblancovet.services.interfaces;

import java.time.LocalDate;
import java.util.List;

public interface HorarioService {
    List<String> getAvailableTimeSlots(Long vetId, LocalDate fecha);
}
