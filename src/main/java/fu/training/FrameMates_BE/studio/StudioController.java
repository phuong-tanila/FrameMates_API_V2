package fu.training.FrameMates_BE.studio;

import fu.training.FrameMates_BE.account.Account;
import fu.training.FrameMates_BE.share.exceptions.RecordNotFoundException;
import fu.training.FrameMates_BE.share.helpers.PaginationHelper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/studios")
@CrossOrigin
public class StudioController {
    @Autowired
    private StudioService studioService;
//    @PreAuthorize("hasRole('ROLE_CUS')")
    @PostMapping
    public ResponseEntity createStudio(
            @RequestBody  StudioModel model
//            Authentication authentication
    ) throws URISyntaxException {
        int studioId = studioService.createStudio(model);
        return ResponseEntity.created(new URI("/api/studios/" + studioId)).build();
    }

    @GetMapping
    public ResponseEntity<List<StudioModel>> getAllStudio(){
        return ResponseEntity.ok(studioService.getAllStudioNotDeleted());
    }
    @GetMapping("{id}")
    public ResponseEntity<StudioModel> getAllStudio(
            @PathVariable int id
    ){
        return ResponseEntity.ok(studioService.getAllStudioNotDeleted().stream().filter(s -> s.getStudioId() == id).findFirst().orElseThrow(() -> new RecordNotFoundException("Can find studio with id: " + id)));
    }
}
