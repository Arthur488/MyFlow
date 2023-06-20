package workflow.system.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.models.Task;
import workflow.system.generics.service.GenericServiceImpl;
import workflow.system.task.dao.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl extends GenericServiceImpl <Task> implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public List <Task> findAllTasksByFolder(Integer folderId) {
        return taskRepository.findAllByFolder_Id(folderId);
    }
}
