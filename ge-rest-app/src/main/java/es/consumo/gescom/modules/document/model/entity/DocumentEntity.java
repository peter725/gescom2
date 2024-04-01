package es.consumo.gescom.modules.document.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.consumo.gescom.commons.db.entity.SimpleEntity;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "document")
public class DocumentEntity extends SimpleEntity {

    @Basic
    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @JsonIgnore
    @Basic
    @Column(name = "sign_path", nullable = true, length = 100)
    private String signPath;

    @Basic
    @Column(name = "extension", nullable = true, length = 100)
    private String extension;

    @JsonIgnore
    @Basic
    @Column(name = "path", nullable = true, length = 500)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentTypeEntity documentType;

    @JsonIgnore
    @Basic
    @Column(name = "campaign_id", nullable = true)
    private Long campaignId;

    @Transient
    private String base64;

    @Transient
    @JsonIgnore
    private String sign;

    @Column(name = "ID_STATE", nullable = false)
    @Setter
    private Integer state = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCreateAt(), that.getCreateAt()) && Objects.equals(getName(), that.getName())
                && Objects.equals(getPath(), that.getPath()) && Objects.equals(getDocumentType(), that.getDocumentType())
                && Objects.equals(getCampaignId(), that.getCampaignId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCreateAt(), getName(), getPath(), getDocumentType(), getCampaignId());
    }


}
