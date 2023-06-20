package workflow.system.folder.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import workflow.models.Customer;
import workflow.models.Folder;
import workflow.security.Utility;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FolderRepositoryTest {

    @Autowired
    FolderRepository folderRepository;

    @Test
    void testCreate() {
        Folder folder = new Folder();
        folder.setFolderName("My work");
        folderRepository.save(folder);
    }

    @Test
    void findByPage(){
        Customer authenticatedCustomer = Utility.getCustomerByEmail("arthur.developer@ukr.net");
        Sort sort = Sort.by("priority").ascending();
        PageRequest pageRequest = PageRequest.of(1, 2, sort);

        Page <Folder> allByCustomer = folderRepository.findAllByCustomer(authenticatedCustomer, pageRequest);

        List <Folder> content = allByCustomer.getContent();
        for (Folder folder: content) {
            System.out.println(folder.getFolderName());
        }
    }


}