package fu.training.FrameMates_BE.mediafile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MediaFileService {
    @Autowired
    private MediaFileMapper mediaFileMapper;

    @Autowired
    private MediaFileRepository mediaFileRepository;
//    List<MediaFileModel> createMediaFile(List<MediaFileModel> mediaFileModels);

    public List<MediaFileModel> getMediaFilesByStudioId(int studioId){
        return mediaFileRepository
                .findAllByStudio_StudioId(studioId)
                .stream().map(mediaFileMapper::toModel)
                .toList();
    }
}
