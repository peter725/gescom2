package es.dgc.gesco.model.modules.authorityOEU.db.entity;


import es.dgc.gesco.model.commom.db.entity.AuditedBaseEntity;
import es.dgc.gesco.model.modules.email.db.entity.Email;
import es.dgc.gesco.model.modules.legislation.db.entity.Legislation;
import es.dgc.gesco.model.modules.phone.db.entity.Phone;
import es.dgc.gesco.model.modules.publicLaboratories.db.entity.PublicLaboratories;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import java.util.List;

import static es.dgc.gesco.model.util.ConstanteBD.*;

/**
 * @author serikat
 */
@Entity
@Table(name = TABLE_AUTHORITY_OEU)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AuthorityOEU extends AuditedBaseEntity {

    //genera el campo id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_AUTHORITY_OEU)
    @SequenceGenerator(
            name = SEQ_AUTHORITY_OEU,
            sequenceName = SEQ_AUTHORITY_OEU,
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    //genera el campo name
    @Column(name = "NAME" )
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 100)
    private String name;

    //genera el campo minitry de tipo string notnull maximo 100
    @Column(name = "MINISTRY" )
    @NotNull(message = "Debes especificar el ministerio")
    @Size(min = 1, max = 100)
    private String ministry;

    //genera el campo mintryAcronym de tipo string notnull maximo 20
    @Column(name = "MINISTRY_ACRONYM" )
    @Size(min = 1, max = 20)
    private String ministryAcronym;

    //genera el campo generalDirection de tipo string notnull maximo 100
    @Column(name = "GENERAL_DIRECTION" )
    @NotNull(message = "Debes especificar la direccion general")
    @Size(min = 1, max = 100)
    private String generalDirection;

    //genera el campo deputyDirectorate de tipo string notnull maximo 200
    @Column(name = "DEPUTY_DIRECTORATE" )
    @NotNull(message = "Debes especificar la subdireccion")
    @Size(min = 1, max = 100)
    private String deputyDirectorate;

    //genera el campo legislation de tipo string notnull maximo 200
    @ManyToOne
    @JoinColumn(name = "LEGISLATION_ID" )
    @NotNull(message = "Debes especificar la legislacion")
    private Legislation legislation;

    //genera el campo areaResponsability de tipo string notnull maximo 10
    @Column(name = "AREA_RESPONSABILITY" )
    @NotNull(message = "Debes especificar el area de responsabilidad")
    @Size(min = 1, max = 50)
    private String areaResponsability;

    //genera el campo commentsAreaResponsability de tipo string notnull maximo 200
    @Column(name = "COMMENTS_AREA_RESPONSABILITY" )
    @Size(min = 1, max = 200)
    private String commentsAreaResponsability;

    //genera el campo committee de tipo string notnull maximo 20
    @Column(name = "COMMITTEE" )
    @Size(min = 1, max = 20)
    private String committee;

    //genera el campo postalAddress de tipo string notnull maximo 100
    @Column(name = "POSTAL_ADDRESS" )
    @NotNull(message = "Debes especificar la direccion postal")
    @Size(min = 1, max = 100)
    private String postalAddress;

    //genera el campo web de tipo string notnull maximo 200
    @Column(name = "WEB" )
    @Size(min = 1, max = 200)
    private String web;

    //genera el campo legalReferFunctAuthority de tipo string notnull maximo 200
    @Column(name = "LEGAL_REFER_FUNCT_AUTHORITY" )
    @NotNull(message = "Debes especificar la Referencia legal a las funciones de la autoridad")
    @Size(min = 1, max = 200)
    private String legalReferFunctAuthority;

    //genera el campo icsms tipo Integer notnull
    @Column(name = "ICSMS" )
    @NotNull(message = "Debes especificar el ICSMS")
    private Integer icsms;

    //genera el campo typeAuthority de tipo string notnull maximo 15
    @Column(name = "TYPE_AUTHORITY" )
    @NotNull(message = "Debes especificar el tipo de autoridad")
    @Size(min = 1, max = 15)
    private String typeAuthority;


    //genera el campo publicLaboratories tipo Integer
    @ManyToOne
    @JoinColumn(name = "PUBLIC_LAB_ID" )
    private PublicLaboratories publicLaboratories;

    //genera el campo generalComments de tipo string notnull maximo 400
    @Column(name = "GENERAL_COMMENTS" )
    @Size(min = 1, max = 400)
    private String generalComments;

    @Transient
    @ToString.Exclude
    private List<Email> emails;

    @Transient
    @ToString.Exclude
    private List<Phone> phones;






}
