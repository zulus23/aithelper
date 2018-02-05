package ru.zhukov.ait.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.zhukov.ait.config.DatabaseConfig;
import ru.zhukov.ait.domain.Enterprise;
import ru.zhukov.ait.domain.TypeOrder;



import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final  Map<Enterprise,ApplicationDataService> dataServiceMap = new WeakHashMap<>();
    private AnnotationConfigApplicationContext ctx;



    @Override
    public List<Enterprise> listEnterprise() {
        return Arrays.asList(Enterprise.values());
    }

    @Override
    public CompletableFuture<ApplicationDataService> createDataService(Enterprise enterprise) {

        switch (enterprise){
            case GOTEK:{


                return CompletableFuture.supplyAsync(()->dataServiceMap.computeIfAbsent(enterprise,(e)-> {
                    ctx = new AnnotationConfigApplicationContext();
                    ctx.registerBean("enterprise",Enterprise.class,() -> Enterprise.GOTEK);
                    ctx.registerBean(DatabaseConfig.class);
                    ctx.refresh();
                    return ctx.getBean(ApplicationDataService.class);
                }));
            }
            case POLYPACK:{

                return CompletableFuture.supplyAsync(()->dataServiceMap.computeIfAbsent(enterprise,(e)-> {
                    ctx = new AnnotationConfigApplicationContext();
                    ctx.registerBean("enterprise",Enterprise.class,() -> Enterprise.POLYPACK);
                    ctx.registerBean(DatabaseConfig.class);
                    ctx.refresh();
                    return  ctx.getBean(ApplicationDataService.class);
                }));
            }
            default:{

            }

        }


        throw new UnsupportedOperationException("Method doesn't implement yet");
    }



}
