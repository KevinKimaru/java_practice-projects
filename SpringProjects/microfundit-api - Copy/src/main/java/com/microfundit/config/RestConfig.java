package com.microfundit.config;

/**
 * Created by Kevin Kimaru Chege on 3/24/2018.
 */

import com.microfundit.model.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RestConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Organisation.class, Brand.class, Donation.class, Funding.class, PointsCompany.class,
                Story.class, Transaction.class, User.class);
    }
}
