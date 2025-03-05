package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public HistoriaClinicaEntity createHistoriaClinicaAPaciente(HistoriaClinicaEntity historiaClinica, Long idPaciente) {
        log.info("Creando una nueva historia clínica");
        
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).get();
        if (paciente.getAcudiente() != null) {
            String diagnostico = "HistoriaCompartida- " + historiaClinica.getDiagnostico();
            historiaClinica.setDiagnostico(diagnostico);
        }

        historiaClinica.setPaciente(paciente);
        paciente.getHistoriasClinicas().add(historiaClinica);
        pacienteRepository.save(paciente);

        return historiaClinicaRepository.save(historiaClinica);
    }

}
