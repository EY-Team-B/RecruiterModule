package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.UsersRepository;
import model.Users;

@RestController
public class RecruiterController {
	@Autowired
    private UsersRepository repo;

	@GetMapping("recruiters")                   //getall
	public List<Users> getAllRecruiters(){
		
		return repo.findAllByRole("RECRUITER");
	}

	@GetMapping("/recruiters/{email}")               //getbyemail
	public Users recruiters(@PathVariable("email") String email)   
	{  
	return repo.findById(email).get();  
	}  
	
	@PostMapping("add")                    //add
	public String addRecruiter(@RequestBody Users recruiter) {
		repo.save(recruiter);
		return recruiter.getEmail()+" Added Successfully!!!";
	}
	
	@PutMapping("update/{email}") // update
	public String update(@RequestBody Users recruiter, @PathVariable("email") String email) {
		repo.findById(email).map(emp -> 
		{
			recruiter.setName(recruiter.getName());
			return repo.save(recruiter);
		});
		return email + " updated successfully";
	}
	
	@DeleteMapping("/deleteRecruiter/{email}")    //delete
	public String deleteRecruiter(@PathVariable("email") String email)   
	{  
	repo.deleteById(email);  
	return "Recruiter " + email +" has been deleted successfully";
	}  

}