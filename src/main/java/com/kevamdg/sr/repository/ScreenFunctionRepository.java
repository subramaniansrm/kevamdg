package com.kevamdg.sr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.entity.ScreenFunction;

@Repository
public interface ScreenFunctionRepository extends JpaRepository<ScreenFunction, Integer> {
	
}

