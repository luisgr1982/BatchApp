package com.at.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.at.batch.model.Person;
import com.at.batch.services.PersonItemProcessor;
import com.at.batch.services.PersonWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public FlatFileItemReader<Person> reader() {//leemos el archivo
    	   FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
    	    reader.setResource(new ClassPathResource("person-salary.csv"));
    	    reader.setLineMapper(new DefaultLineMapper<Person>() {{
    	        setLineTokenizer(new DelimitedLineTokenizer() {{
    	            setNames(new String[] { "id", "firstName", "lastName", "salary" });
    	        }});
    	        setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
    	            setTargetType(Person.class);
    	        }});
    	    }});
    	    return reader;
    }
    
    @Bean
    public ItemWriter<Person> writer() {    	
    	return new PersonWriter();
    }
    
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	} 
	
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .flow(step1())
            .end()
            .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
            .<Person, Person> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
    }    
}
