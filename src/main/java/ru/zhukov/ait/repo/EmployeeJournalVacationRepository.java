package ru.zhukov.ait.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhukov.ait.domain.EmployeeJournalVacation;

public interface EmployeeJournalVacationRepository extends JpaRepository<EmployeeJournalVacation,String> {
}
