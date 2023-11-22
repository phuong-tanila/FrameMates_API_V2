package fu.training.FrameMates_BE.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByAccount_AccountId(int accountId);
    List<Order> findAllByStudio_StudioId(int studioId);

//    List<Order> findAll();

}
