package udemy.spring.batch.udemy.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgomes on 7/10/17.
 */
@Configuration
public class JobConfiguration {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public SimpleReader simpleReader() {
        List<Integer> data = new ArrayList<Integer>(200);

        for(int i = 0; i < 200; i++) {
            data.add(i);
        }

        return new SimpleReader(data);
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .chunk(10)
                .reader(simpleReader())
                .writer(list -> list.forEach(item -> System.out.println("Item: " + item)))
                .build();
    }

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                .start(step1())
                .build();
    }
}
