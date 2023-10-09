package fu.training.FrameMates_BE.mediafile;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MediaFileMapper {
    MediaFile toEntity (MediaFileModel model);
    MediaFileModel toModel (MediaFile entity);
    List<MediaFile> toEntities (List<MediaFileModel> models);
    List<MediaFileModel> toModels (List<MediaFile> entities);
}
