package com.pb.puenteblancovet.services.impl;

import com.pb.puenteblancovet.dto.response.VetClinicalRecordResponseDto;
import com.pb.puenteblancovet.dto.response.VetPatientListResponseDto;
import com.pb.puenteblancovet.entity.AtencionMedica;
import com.pb.puenteblancovet.repository.AtencionMedicaRepository;
import com.pb.puenteblancovet.services.interfaces.VetPatientHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetPatientHistoryServiceImpl implements VetPatientHistoryService {

    private final AtencionMedicaRepository atencionMedicaRepository;

    @Override
    public List<VetPatientListResponseDto> getAllPatientsWithHistory() {
        List<AtencionMedica> records = atencionMedicaRepository.findAllWithPetAndUser();

        return records.stream()
                .map(att -> new VetPatientListResponseDto(
                        att.getCita().getPet().getId(),
                        att.getCita().getPet().getName(),
                        att.getCita().getPet().getBreed(),
                        att.getCita().getPet().getType(),
                        att.getCita().getUsuario().getNombres()
                ))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<VetClinicalRecordResponseDto> getClinicalHistoryByPet(Long petId) {
        return atencionMedicaRepository.findByPetId(petId).stream()
                .map(record -> VetClinicalRecordResponseDto.builder()
                        .fecha(record.getCita().getFecha())
                        .servicio(record.getCita().getServicio().getDescripcion())
                        .diagnostico(record.getDiagnostico())
                        .tratamiento(record.getTratamiento())
                        .observaciones(record.getObservacionesClinicas())
                        .build())
                .collect(Collectors.toList());
    }
}

