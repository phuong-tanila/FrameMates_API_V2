package fu.training.FrameMates_BE.studio;

import fu.training.FrameMates_BE.share.helpers.PaginationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface StudioService {
    long count();

    void updateStudioStatus(int id, int status, Authentication authentication) throws IllegalAccessException;
    StudioModel createStudio(StudioModel studioModel);
    StudioModel getById(Integer id);
    List<StudioModel> getByName(String name);
    StudioModel update(Integer id, StudioModel studioModel);

    PaginationResponse<StudioModel> searchByStatus(int status, String searchKey, Pageable pageable);
    StudioModel findByCurrentOwner(Authentication authentication);

}
