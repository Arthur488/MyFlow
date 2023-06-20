package workflow.system.folder.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import workflow.models.Customer;
import workflow.models.Folder;
import workflow.system.folder.dao.FolderRepository;
import workflow.system.generics.service.GenericServiceImpl;

import java.util.List;

@Service
public class FolderServiceImpl extends GenericServiceImpl <Folder> implements FolderService {

    private final FolderRepository folderRepository;

    public FolderServiceImpl(FolderRepository folderRepository) {
        super(folderRepository);
        this.folderRepository = folderRepository;
    }

    @Override
    public List <Folder> findAllFoldersByCustomer(Customer customer) {
        return folderRepository.findAllByCustomer(customer);
    }

    @Override
    public Page <Folder> findFoldersByCustomerByPage(Customer customer, Pageable pageable) {
        return folderRepository.findAllByCustomer(customer, pageable);
    }

    @Override
    public boolean checkFolderForDuplicatedName(String folderName) {
        return folderRepository.existsByFolderName(folderName);
    }
}
