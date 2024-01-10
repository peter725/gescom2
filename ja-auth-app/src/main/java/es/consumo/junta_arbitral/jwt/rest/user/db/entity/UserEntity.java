package es.consumo.junta_arbitral.jwt.rest.user.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(name = "login_id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
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
    @Column(name = "last_surname",  length = 20)
    private String lastSurname;
    @Basic
    @Column(name = "dni", nullable = false, length = 20)
    private String dni;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id", insertable = false, updatable = false)
    private LoginEntity login;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(createAt, that.createAt)) return false;
        if (!Objects.equals(updateAt, that.updateAt)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(surname, that.surname)) return false;
        if (!Objects.equals(lastSurname, that.lastSurname)) return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (lastSurname != null ? lastSurname.hashCode() : 0);
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        return result;
    }
}
