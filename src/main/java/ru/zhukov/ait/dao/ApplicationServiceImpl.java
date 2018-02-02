package ru.zhukov.ait.dao;

import org.springframework.stereotype.Service;
import ru.zhukov.ait.domain.Enterprise;
import ru.zhukov.ait.domain.TypeOrder;



import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public List<Enterprise> listEnterprise() {
        return Arrays.asList(Enterprise.values());
    }

    @Override
    public ApplicationDataService createDataService(Enterprise enterprise) {

        switch (enterprise){
            case GOTEK:{

            }
            case POLYPACK:{

            }
            default:{

            }

        }


        throw new UnsupportedOperationException("Method doesn't implement yet");
    }


}
