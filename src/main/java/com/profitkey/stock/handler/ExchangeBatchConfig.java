package com.profitkey.stock.handler;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import com.profitkey.stock.dto.ExchangeDto;
import com.profitkey.stock.util.ExchangeUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ExchangeBatchConfig {

	private final ExchangeUtils exchangeUtils;

    @Bean
    @Primary
    public Job exchangeJob(JobRepository jobRepository,
                           Step step) {
        return new JobBuilder("exchangeJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager tm, Tasklet tasklet) {
        return new StepBuilder("step", jobRepository)
                .tasklet(tasklet, tm)
                .build();
    }

    @Bean
    public Tasklet tasklet() {
        return ((contribution, chunkContext) -> {
            List<ExchangeDto> exchangeDtoList = exchangeUtils.getExchangeDataAsDtoList();

            for (ExchangeDto exchangeDto : exchangeDtoList) {
                log.info("통화 : " + exchangeDto.getCur_nm());
                log.info("환율 : " + exchangeDto.getDeal_bas_r());
            }
            return RepeatStatus.FINISHED;
        });
    }
}