package com.springbootschmgtsysapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootschmgtsysapi.entity.SchoolProfile;

public interface SchoolRepository  extends JpaRepository<SchoolProfile, Integer>{

}
