package com.example.back_end.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendWithLombok {

    private String id;
    private String name;
    private Void getId;
    private Void getName;
    private Void setId;
    private Void setName;

    public FriendWithLombok(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
