package com.project.ResturantApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ResturantApp.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
