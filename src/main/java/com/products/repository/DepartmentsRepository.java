package com.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.model.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {
}
