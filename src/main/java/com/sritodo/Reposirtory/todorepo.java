package com.sritodo.Reposirtory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sritodo.Enitity.Todo;

public interface todorepo extends JpaRepository<Todo,Long>{

}
