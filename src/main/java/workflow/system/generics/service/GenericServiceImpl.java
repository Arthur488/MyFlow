package workflow.system.generics.service;

import org.springframework.stereotype.Service;
import workflow.models.BaseEntity;
import workflow.system.generics.dao.GenericRepository;

@Service
public class GenericServiceImpl <T extends BaseEntity> implements GenericService <T> {

    private final GenericRepository <T> genericRepository;

    public GenericServiceImpl(GenericRepository <T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public Iterable <T> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public T findById(Integer id) {
        return genericRepository.findById(id).get();
    }

    @Override
    public T save(T t) {
        return genericRepository.save(t);
    }

    @Override
    public void deleteById(Integer id) {
        genericRepository.deleteById(id);
    }
}
