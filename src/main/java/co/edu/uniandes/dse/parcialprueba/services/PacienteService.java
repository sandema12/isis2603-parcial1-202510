package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity paciente) throws Exception {
        log.info("Creando un nuevo paciente");

        if (paciente.getNumero().length() != 11 | (!paciente.getNumero().startsWith("311")) | (!paciente.getNumero().startsWith("601"))) {
            throw new Exception("El número de teléfono debe tener 11 dígitos");
        }

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void asociarAcudiente(Long idPaciente, Long idAcudiente) {
        log.info("Asociando acudiente a paciente");

        PacienteEntity paciente = pacienteRepository.findById(idPaciente).get();
        if (paciente.getAcudiente() != null) {
            throw new IllegalArgumentException("El paciente ya tiene un acudiente asignado");
        }

        PacienteEntity acudiente = pacienteRepository.findById(idAcudiente).get();
        if (acudiente.getAcudiente() != null) {
            throw new IllegalArgumentException("El acudiente no puede tener un acudiente asignado");
        }

        if (acudiente.getHistoriasClinicas().size() == 0) {
            throw new IllegalArgumentException("El acudiente debe tener al menos una historia clínica asociadas");
        }

        paciente.setAcudiente(acudiente);
        pacienteRepository.save(paciente);
    }
    

}
