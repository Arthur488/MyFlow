package workflow.system;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import workflow.models.Customer;
import workflow.models.Folder;
import workflow.models.Priority;
import workflow.security.Utility;
import workflow.system.folder.service.FolderService;

import java.util.*;
import java.util.stream.StreamSupport;

@Controller
public class MainController {

    private final FolderService folderService;

    public MainController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isNotAuthenticated = authentication == null || authentication instanceof AnonymousAuthenticationToken;
        return isNotAuthenticated ? "login" : "redirect:/";
    }

    @GetMapping("/")
    public String findAllFolders() {
        return "redirect:/folders/page/1?sort=priority,desc";
    }

    @GetMapping("/folders/page/{pageNum}")
    public String findAllFoldersSort(Model model, HttpServletRequest request,
                                     @PathVariable Integer pageNum,
                                     @RequestParam("sort") String sort) {

        String[] values = sort.split(",");
        String sortField = values[0];
        String sortOrder = values[1];

        Customer authenticatedCustomer = Utility.getCustomerByEmailFromRequest(request);
        Pageable pageable = createPageable(sortField, sortOrder, pageNum - 1);
        Page <Folder> foldersByCustomerByPage = folderService.findFoldersByCustomerByPage(authenticatedCustomer, pageable);
        List<Folder> folderList = new ArrayList <>(foldersByCustomerByPage.getContent());
        if (sortField.equals("priority")){
            switch (sortOrder){
                case "asc" -> folderList.sort(Comparator.comparing(Folder::getPriorityWeight));
                case "desc" -> folderList.sort(Comparator.comparing(Folder::getPriorityWeight).reversed());
            }
        }
        if (sortField.equals("taskList")){
            switch (sortOrder){
                case "asc" -> folderList.sort((o1, o2) -> o2.getTaskList().size() - o1.getTaskList().size());
                case "desc" -> folderList.sort(Comparator.comparingInt(o -> o.getTaskList().size()));
            }
        }
        model.addAttribute("allFolders", folderList);
        model.addAttribute("pageTitle", "All folders");
        return "folder/folders";
    }

    private Pageable createPageable(String sortField, String sortOrder, Integer pageNum) {
        Sort sort = Sort.by(sortField);
        sort = sortOrder.equals("asc") ? sort.ascending() : sort.descending();
        return PageRequest.of(pageNum, 12, sort);
    }

}

