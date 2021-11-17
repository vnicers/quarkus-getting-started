package org.acme.getting.started.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.acme.getting.started.common.config.Views;

@AllArgsConstructor
public class User {

    @JsonView(Views.Private.class)
    public int id;

    @JsonView(Views.Public.class)
    public String name;
}