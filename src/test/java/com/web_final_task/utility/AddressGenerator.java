package com.web_final_task.utility;

import com.web_final_task.entity.Address;
import com.web_final_task.entity.xml.State;

import static com.web_final_task.utility.CommonFaker.getFakerWithDefaultLocale;

public class AddressGenerator {

    public static Address generate(long id, long userId) {
        return Address.builder()
                .id(id)
                .userId(userId)
                .addressLine1(getFakerWithDefaultLocale().address().streetAddress())
                .addressLine2(getFakerWithDefaultLocale().address().streetAddress())
                .city(getFakerWithDefaultLocale().address().city())
                .zip(getFakerWithDefaultLocale().address().zipCode())
                .state(State.DC.value())
                .build();
    }
}
