package workflow.system.folder.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workflow.models.Customer;
import workflow.models.Folder;
import workflow.system.generics.dao.GenericRepository;

import java.util.List;

public interface FolderRepository extends GenericRepository <Folder> {

    List <Folder> findAllByCustomer(Customer customer);

    Page <Folder> findAllByCustomer(Customer customer, Pageable pageable);

    boolean existsByFolderName(String folderName);

}
