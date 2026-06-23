package org.example;
import com.northernArc.springDao.entity.Todo;

import java.util.Collection;

public interface TodoDao {
    void save(Todo todo);
    void deleteById(int id);
    void updateById(int id, Todo todo);
    Todo findById(int id);
    Collection<Todo> findAllTodos();
    Collection<Todo> findAllCompletedTodos();
    Collection<Todo> findAllIncompleteTodos();
    Collection<Todo> sortByCompleted();
    Collection<Todo> sortByIncompleted();
    void deleteAll();
}
