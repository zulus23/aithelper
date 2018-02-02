package ru.zhukov.ait.dao;

import org.springframework.stereotype.Service;
import ru.zhukov.ait.domain.Enterprise;
import ru.zhukov.ait.domain.TypeOrder;



import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final  Map<Enterprise,ApplicationDataService> dataServiceMap = new WeakHashMap<>();

    @Override
    public List<Enterprise> listEnterprise() {
        return Arrays.asList(Enterprise.values());
    }

    @Override
    public ApplicationDataService createDataService(Enterprise enterprise) {

        switch (enterprise){
            case GOTEK:{
                dataServiceMap.computeIfAbsent(enterprise,(e)-> null);
            }
            case POLYPACK:{

            }
            default:{

            }

        }


        throw new UnsupportedOperationException("Method doesn't implement yet");
    }


}
