package fu.training.FrameMates_BE.studio;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StudioMapper {

    @Mapping(source = "studioId", target = "studioId", ignore = true)
    Studio fromCreateModelToEntity(StudioModel model);

    StudioModel toModel(Studio studio);
}
