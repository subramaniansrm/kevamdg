package com.kevamdg.sr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kevamdg.sr.entity.StateEntity;

/**
 * State Repository
 * 
 * @author raathikaabm
 *
 */

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Integer> {

}
