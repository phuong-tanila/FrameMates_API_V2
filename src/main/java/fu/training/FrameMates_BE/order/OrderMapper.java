package fu.training.FrameMates_BE.order;


import fu.training.FrameMates_BE.share.helpers.EnumConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {

    @Mapping(source = "orderId", target = "orderId", ignore = true)
    Order fromCreateModelToEntity(OrderModel model);
    @Mapping(source = "status", target = "status", qualifiedByName = "statusEnumToString")
    @Mapping(source = "account.password", target = "account.password", ignore = true)
    OrderModel toModel(Order order);

    @Mapping(source = "status", target = "status", qualifiedByName = "statusEnumToString")
    @Mapping(source = "account.password", target = "account.password", ignore = true)
    @Mapping(source = "studio.slotBookings", target = "studio.slotBookings", ignore = true)
    OrderModelIncludeStudio toModelIncludeStudio(Order order);

    @Named("statusEnumToString")
    default String statusEnumToString(int status) {
        return EnumConverter.convertEnumValueToString(status, OrderStatus.class);
    }
}
