package ru.zhukov.ait.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import ru.zhukov.ait.domain.Order;
import ru.zhukov.ait.domain.TypeOrder;

import java.time.LocalDate;
import java.util.List;

public interface ApplicationDataService  {

    List<TypeOrder> listTypeOrder();

    List<Order> listOrder();
    List<Order> listOrderByTypeAndDateBeginBetween(TypeOrder order, LocalDate dateBegin, LocalDate dateEnd);

    Order changeMarkCalculate(Order order);

}
