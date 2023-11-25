package fu.training.FrameMates_BE.studio;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.account.AccountService;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import fu.training.FrameMates_BE.share.helpers.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudioService {
//    long count();
//
//    void updateStudioStatus(int id, int status, Authentication authentication) throws IllegalAccessException;
//    StudioModel createStudio(StudioModel studioModel);
//    StudioModel getById(Integer id);
//    List<StudioModel> getByName(String name);
//    StudioModel update(Integer id, StudioModel studioModel);
//
//    PaginationResponse<StudioModel> searchByStatus(int status, String searchKey, Pageable pageable);
//    StudioModel findByCurrentOwner(Authentication authentication);
    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StudioMapper studioMapper;
    public Studio getById(int studioId){
        return studioRepository.findByStudioIdAndDeleted(studioId, false).orElseThrow(() -> new RecordNotFoundException("Studio id: " + studioId + "not found"));
    }

    public int createStudio(StudioModel model) {
        var currentUser = accountService.createAccount(model.getOwner());
        currentUser.setRole("OWNER");
        var studioEntity = studioMapper.fromCreateModelToEntity(model);
        studioEntity.setOwner(currentUser);
        studioEntity.setTotalRating((float) 0);
        studioEntity.setDeleted(false);
        return studioRepository.save(studioEntity).getStudioId();
    }

    public List<StudioModel> getAll() {
        var list = studioRepository.findAll();
        return list.stream().map(studioMapper::toModel).toList();
    }

    public List<StudioModel> getAllStudioNotDeleted() {
        var list = studioRepository.getAllByDeleted(false);
        list.forEach(s -> s.setSlotBookings(null));
        return list.stream().map(studioMapper::toModel).toList();
    }
}
