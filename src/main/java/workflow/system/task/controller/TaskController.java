package workflow.system.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workflow.models.Priority;
import workflow.models.Status;
import workflow.models.Task;
import workflow.system.folder.service.FolderService;
import workflow.system.task.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/folders/{folder_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final FolderService folderService;

    public TaskController(TaskService taskService, FolderService folderService) {
        this.taskService = taskService;
        this.folderService = folderService;
    }

    @GetMapping
    public String findAllTasksByFolder(@PathVariable Integer folder_id, Model model) {
        List <Task> allTasksByFolder = taskService.findAllTasksByFolder(folder_id);
        String folderName = folderService.findById(folder_id).getFolderName();
        model.addAttribute("allTasks", allTasksByFolder);
        model.addAttribute("folderName", folderName);
        model.addAttribute("pageTitle", "Tasks in folder " + folderName);
        return "task/tasks";
    }

    @GetMapping("/new")
    public String createNewTask(Model model, @PathVariable Integer folder_id) {
        String folderName = folderService.findById(folder_id).getFolderName();
        model.addAttribute("task", new Task());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("folderId", folder_id);
        model.addAttribute("pageHead", "Нова задача");
        model.addAttribute("pageTitle", "New Task");
        model.addAttribute("folderName", folderName);
        return "task/task_form";
    }

    @PostMapping("/save")
    public String saveTask(@PathVariable Integer folder_id, Task task) {
        task.setFolder(folderService.findById(folder_id));
        taskService.save(task);
        return "redirect:/folders/" + folder_id + "/tasks";
    }

    @GetMapping("/edit/{task_id}")
    public String editTask(@PathVariable Integer task_id, Model model, @PathVariable String folder_id) {
        Task task = taskService.findById(task_id);
        model.addAttribute("task", task);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("folderId", folder_id);
        model.addAttribute("pageHead", "Редагування задачі " + task.getTaskName());
        model.addAttribute("pageTitle", "Edit Task");
        model.addAttribute("folderName", task.getFolder().getFolderName());
        return "task/task_form";
    }

    @GetMapping("/delete/{task_id}")
    public String deleteTask(@PathVariable Integer task_id, @PathVariable Integer folder_id) {
        taskService.deleteById(task_id);
        return "redirect:/folders/" + folder_id + "/tasks";
    }


}
