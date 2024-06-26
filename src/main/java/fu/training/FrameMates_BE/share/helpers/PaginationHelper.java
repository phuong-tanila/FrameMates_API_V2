package fu.training.FrameMates_BE.share.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PaginationHelper {
    public static Sort.Direction getSortDirection(String direction){
        return direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
    public static List<Sort.Order> getSortOrders(List<String> orders){
        List<Sort.Order> ordersList = new ArrayList<>();
        for (String sortOrder : orders) {
            System.out.println(sortOrder);
            String[] splitedCurrentOrder = sortOrder.split("_");
            ordersList.add(new Sort.Order(getSortDirection(splitedCurrentOrder[1]), splitedCurrentOrder[0]));
        }
        return ordersList;
    }
    public static List<String> convertArrayToListFromRequest(String... orders){
        return Arrays.stream(orders).filter(Predicate.not(String::isEmpty)).toList();
    }
    public static Pageable getPageable(int pageNo, int pageSize, String... orders){
        List<String> dynamicListOrder = convertArrayToListFromRequest(orders);
        return PageRequest.of(pageNo, pageSize, Sort.by(getSortOrders(dynamicListOrder)));
    }
}
