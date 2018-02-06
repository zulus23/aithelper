package ru.zhukov.ait.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhukov.ait.domain.Order;
import ru.zhukov.ait.domain.TypeOrder;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository  extends JpaRepository<Order,String>{

    List<Order> findByTypeOrderAndDateBeginBetween(TypeOrder type, LocalDate dateBegin, LocalDate dateEnd);

}
