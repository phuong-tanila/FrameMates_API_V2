package fu.training.FrameMates_BE.orderdetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {


    List<OrderDetail> findOrderDetailByRatingNotNullAndSlotBooking_Studio_StudioId(int studioId);
    Optional<OrderDetail> findByOrderDetailIdAndAndOrder_Account_AccountId(int orderDetailId, Integer accountId);

    public List<OrderDetail> findAllByOrder_Account_AccountId(int accountId);

    public List<OrderDetail> findAllByOrder_Studio_StudioIdAndRatingNotNull(int studioId);
}
