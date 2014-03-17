package com.osmanizbat.todo.bootstrap;

import com.google.inject.AbstractModule;
import com.osmanizbat.todo.usecases.CompleteTodoInteractor;
import com.osmanizbat.todo.boundary.CompleteTodoRequestBoundary;
import com.osmanizbat.todo.usecases.CreateTodoInteractor;
import com.osmanizbat.todo.boundary.CreateTodoRequestBoundary;
import com.osmanizbat.todo.boundary.DeleteTodoRequestBoundary;
import com.osmanizbat.todo.boundary.ListTodosRequestBoundary;
import com.osmanizbat.todo.core.utils.TodoMapper;
import com.osmanizbat.todo.usecases.DeleteTodoInteractor;
import com.osmanizbat.todo.usecases.ListTodosInteractor;
import java.util.Iterator;
import java.util.ServiceLoader;

public class TodoCoreModule extends AbstractModule {

    @Override
    protected void configure() {
        System.out.println("Configuring TodoCoreModule...");
        
        installDataModule();
        
        bind(CreateTodoRequestBoundary.class).to(CreateTodoInteractor.class);
        bind(ListTodosRequestBoundary.class).to(ListTodosInteractor.class);
        bind(CompleteTodoRequestBoundary.class).to(CompleteTodoInteractor.class);
        bind(DeleteTodoRequestBoundary.class).to(DeleteTodoInteractor.class);
        bind(TodoMapper.class).toInstance(new TodoMapper());
        
    }
    
    private void installDataModule() {
        ServiceLoader<TodoDataModule> dataModules = ServiceLoader.load(TodoDataModule.class);
        final Iterator<TodoDataModule> iterator = dataModules.iterator();
        
        if(!iterator.hasNext()){
            throw new DataModuleNotFoundException();
        }
        
        for(TodoDataModule dm : dataModules){
            
            System.out.println("Installing " + dm.getClass().getName());
            
            if (iterator.hasNext()) {
                throw new MultipleDataModuleException(iterator.next().getClass().getName());
            }

            if(dm instanceof AbstractModule){
                install((AbstractModule)dm);
            }
        }
    }
    
}
