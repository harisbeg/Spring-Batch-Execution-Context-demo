package hello;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class MyTasklet implements Tasklet {
	
	@Autowired
	private List<Person> exclusionList;

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		exclusionList.clear();
		exclusionList.add(new Person("Joe", "Doe"));
		exclusionList.add(new Person("Jane", "Doe"));
		chunkContext.getStepContext().getJobExecutionContext().put("exclusionList", exclusionList);
		return RepeatStatus.FINISHED;
	}

}
