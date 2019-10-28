package com.example.batchprocessing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * com.example.batchprocessing.JobCompletionNotificationListener, created on 28/10/2019 08:34 <p>
 * @author AlessandroA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private final JdbcTemplate jdbcTemplate;

  @Override public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! Job finished, check results");

      jdbcTemplate.query("SELECT first_name, last_name FROM people",
          (rs, row) -> new Person(
              rs.getString(1),
              rs.getString(2))
      ).forEach(person -> log.info("Found {} in the database", person));
    }
  }
}
