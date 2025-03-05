package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private String correo;
    private String numero;

    @PodamExclude
    @OneToMany(mappedBy = "paciente")
    private List<HistoriaClinicaEntity> historiasClinicas;

    @PodamExclude
    @OneToOne(mappedBy = "paciente") //No obligatorio
    private PacienteEntity acudiente;
    

}
