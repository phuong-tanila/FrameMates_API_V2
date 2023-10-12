package fu.training.FrameMates_BE.mediafile;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/media-files")
public class MediaFileController {

    @Autowired
    private MediaFileService mediaFileService;
    @GetMapping("{studioId}")
    public ResponseEntity<List<MediaFileModel>> getMediaFilesByStudioId(
        @PathVariable int studioId
    ){
        return ResponseEntity.ok(mediaFileService.getMediaFilesByStudioId(studioId));
    }
}
