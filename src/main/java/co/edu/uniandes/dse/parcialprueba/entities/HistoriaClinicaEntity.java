package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.Date;

import co.edu.uniandes.dse.parcialprueba.podam.DateStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@Entity
public class HistoriaClinicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnostico;
    private String tratamiento;
    
    @Temporal(TemporalType.DATE)
	@PodamStrategyValue(DateStrategy.class)
	private Date fechaCreacion;

    @PodamExclude
    @OneToOne
    private PacienteEntity paciente;
    

}
