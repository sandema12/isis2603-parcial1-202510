import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.uniandes.dse.parcialprueba.MainApplication;
import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;

import co.edu.uniandes.dse.parcialprueba.services.PacienteService;
import jakarta.transaction.Transactional;

@SpringBootTest(classes = MainApplication.class)
@Transactional

public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    public void createPacienteTest() throws Exception {
        PacienteEntity paciente = new PacienteEntity();
        paciente.setNombre("Santiago");
        paciente.setCorreo("sdm@algo.com");
        paciente.setNumero("31112345678");
        paciente.setAcudiente(null);
        paciente.setHistoriasClinicas(null);
        pacienteService.createPaciente(paciente);

        PacienteEntity pacienteEncontrado = pacienteRepository.findById(paciente.getId()).get();
        assertEquals(paciente, pacienteEncontrado);

    }

    @Test
    public void createPacienteTestInvalido() {
        PacienteEntity paciente = new PacienteEntity();
        paciente.setNombre("Santiago");
        paciente.setCorreo("sdm@algo.com");
        paciente.setNumero("3111");
        paciente.setAcudiente(null);
        paciente.setHistoriasClinicas(null);

    }

    @Test
    public void asociarAcudienteTest() {
        PacienteEntity paciente = new PacienteEntity();
        paciente.setNombre("Santiago");
        paciente.setCorreo("sdm@algo.com");
        paciente.setNumero("31112345678");
        paciente.setAcudiente(null);
        paciente.setHistoriasClinicas(null);

        

        PacienteEntity acudiente = new PacienteEntity();
        acudiente.setNombre("Juan");
        acudiente.setCorreo("juan@algo.com");
        acudiente.setNumero("31112346579");
        acudiente.setAcudiente(null);
        acudiente.setHistoriasClinicas(null);


        HistoriaClinicaEntity historiaClinica = new HistoriaClinicaEntity();
        historiaClinica.setDiagnostico("Gripe");
        historiaClinica.setPaciente(null);
        historiaClinica.setTratamiento("Tomar agua");
        historiaClinica.setFechaCreacion(new Date(2021, 10, 10));

        acudiente.getHistoriasClinicas().add(historiaClinica);
        historiaClinica.setPaciente(acudiente);

        acudiente.setAcudiente(acudiente);
        

        pacienteService.asociarAcudiente(paciente.getId(), acudiente.getId());
        

    
    }
}
