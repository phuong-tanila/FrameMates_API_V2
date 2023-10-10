package fu.training.FrameMates_BE.studio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {
    public List<Studio> getAllByDeleted(boolean deleted);

    public Optional<Studio> findByStudioIdAndDeleted(int studio, boolean deleted);
}
