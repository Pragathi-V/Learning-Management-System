package com.capg.batchservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.batchservice.dto.APIResponseDto;
import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.service.BatchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
 
@Tag(name = "Batch-Service - MentorController", description = "Batch Controller Exposes REST APIs for Batch-Service")
@RestController
@RequestMapping("api/batch")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class BatchController {
 
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchController.class);
 
	private BatchService batchService;
 
	@PostMapping
	public ResponseEntity<BatchDto> createBatch(@RequestBody @Valid BatchDto batchDto) {
		BatchDto savedBatch = batchService.createBatch(batchDto);
		return new ResponseEntity<>(savedBatch, HttpStatus.CREATED);
	}
 
	@Operation(summary = "Get Batch By Code REST API", description = "Get batch By REST API is used to get a batch object from the database")
 
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@GetMapping("code/{batchCode}")
	public ResponseEntity<APIResponseDto> getBatchByBatchCode(@PathVariable String batchCode) {
		APIResponseDto apiResponseDto = batchService.getBatchByCode(batchCode);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
	@Operation(summary = "Get Batch By Code REST API", description = "Get batch By REST API is used to get a batch object from the database")
	 
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@GetMapping("code1/{batchCode}")
	public ResponseEntity<BatchDto> getBatchByBatchCode1(@PathVariable String batchCode) {
		BatchDto batchDto = batchService.getBatchByCode1(batchCode);
		return new ResponseEntity<>(batchDto, HttpStatus.OK);
	}
	@Operation(summary = "Get Batch By ID REST API", description = "Get batch By REST API is used to get a batch object from the database")
 
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@GetMapping("{batchId}")
	public ResponseEntity<BatchDto> getBatchById(@PathVariable Long batchId) {
		BatchDto batch = batchService.getBatchById(batchId);
		return new ResponseEntity<>(batch, HttpStatus.OK);
	}
 
	@Operation(summary = "Get batch By REST API", description = "Get batch By REST API is used to get a batch object from the database")
 
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@GetMapping()
	public ResponseEntity<List<BatchDto>> getAllBatch() {
		List<BatchDto> batch = batchService.getAllBatch();
		return new ResponseEntity<>(batch, HttpStatus.OK);
	}
 
	@Operation(summary = "Put batch By REST API", 
			description = "put batch By REST API is used to update a batch object from the database")
	@ApiResponse(responseCode = "200", 
	description = "HTTP Status 200 SUCCESS")
	@PutMapping("code/{batchCode}")
	public ResponseEntity<BatchDto> updateBatch1(@PathVariable String batchCode, @RequestBody @Valid  BatchDto batchDto){
		batchDto.setBatchCode(batchCode);
		BatchDto updateBatch = batchService.updateBatch(batchDto);
		return new ResponseEntity<BatchDto>(updateBatch, HttpStatus.OK);
	}
	@Operation(summary = "Put batch By REST API", 
			description = "put batch By REST API is used to update a batch object from the database")
	@ApiResponse(responseCode = "200", 
	description = "HTTP Status 200 SUCCESS")
	@PutMapping("{batchId}")
	public ResponseEntity<BatchDto> updateBatch(@PathVariable Long batchId,
			@RequestBody @Valid BatchDto batchDto) {
		batchDto.setBatchId(batchId);
		BatchDto updateBatch = batchService.updateBatch(batchDto);
		return new ResponseEntity<BatchDto>(updateBatch, HttpStatus.OK);
	}
 
	@Operation(summary = "Delete batch By code REST API", description = "Delete batch By REST API is used to delete a batch  from the database")
 
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@DeleteMapping("code/{batchCode}")
	public ResponseEntity<String> deleteBatch1(@PathVariable String batchCode){
		batchService.deleteBatch1(batchCode);
		return new ResponseEntity<String>("Batch successfully deleted!!", HttpStatus.OK);
	}
 
	@Operation(summary = "Delete batch By ID REST API", description = "Delete batch By REST API is used to delete a batch  from the database")
 
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@DeleteMapping("{batchId}")
	public ResponseEntity<String> deleteBatch(@PathVariable Long batchId) {
		batchService.deleteBatch(batchId);
		return new ResponseEntity<String>("Batch successfully deleted!!", HttpStatus.OK);
	}
}