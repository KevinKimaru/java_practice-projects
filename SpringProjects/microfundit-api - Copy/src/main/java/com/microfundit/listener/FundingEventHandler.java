package com.microfundit.listener;

import com.microfundit.model.Funding;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@Component
@RepositoryEventHandler(Funding.class)
public class FundingEventHandler {
    @HandleBeforeCreate
    private void setDefaultsBeforeAddingFunding(Funding funding) {
        if(funding.getPlacedAmount() % funding.getRatio() != 0) {
            throw new RuntimeException("Amount funded has to be a multiple of the ratio assigned");
        }
        funding.setDateAdded(new Date());
        funding.setStatus(1);
        funding.setCurrentAmount(funding.getPlacedAmount());
    }


}

