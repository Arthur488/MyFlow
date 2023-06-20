package workflow.system.task.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import workflow.models.Folder;
import workflow.models.Priority;
import workflow.models.Status;
import workflow.models.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    @Rollback(value = false)
    void testCreateTask() {
        Task task = new Task();
        task.setTaskName("Create photo upload");
        task.setTaskDescription("Create photo upload in order have ability to upload up to 10 photos at 1 time");
        task.setCreatedDate(LocalDate.now());
        task.setPriority(Priority.LOW);
        task.setDeadline(LocalDate.of(2023, 10, 15));
        task.setExpired(false);
        task.setStatus(Status.InProgress);
        taskRepository.save(task);
    }

}