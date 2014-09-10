package com.fabricgroup.spring4.component;


import java.util.List;

import com.fabricgroup.spring4.component.TaskManagerService;
import com.fabricgroup.spring4.domain.Task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TaskManagerServiceTest {

@Test
	public void testGetAllTasks(){
		TaskManagerService tms = new TaskManagerService();
		
		tms.initTasks();
		List<Task> listOfTasks = tms.getAllTasks();
		
		//from init task, shall be 5 in the list
		assertEquals("with init task, shall be 5 in the list", 5, listOfTasks.size());
	}
	
	@Test
	public void archiveOneTaskFromInitShouldBeFour (){
		TaskManagerService tms = new TaskManagerService();
		
		tms.initTasks();
		tms.archiveTask(1);
		List<Task> listOfTasks = tms.getAllTasks();

		assertEquals("with init task, and archive one, shall be 4 in the list", 4, listOfTasks.size());
	}
	

	@Test
	public void changeTaskStatusShouldBeSame(){
		TaskManagerService tms = new TaskManagerService();
		String newStatus = "COMPLETED";
		
		tms.initTasks();
		tms.changeTaskStatus(1, newStatus);
		
		Task newStatusTask = tms.getTask(1); 
		
		assertEquals("status shall be changed to COMPLETED for task id 1", newStatus, newStatusTask.getTaskStatus());

	}
	
	@Test
	public void addOneTaskShouldBeSix(){
		Task task = new Task();
		task.setTaskId(6);
		task.setTaskDescription("Production Support");
		task.setTaskName("Support");
		task.setTaskPriority("MEDIUM");
		task.setTaskStatus("ACTIVE");
		
		TaskManagerService tms = new TaskManagerService();

		tms.initTasks();
		tms.addTask(task);
		
		assertEquals("after added 1 task to init task list, shauld have 6 in list", 6, tms.getAllTasks().size());

		
		
	}
}
