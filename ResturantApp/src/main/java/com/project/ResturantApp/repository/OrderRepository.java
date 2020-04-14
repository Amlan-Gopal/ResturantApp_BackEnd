package com.project.ResturantApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ResturantApp.entity.CustomerOrder;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer>{

}
