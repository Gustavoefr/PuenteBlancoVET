// HistorialMedicoServiceImpl.java
package com.pb.puenteblancovet.services.impl;

import com.pb.puenteblancovet.dto.request.AttendAppointmentRequestDto;
import com.pb.puenteblancovet.dto.response.AppointmentDetailResponseDto;
import com.pb.puenteblancovet.entity.AtencionMedica;
import com.pb.puenteblancovet.entity.Cita;
import com.pb.puenteblancovet.repository.AtencionMedicaRepository;
import com.pb.puenteblancovet.repository.CitaRepository;
import com.pb.puenteblancovet.services.interfaces.VetClinicalRecordService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class VetClinicalRecordServiceImpl implements VetClinicalRecordService {

    private final CitaRepository citaRepository;
    private final AtencionMedicaRepository atencionMedicaRepository;

    @Override
    public AppointmentDetailResponseDto getAppointmentDetails(Long citaId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));

        return AppointmentDetailResponseDto.builder()
                .citaId(cita.getId())
                .hora(cita.getHora().toString())
                .fecha(cita.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .nombreCliente(cita.getUsuario().getNombres())
                .emailCliente(cita.getUsuario().getCorreo())
                .nombreMascota(cita.getPet().getName())
                .tipoMascota(cita.getPet().getType())
                .razaMascota(cita.getPet().getBreed())
                .edadMascota(cita.getPet().getAge())
                .servicio(cita.getServicio().getDescripcion())
                .build();
    }

    @Override
    public void saveMedicalAttention(Long citaId, AttendAppointmentRequestDto dto) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));

        AtencionMedica atencion = AtencionMedica.builder()
                .cita(cita)
                .observacionesClinicas(dto.getObservacionesClinicas())
                .diagnostico(dto.getDiagnostico())
                .tratamiento(dto.getTratamiento())
                .prescripcion(dto.getPrescripcion())
                .activo(true)
                .build();

        atencionMedicaRepository.save(atencion);

        cita.setEstado("COMPLETADA");
        citaRepository.save(cita);
    }
}
