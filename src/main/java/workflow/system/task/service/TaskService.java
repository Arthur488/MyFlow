package workflow.system.task.service;

import workflow.models.Task;
import workflow.system.generics.service.GenericService;

import java.util.List;

public interface TaskService extends GenericService <Task> {

    List <Task> findAllTasksByFolder(Integer folderId);

}
