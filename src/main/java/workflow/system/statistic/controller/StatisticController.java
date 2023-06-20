package workflow.system.statistic.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workflow.models.Folder;
import workflow.security.Utility;
import workflow.system.folder.service.FolderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/charts")
public class StatisticController {

    private final FolderService folderService;

    @Autowired
    public StatisticController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping
    public String getChartData(Model model, HttpServletRequest request) {
        List <Folder> folderList = folderService.findAllFoldersByCustomer(Utility.getCustomerByEmailFromRequest(request));
        Map <String, Integer> priorityCounts = new HashMap <>();

        // Подсчет количества папок для каждого приоритета
        for (Folder folder : folderList) {
            String priority = folder.getPriority().getValue() + " пріорітет";
            priorityCounts.put(priority, priorityCounts.getOrDefault(priority, 0) + 1);
        }

        // Передача данных в модель
        model.addAttribute("priorityCounts", priorityCounts);
        model.addAttribute("pageTitle", "Folder Priority Chart");
        return "statistic/statistic";
    }

}
