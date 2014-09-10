package com.fabricgroup.spring4.component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.fabricgroup.spring4.domain.Task;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TaskManagerService {

	private final String TASK_JSON_FILE_LOCATION = "/Users/charles.wu/Documents/workspace/spring4/task.txt";
	public List<Task> getAllTasks() {
		
		List<Task> listOfTasks = new ArrayList<Task>();
		try{
		//read json file data to String
		Path file = Paths.get(TASK_JSON_FILE_LOCATION);
        byte[] jsonData = Files.readAllBytes(file);
         
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        

        //convert json string to object
        //as asList Returns a fixed-size list backed by the specified array. cannot remove in later operation
        listOfTasks = new LinkedList<Task> (
        		Arrays.asList(objectMapper.readValue(jsonData, Task[].class)));
        
        
        System.out.println("listOfTasks Object read from file\n"+listOfTasks);
         
		}
		catch (IOException oe){
			oe.printStackTrace();
		}
		return listOfTasks;
	}

	public void archiveTask(int taskId) {
		
		
		List<Task> listOfTasks = getAllTasks();
		
		Iterator<Task> iTasks = listOfTasks.iterator();
		while (iTasks.hasNext()){
			Task t = iTasks.next();
			
			if (t.getTaskId() == taskId){
				iTasks.remove();
				System.out.println(" RRRRemoved Task with ID = "+taskId);
			}
		}
		writeTasktoFile(listOfTasks);
		
	}

	public void changeTaskStatus(int taskId, String taskStatus) {

		List<Task> listOfTasks = getAllTasks();
		
		Iterator<Task> iTasks = listOfTasks.iterator();
		while (iTasks.hasNext()){
			Task t = iTasks.next();
			
			if (t.getTaskId() == taskId){
				t.setTaskStatus(taskStatus);
				System.out.printf("Change Task with ID %d and status is %s ",taskId, taskStatus);
			}
		}
		writeTasktoFile(listOfTasks);
		
	}

	
	public Task getTask (int taskId){
		Task task = new Task();
		
List<Task> listOfTasks = getAllTasks();
		
		Iterator<Task> iTasks = listOfTasks.iterator();
		while (iTasks.hasNext()){
			Task t = iTasks.next();
			
			if (t.getTaskId() == taskId){
				task = t;
				System.out.printf("get the single task with id %d ",taskId);
			}
		}
		
		return task;
	}
	
	
	public void addTask(Task task) {
		task.setTaskId( genTaskId() );
		
		List<Task> listOfTasks = getAllTasks();
		listOfTasks.add(task);
		writeTasktoFile(listOfTasks);
		
	}
	
	public void initTasks(){
		List<Task> listOfTask = new ArrayList<Task> ();
		
		Task task = new Task();
		task.setTaskId(1);
		task.setTaskDescription("Gathering Requirement");
		task.setTaskName("Requirement");
		task.setTaskPriority("MEDIUM");
		task.setTaskStatus("ACTIVE");
		listOfTask.add(task);
		
		task = new Task();
		task.setTaskId(2);
		task.setTaskDescription("Application Designing");
		task.setTaskName("Design");
		task.setTaskPriority("MEDIUM");
		task.setTaskStatus("ACTIVE");
		listOfTask.add(task);
		
		task = new Task();
		task.setTaskId(3);
		task.setTaskDescription("Implementation");
		task.setTaskName("Implementation");
		task.setTaskPriority("MEDIUM");
		task.setTaskStatus("ACTIVE");
		listOfTask.add(task);
		
		task = new Task();
		task.setTaskId(4);
		task.setTaskDescription("Unit Testing");
		task.setTaskName("Testing");
		task.setTaskPriority("LOW");
		task.setTaskStatus("ACTIVE");
		listOfTask.add(task);
		
		task = new Task();
		task.setTaskId(5);
		task.setTaskDescription("Maintanence");
		task.setTaskName("Maintanence");
		task.setTaskPriority("LOW");
		task.setTaskStatus("ACTIVE");
		listOfTask.add(task);
		
		writeTasktoFile(listOfTask);
		System.out.println("IIIIINIT 5 tasks done");
	}
	
	private void writeTasktoFile(List<Task> listOfTasks){
		try{
			Path file = Paths.get(TASK_JSON_FILE_LOCATION);
	         
	        Charset charset = Charset.forName(JsonEncoding.UTF8.toString());

	        BufferedWriter writer = Files.newBufferedWriter(file, charset);
	        
	        //create ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();
	        //configure Object mapper for pretty print
	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	         
	        //writing to console, can write to any output stream such as file
	        StringWriter stringEmp = new StringWriter();
	        objectMapper.writeValue(stringEmp, listOfTasks);
	        objectMapper.writeValue(writer, listOfTasks);
	        System.out.println(" Tasks JSON being written\n"+stringEmp);		
	} catch (Exception e){
		e.printStackTrace();
	}
	}

	private int genTaskId(){
		return (int) (new Date().getTime()/1000);
	}
}
