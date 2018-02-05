package ru.zhukov.ait.dao;

import org.springframework.context.ApplicationContextAware;
import ru.zhukov.ait.domain.Enterprise;
import ru.zhukov.ait.domain.TypeOrder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ApplicationService{

    List<Enterprise> listEnterprise();


    CompletableFuture<ApplicationDataService> createDataService(Enterprise newEnterprise);
}
