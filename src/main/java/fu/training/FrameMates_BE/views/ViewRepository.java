package fu.training.FrameMates_BE.views;

import fu.training.FrameMates_BE.studio.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View, Integer>  {
    
}
