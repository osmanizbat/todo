package com.osmanizbat.todo.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.osmanizbat.todo.repositories.TodoRepository;
import com.osmanizbat.todo.data.models.MockTodoRepository;

public class TodoMockDataModule extends AbstractModule implements TodoDataModule {

    @Override
    protected void configure() {
        System.out.println("Configuring TodoMockDataModule...");
        bind(TodoRepository.class).to(MockTodoRepository.class).in(Singleton.class);
    }
    
}
