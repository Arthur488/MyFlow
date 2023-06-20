package workflow.system.folder.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workflow.models.Customer;
import workflow.models.Folder;
import workflow.system.generics.service.GenericService;

import java.util.List;

public interface FolderService extends GenericService <Folder> {

    List <Folder> findAllFoldersByCustomer(Customer customer);

    Page <Folder> findFoldersByCustomerByPage(Customer customer, Pageable pageable);

    boolean checkFolderForDuplicatedName(String folderName);

}
