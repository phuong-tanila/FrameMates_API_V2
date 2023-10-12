package fu.training.FrameMates_BE.orderdetail;

import fu.training.FrameMates_BE.order.Order;
import fu.training.FrameMates_BE.order.OrderStatus;
import fu.training.FrameMates_BE.share.helpers.EnumConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

    OrderDetail toEntity(OrderDetailModel orderDetailModel);
    OrderDetailModel toModel(OrderDetail orderDetail);
}
