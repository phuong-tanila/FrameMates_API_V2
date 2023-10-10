package fu.training.FrameMates_BE.share.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SoftDeleteEntity {
    @Column(name = "deleted", nullable = true)
    public Boolean deleted = false;
}
