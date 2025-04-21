package com.profitkey.stock.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.profitkey.stock.repository.stock.StockRepository;
import com.profitkey.stock.service.stock.StockService;
import com.profitkey.stock.util.DateTimeUtil;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BatchConfig {

	private final StockRepository stockRepository;
	private final StockService stockService;
	private final org.springframework.batch.core.repository.JobRepository jobRepository;
	private final org.springframework.transaction.PlatformTransactionManager transactionManager;

	public BatchConfig(StockRepository stockRepository,
		StockService stockService,
		org.springframework.batch.core.repository.JobRepository jobRepository,
		org.springframework.transaction.PlatformTransactionManager transactionManager) {
		this.stockRepository = stockRepository;
		this.stockService = stockService;
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	public Job createStockInfoJob() {
		return new JobBuilder("createStockInfoJob", jobRepository)
			.start(createStockInfoStep())
			.build();
	}

	@Bean
	public Step createStockInfoStep() {
		log.info("createStockInfoStep batch-------------------------*********");

		String today = DateTimeUtil.curDate("");
		stockRepository.deleteByBaseDate(today);

		return new StepBuilder("createStockInfoStep", jobRepository)
			.tasklet((contribution, chunkContext) -> {
				stockService.createStockInfo();
				return RepeatStatus.FINISHED;
			}, transactionManager)
			.build();
	}
}
