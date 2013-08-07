package com.ecomputercoach.file.partition;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.item.ExecutionContext;

@SuppressWarnings("unchecked")
public class OutputFileListener extends StepListenerSupport{

	private String outputKeyName = "outputFile";

	private String inputKeyName = "fileName";

	public void setOutputKeyName(String outputKeyName) {
		this.outputKeyName = outputKeyName;
	}

	public void setInputKeyName(String inputKeyName) {
		this.inputKeyName = inputKeyName;
	}

	
	public void beforeStep(StepExecution stepExecution) 
	{
		ExecutionContext executionContext = stepExecution.getExecutionContext();
		if (executionContext.containsKey(inputKeyName) && !executionContext.containsKey(outputKeyName)) {
			String inputName = executionContext.getString(inputKeyName);
			executionContext.putString(outputKeyName, "file:c:/data/output/" + FilenameUtils.getBaseName(inputName) + ".csv");
		}
	}

}
