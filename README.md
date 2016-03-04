# Spring-Batch-demo-1
This Spring Batch project demonstrates how to pass data between job steps using execution context and promotion listener. 
It creates an exclusion list in the Tasklet step, and uses this list in the ItemProcessor step to filter out some input records.
To pass the exclusion list from the Tasklet step to the processor step, it first saves it in the Tasklet step's execution context.
Then it promotes it to the Job's execution context using the execution context promotion listener. 
The processor step retrieves the interstep data from the Job execution context and uses it for the excusion logic.

