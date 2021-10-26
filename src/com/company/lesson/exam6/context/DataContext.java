package com.company.lesson.exam6.context;
import com.company.lesson.exam6.domain.Task;
import com.company.lesson.exam6.domain.TasksDataModel;
import java.util.Optional;

public interface DataContext {

    Optional<Task> getTask() ;
    boolean add(TasksDataModel task);
}
