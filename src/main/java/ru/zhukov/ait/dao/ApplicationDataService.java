package ru.zhukov.ait.dao;

import org.springframework.context.ApplicationContextAware;
import ru.zhukov.ait.domain.TypeOrder;

import java.util.List;

public interface ApplicationDataService  {

    List<TypeOrder> listTypeOrder();
}
