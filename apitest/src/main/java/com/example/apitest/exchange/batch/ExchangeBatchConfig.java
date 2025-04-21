package com.example.apitest.exchange.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ExchangeBatchConfig {
    private final ExchangeUtils exchangeUtils;

    @Bean
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

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), bf.readLine(), Boolean.valueOf(false));
        int suNo = Integer.parseInt(st.nextToken());
        int quizNo = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        long[] v = new long[suNo + 1];
        for (long l : v) {

        }







    }

    @Bean
    public Tasklet tasklet() {
        return ((contribution, chunkContext) -> {
            try {
                List<ExchangeDto> exchangeDtoList = exchangeUtils.getExchangeDataAsDtoList();
                log.info("환율 정보 가져오기 성공: " + exchangeDtoList.size() + " 개");
                for (ExchangeDto exchangeDto : exchangeDtoList) {
                    System.out.println("통화 : " + exchangeDto.getCur_nm());
                    System.out.println("환율 : " + exchangeDto.getDeal_bas_r());
                    log.info("통화 : " + exchangeDto.getCur_nm());
                    log.info("환율 : " + exchangeDto.getDeal_bas_r());
                }
            } catch (Exception e) {
                log.error("환율 데이터 가져오기 실패", e);
            }
            return RepeatStatus.FINISHED;
        });
    }
}