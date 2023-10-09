package fu.training.FrameMates_BE.share.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class SoftDeleteEntity {
    @Column(name = "deleted")
    public boolean deleted;
}
