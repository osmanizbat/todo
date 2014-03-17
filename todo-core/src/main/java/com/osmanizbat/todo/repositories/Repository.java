package com.osmanizbat.todo.repositories;

import java.util.List;

/**
 *
 * @author osmanizbat
 */
 public interface Repository<ID, ENTITY> {
        ENTITY get(ID id);
        void update(ENTITY entity);
        void destroy(ID id);
        void create(ENTITY entity);
        List<ENTITY> all();
    }