package ru.zhukov.ait.dao;

import ru.zhukov.ait.domain.Enterprise;
import ru.zhukov.ait.domain.TypeOrder;

import java.util.Arrays;
import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public List<Enterprise> listEnterprise() {
        return Arrays.asList(Enterprise.values());
    }

    @Override
    public List<TypeOrder> listTypeOrder() {
        return null;
    }
}
