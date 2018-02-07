package ru.zhukov.ait.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.zhukov.ait.domain.Order;
import ru.zhukov.ait.domain.TypeOrder;
import ru.zhukov.ait.repo.OrderRepository;
import ru.zhukov.ait.repo.TypeOrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Repository
public class ApplicationDataServiceImpl implements ApplicationDataService {


    @Autowired
    private TypeOrderRepository typeOrderRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<TypeOrder> listTypeOrder() {

        return typeOrderRepository.findAll();
    }

    @Override
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> listOrderByTypeAndDateBeginBetween(TypeOrder order, LocalDate dateBegin, LocalDate dateEnd) {
        return orderRepository.findByTypeOrderAndDateBeginBetween(order,dateBegin,dateEnd);
    }

    @Override
    public Order changeMarkCalculate(Order order) {
        order.setStatusCalculate(false);
        return orderRepository.save(order);
    }


}
