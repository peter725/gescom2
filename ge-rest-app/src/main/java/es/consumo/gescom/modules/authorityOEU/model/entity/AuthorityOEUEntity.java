package es.consumo.gescom.modules.authorityOEU.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.email.model.entity.EmailEntity;
import es.consumo.gescom.modules.legislation.model.entity.LegislationEntity;
import es.consumo.gescom.modules.phone.model.entity.PhoneEntity;
import es.consumo.gescom.modules.publicLaboratories.model.entity.PublicLaboratoriesEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "authority_oeu")
@Getter
@Setter
public class AuthorityOEUEntity extends SimpleEntity {

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
    private LegislationEntity legislation;

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
    private PublicLaboratoriesEntity publicLaboratories;

    //genera el campo generalComments de tipo string notnull maximo 400
    @Column(name = "GENERAL_COMMENTS" )
    @Size(min = 1, max = 400)
    private String generalComments;

    @Transient
    @ToString.Exclude
    private List<EmailEntity> emails;

    @Transient
    @ToString.Exclude
    private List<PhoneEntity> phones;

    @JsonIgnore
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @CreatedBy
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "UPDATED_BY", nullable = true)
    private Long updatedBy;


    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            AuthorityOEUEntity that = (AuthorityOEUEntity) o;
            return Objects.equals(getId(), that.getId()) && Objects.equals(name, that.name);
        }

    @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getId());
        }

        public interface SimpleProjection {

            Long getId();
            String getName();

        }
}
