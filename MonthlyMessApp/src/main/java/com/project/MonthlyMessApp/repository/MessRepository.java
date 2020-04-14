package com.project.MonthlyMessApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.MonthlyMessApp.entity.MessEntity;

@Repository
public interface MessRepository extends JpaRepository<MessEntity, Integer>{

	@Query("select mess from MessEntity mess where mess.customer.customerName = ?1")
	MessEntity findMessDairyByCustomerName(String customerName);
}
