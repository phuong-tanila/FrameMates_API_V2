package fu.training.FrameMates_BE.mediafile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFile, Integer> {
    List<MediaFile> findAllByStudio_StudioId(int studioId);
}
