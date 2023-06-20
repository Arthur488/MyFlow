package workflow.system.generics.dao;

import org.springframework.data.repository.CrudRepository;
import workflow.models.BaseEntity;

public interface GenericRepository <T extends BaseEntity> extends CrudRepository <T, Integer> {
}
