package fu.training.FrameMates_BE.slotbooking;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SlotBookingMapper {
    SlotBookingModel toModels(SlotBooking entity);

    @Mapping(source = "slotId", target = "slotId", ignore = true)
    SlotBooking fromCreateModelToEntity(SlotBookingModel model);

    SlotBookingModel toModel(SlotBooking slotBooking);
}
