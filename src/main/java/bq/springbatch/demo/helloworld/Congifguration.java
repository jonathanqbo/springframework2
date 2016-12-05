package bq.springbatch.demo.helloworld;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class Congifguration {

	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Bean
	public ItemReader<HelloWorld> reader() {
		FlatFileItemReader<HelloWorld> reader = new FlatFileItemReader<HelloWorld>();
		reader.setResource(new ClassPathResource("batch_helloworld.csv"));
		
		// LineMapper
		DefaultLineMapper<HelloWorld> mapper = new DefaultLineMapper<HelloWorld>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames(new String[]{"name","value"});
		mapper.setLineTokenizer(delimitedLineTokenizer);
		
		BeanWrapperFieldSetMapper<HelloWorld> fieldSetMapper = new BeanWrapperFieldSetMapper<HelloWorld>();
		fieldSetMapper.setTargetType(HelloWorld.class);
		mapper.setFieldSetMapper(fieldSetMapper);
		
		reader.setLineMapper(mapper);
		
		return reader;
	}
	
	@Bean
	public ItemWriter<HelloWorld> writer() {
		return (values) -> {
			System.out.print("\nwriting ");
			values.forEach(v -> System.out.print(v.getName() + "=" + v.getValue() + "; "));
		};
	}
	
	@Bean
	public ItemProcessor<HelloWorld, HelloWorld> processor() {
		return (value) -> {
			System.out.print("\nprocessing value " + value.getName() + "=" + value.getValue());
			return value;
		};
	}
	
	@Bean
	public Step step1(ItemReader<HelloWorld> reader, ItemProcessor<HelloWorld, HelloWorld> processor, ItemWriter<HelloWorld> writer) {
		return steps.get("helloworld")
				.<HelloWorld, HelloWorld> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean
	public Job job(Step step1) {
		return jobs.get("helloworld")
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.build();
	}
	
}
