package workflow.system.folder.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workflow.models.Customer;
import workflow.models.Folder;
import workflow.security.Utility;
import workflow.system.folder.service.FolderService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/folders")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/new")
    public String newFolder(Model model) {
        model.addAttribute("folder", new Folder());
        model.addAttribute("pageHead", "Створення нової папки");
        return "folder/folder_form";
    }

    @PostMapping("/save")
    public String saveFolder(Folder folder, HttpServletRequest request, RedirectAttributes ra) {
        Customer authenticatedCustomer = Utility.getCustomerByEmailFromRequest(request);
        folder.setCustomer(authenticatedCustomer);
        Folder savedFolder = folderService.save(folder);
        ra.addFlashAttribute("message", "Папка " + savedFolder.getFolderName() + " успішно збережена!");
        return "redirect:/folders/page/1?sort=priority,desc";
    }

    @GetMapping("/edit/{id}")
    public String updateFolder(@PathVariable Integer id, Model model) {
        Folder folder = folderService.findById(id);
        model.addAttribute("folder",folder);
        model.addAttribute("pageHead", "Редагування папки " + folder.getFolderName());
        return "folder/folder_form";
    }

    @GetMapping("/delete/{id}")
    public String updateFolder(@PathVariable Integer id, RedirectAttributes ra) {
        String folderName = folderService.findById(id).getFolderName();
        folderService.deleteById(id);
        ra.addFlashAttribute("message", "Папка " + folderName + " успішно збережена!");
        return "redirect:/folders/page/1?sort=priority,desc";
    }

    @GetMapping("/check_for_uniqueness/{folderName}")
    @ResponseBody
    public String checkForUniqueness(@PathVariable String folderName){
        boolean isExists = folderService.checkFolderForDuplicatedName(folderName);
        return isExists ? "DuplicateName" : "OK";
    }

}
