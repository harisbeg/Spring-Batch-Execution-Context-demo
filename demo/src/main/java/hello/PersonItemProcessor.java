package hello;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.JobContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
    
    private StepExecution stepExecution; 
    private List<Person> exclusionList;

    @Override
    public Person process(final Person person) throws Exception {
    	System.out.println("Entered hello.PersonItemProcessor.process()");
//    	ExecutionContext stepContext = this.stepExecution.getExecutionContext();
    	
//    	if (stepContext.containsKey("exclusionList")) {
//    		System.out.println("Step execution context contains exclusionList");
//    	} else {
//    		System.out.println("Step execution context doesn't contain exclusionList");
//    	}
    	
//    	@SuppressWarnings("unchecked") 
//    	List<Person> exclusionList = (List<Person>) stepContext.get("exclusionList");
//    	System.out.println("exclusionList read in hello.PersonItemProcessor.process() = " + exclusionList.toString());
    	
    	if (this.exclusionList.contains(person)) {
    		System.out.println("Person " + person.getFirstName() + " " + person.getLastName() + " found in exclusionList");
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
        	System.out.println("Job execution context in PersonItemProcessor contains exclusionList");
        	this.exclusionList = (List<Person>) jobContext.get("exclusionList");
        	for (Person person : exclusionList) {
        		System.out.println(person.getFirstName() + " " + person.getLastName() + " found in exclusionList; hence not writing");
        	}
        } else {
        	System.out.println("Job execution context doesn't contain exclusionList");
        }
    }
    
//    @BeforeStep
//    public void saveStepExecution(StepExecution stepExecution) {
//        this.stepExecution = stepExecution;
//    }

}