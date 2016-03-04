package hello;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MyTasklet implements Tasklet {
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		List<Person> exclusionList = new ArrayList<Person>();
		exclusionList.add(new Person("Joe", "Doe"));
		exclusionList.add(new Person("Jane", "Doe")); 
		
		StepContext stepContext = chunkContext.getStepContext(); 
		stepContext.getStepExecution().getExecutionContext().put("exclusionList", exclusionList);
		return RepeatStatus.FINISHED;
	}

}
