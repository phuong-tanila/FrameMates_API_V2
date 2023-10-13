package fu.training.FrameMates_BE.slotbooking;

import fu.training.FrameMates_BE.order.OrderStatus;
import fu.training.FrameMates_BE.share.helpers.EnumConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SlotBookingMapper {
    SlotBookingModel toModels(SlotBooking entity);

    @Mapping(source = "slotId", target = "slotId", ignore = true)
    SlotBooking fromCreateModelToEntity(SlotBookingModel model);

    @Mapping(source = "bookingStatus", target = "bookingStatus", qualifiedByName = "statusEnumToString")
    SlotBookingModel toModel(SlotBooking slotBooking);

    @Named("statusEnumToString")
    default String statusEnumToString(int status) {
        return EnumConverter.convertEnumValueToString(status, SlotBookingStatus.class);
    }
}
