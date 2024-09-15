package com.example.back_end.controller;

import com.example.back_end.model.FriendWithLombok;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/twitter")
public class FriendsController {

    private List<FriendWithLombok> friends;

    public FriendsController() {
        friends = new ArrayList<>();
        friends.add(new FriendWithLombok("nicoll", "Stepane Nicoll"));
        friends.add(new FriendWithLombok("woojung", "Jeon Woojung"));
    }

    @GetMapping("/")
    public List<FriendWithLombok> list() {
        return friends;
    }

    @GetMapping("/{id}")
    public FriendWithLombok get(@PathVariable String id) {
        return friends.stream().filter(f -> id.equals(f.getId())).findAny().orElse(null);
    }

    @PostMapping("/post")
    public String PostTest(@RequestBody String msg) {
        return "post success!!!" + msg;
    }
}
