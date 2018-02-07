package ru.zhukov.ait.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.zhukov.ait.domain.Order;
import ru.zhukov.ait.domain.TypeOrder;
import ru.zhukov.ait.repo.EmployeeJournalVacationRepository;
import ru.zhukov.ait.repo.OrderRepository;
import ru.zhukov.ait.repo.TypeOrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Repository
public class ApplicationDataServiceImpl implements ApplicationDataService {


    @Autowired
    private TypeOrderRepository typeOrderRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmployeeJournalVacationRepository vacationRepository;



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
    @Transactional(propagation = Propagation.REQUIRED)
    public Order changeMarkCalculate(Order order) {
        Order temp = order;
        if(order.getStatusCalculate()) {
            order.setStatusCalculate(false);
            Optional.ofNullable(order.getJournalVacation()).ifPresent(v -> {
                vacationRepository.delete(v);
                order.setJournalVacation(null);
            });

            temp = orderRepository.save(order);

        }
        return temp;

    }


}
