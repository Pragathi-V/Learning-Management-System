package com.capg.batchservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.batchservice.entity.Batch;

import jakarta.transaction.Transactional;

public interface BatchRepository extends JpaRepository<Batch, Long>{

	@Transactional
	Batch findByBatchCode(String batchCode);
	
	@Transactional
	void deleteByBatchCode(String batchCode);
}
