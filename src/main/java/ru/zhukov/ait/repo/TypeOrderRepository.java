package ru.zhukov.ait.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhukov.ait.domain.TypeOrder;

@Repository
public interface TypeOrderRepository extends JpaRepository<TypeOrder,String> {
}
