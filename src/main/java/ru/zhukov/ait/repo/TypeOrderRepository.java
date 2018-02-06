package ru.zhukov.ait.repo;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhukov.ait.domain.TypeOrder;



public interface TypeOrderRepository extends JpaRepository<TypeOrder,String> {
    TypeOrder findByName(String name);
}
