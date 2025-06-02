package com.puenteblanco.pb.controller.client;

import com.puenteblanco.pb.dto.view.VaccineDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VaccinationViewController {

    @GetMapping("/vaccination")
    public String showVaccinationPage(Model model) {
        List<VaccineDto> catVaccines = List.of(
            new VaccineDto("FVRCP (Rinotraqueítis viral felina, Calicivirus, Panleucopenia)", "6–8 weeks", "3–4 doses", "Cada 3–4 semanas hasta las 16 semanas, luego anualmente", "Vacuna esencial para todos los gatos"),
            new VaccineDto("Rabia", "12–16 weeks", "1 dose", "Anualmente o cada 3 años (según la vacuna)", "Requerida por ley en la mayoría de zonas"),
            new VaccineDto("FeLV (Virus de la leucemia felina)", "8–12 weeks", "2 doses", "Refuerzo al año, luego cada 2–3 años", "Recomendado para gatos que salen o en hogares con varios gatos"),
            new VaccineDto("FIV (Virus de inmunodeficiencia felina)", "8 weeks or older", "3 doses", "Serie inicial, luego según indicación del veterinario", "Opcional, consultar con el veterinario")
        );

        List<VaccineDto> dogVaccines = List.of(
            new VaccineDto("DHPP (Moquillo, Hepatitis, Parainfluenza, Parvovirus)", "6–8 semanas", "3–4 dosis", "Cada 3–4 semanas hasta las 16 semanas, luego anualmente o cada 3 años", "Vacuna esencial para todos los perros"),
            new VaccineDto("Rabia", "12–16 semanas", "1 dosis", "Anualmente o cada 3 años (según la vacuna)", "Requerida por ley en la mayoría de zonas"),
            new VaccineDto("Bordetella (Tos de las perreras)", "8 semanas", "1–2 dosis", "Cada 6–12 meses para perros en riesgo", "Recomendado para perros que van a guarderías, parques, etc."),
            new VaccineDto("Leptospirosis", "12 semanas", "2 dosis", "Anualmente", "Recomendado en zonas con alta prevalencia")
        );

        model.addAttribute("catVaccines", catVaccines);
        model.addAttribute("dogVaccines", dogVaccines);

        return "vaccination";
    }
}
