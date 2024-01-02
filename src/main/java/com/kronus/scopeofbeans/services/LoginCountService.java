package com.kronus.scopeofbeans.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class LoginCountService {
    private long count;

    public void counter() {
        count++;
    }

    public long getCount() {
        return count;
    }
}
