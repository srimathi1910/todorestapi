package com.sritodo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sritodo.Enitity.Todo;
import com.sritodo.Reposirtory.todorepo;
import com.sritodo.dto.Tododto;

import jakarta.validation.Valid;

@RestController
public class TodoController 
{
	
	@Autowired
	private todorepo repo;
	
	//--------GETTING A LIST OF TODOS----------------//
	@GetMapping("/todos")
	public List<Todo> root()
	{
		return repo.findAll();
	}
	
	
	//---------CREATING A TODO AND SAVING IT IN REPO----------//
	@PostMapping("/todos")
	public ResponseEntity<Object> addtodo(@Valid @RequestBody Tododto tododto)
	{
		repo.save(new Todo(null,tododto.getTask(),false));
		return ResponseEntity.ok().build();
	}
	
	
	
	//-----------GETTING A SPECIFIC TODO-------------------//
	@GetMapping("/todos/{id}")
	public Optional<Todo> getbyid(@PathVariable("id") Long id)
	{
		return repo.findById(id);
	}
	
	//--------------UPDATE THE TASK BY ID--------------------//
	
	@PutMapping("/todos/{id}")
	public void addtodo(@PathVariable("id")Long id,@Valid @RequestBody Tododto tododto)
	{
		Optional<Todo> t=repo.findById(id);
		if(t.isPresent())
		{
			Todo actualt=t.get();
			actualt.setTask(tododto.getTask());
			repo.save(actualt);
		}	
	}
	
	//--------------UPDATE THE STATUS USING ID----------------//
	
	@PutMapping("/todos/{id}/mark_complted")
	public void markcomplted(@PathVariable("id")Long id)
	{
		Optional<Todo> t=repo.findById(id);
		
		Todo actualid=t.get();
		if(actualid.isCompleted())
		{
			actualid.setCompleted(false);
		}
		else
		{
			actualid.setCompleted(true);
		}
		
		repo.save(actualid);
	}
	
	//-------------DELETE THE ID ---------------------//
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Void> deleteTodoById(@PathVariable("id") Long id) {
	    try {
	        repo.deleteById(id);
	        return ResponseEntity.noContent().build(); // Respond with HTTP 204 (No Content) for successful deletion
	    } catch (Exception e) {
	        return ResponseEntity.notFound().build(); // Respond with HTTP 404 (Not Found) if the entity with the specified ID does not exist
	    }
	}

	
	
	
	
	
	
	
	
	



}

