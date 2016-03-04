package hello;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
    
    private List<Person> exclusionList;

    @Override
    public Person process(final Person person) throws Exception {
    	
    	if (this.exclusionList.contains(person)) {
    		System.out.println("Person " + person.getFirstName() + " " + person.getLastName() + " found in exclusionList. Hence, not converting.");
    		return null;
    	}
    	
        final String firstName = person.getFirstName().toUpperCase();
        
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
    
    @SuppressWarnings("unchecked")
	@BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        if (jobContext.containsKey("exclusionList")) {
        	this.exclusionList = (List<Person>) jobContext.get("exclusionList");
        } else {
        	log.info("Job execution context doesn't contain exclusionList");
        }
    }
    
}