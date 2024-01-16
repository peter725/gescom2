package es.consumo.gescom.modules.users.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import es.consumo.gescom.commons.db.entity.SimpleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends SimpleEntity{

    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = true)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Basic
    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 20)
    private String surname;
    @Basic
    @Column(name = "last_surname", nullable = true, length = 20)
    private String lastSurname;
    @Basic
    @Column(name = "dni", nullable = false, length = 20)
    private String dni;
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id", insertable = true)
    private LoginEntity login;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!Objects.equals(createAt, that.createAt)) return false;
        if (!Objects.equals(updateAt, that.updateAt)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(surname, that.surname)) return false;
        if (!Objects.equals(lastSurname, that.lastSurname)) return false;
        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(dni, that.dni);
    }

    public interface SimpleProjection {
        Long getId();

        String getName();

        String getSurname();

        String getDni();

        String getEmail();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime getCreateAt();
    }
}
