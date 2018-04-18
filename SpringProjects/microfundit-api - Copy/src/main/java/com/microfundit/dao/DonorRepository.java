package com.microfundit.dao;

import com.microfundit.model.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Kevin Kimaru Chege on 4/4/2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
public interface DonorRepository extends CrudRepository<Donor, Long> {
}
