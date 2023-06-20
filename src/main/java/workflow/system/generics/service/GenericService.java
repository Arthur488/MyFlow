package workflow.system.generics.service;

import workflow.models.BaseEntity;

public interface GenericService <T extends BaseEntity>{

    Iterable <T> findAll();

    T findById(Integer id);

    T save(T t);

//    T update(Integer id, T t);

    void deleteById(Integer id);

}
