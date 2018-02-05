package ru.zhukov.ait.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.zhukov.ait.domain.TypeOrder;
import ru.zhukov.ait.repo.TypeOrderRepository;

import java.util.List;

@Service
public class ApplicationDataServiceImpl implements ApplicationDataService {


    @Autowired
    private TypeOrderRepository typeOrderRepository;

    @Override
    public List<TypeOrder> listTypeOrder() {

        return typeOrderRepository.findAll();
    }


}
