package com.kevamdg.sr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.entity.ScreenField;

@Repository
public interface ScreenFieldRepositoy extends JpaRepository<ScreenField, Integer> {
}
