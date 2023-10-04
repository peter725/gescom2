package es.dgc.gesco.model;

import es.dgc.gesco.model.util.ConstanteBD;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = ConstanteBD.TABLA_USUARIO)
@SequenceGenerator(name=ConstanteBD.SEQ_USUARIO, sequenceName = ConstanteBD.SEQ_USUARIO, allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ConstanteBD.SEQ_USUARIO)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
}
