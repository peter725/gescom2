package es.consumo.gescom.modules.arbitrationBoard.model.entity;

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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "arbitration_board")
public class ArbitrationBoardEntity extends SimpleEntity {

    @JsonIgnore
    @CreatedDate
    @Basic
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @JsonIgnore
    @LastModifiedDate
    @Basic
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @Basic
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Basic
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Basic
    @Column(name = "phone", length = 20)
    private String phone;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArbitrationBoardEntity that = (ArbitrationBoardEntity) o;
        return Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createAt, updateAt, name, email, phone);
    }

    public interface SimpleProjection {
        Long getId();

        String getName();

        String getEmail();

        String getPhone();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime getCreateAt();
    }
}
