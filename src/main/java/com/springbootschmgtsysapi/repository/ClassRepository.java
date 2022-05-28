package com.springbootschmgtsysapi.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootschmgtsysapi.entity.Classs;

public interface ClassRepository extends JpaRepository<Classs, Integer> {
	Optional<Classs> findByClassName(String className);
	Optional<Classs> findByClassNameAndIdNot(String className, int id);
}
