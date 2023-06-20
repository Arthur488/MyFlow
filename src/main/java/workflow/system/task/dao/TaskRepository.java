package workflow.system.task.dao;

import workflow.models.Task;
import workflow.system.generics.dao.GenericRepository;

import java.util.List;

public interface TaskRepository extends GenericRepository <Task> {

    List <Task> findAllByFolder_Id(Integer folder_id);


}
