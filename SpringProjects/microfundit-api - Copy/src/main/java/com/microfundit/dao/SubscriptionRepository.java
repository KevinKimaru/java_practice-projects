package com.microfundit.dao;

import com.microfundit.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Kevin Kimaru Chege on 4/2/2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
