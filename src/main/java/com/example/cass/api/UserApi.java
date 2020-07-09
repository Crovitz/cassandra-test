package com.example.cass.api;

import com.example.cass.api.model.UserByStatusAndFavouriteDayView;
import com.example.cass.api.model.UserByUsernameView;
import com.example.cass.api.model.UserView;
import com.example.cass.domain.UserService;
import com.example.cass.domain.user.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserApi {
    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserView> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserView getById(@PathVariable("id") String id) {
        return userService.getById(UUID.fromString(id));
    }

    @GetMapping("/search")
    public UserByUsernameView getUserByUsername(@RequestParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/status-and-day")
    public List<UserByStatusAndFavouriteDayView> getByStatus(
            @RequestParam("status") String status,
            @RequestParam("day") String favouriteDay) {
        return userService.getByStatusAndFavouriteDay(User.Status.valueOf(status), LocalDate.parse(favouriteDay));
    }

    @PostMapping
    public UserView addUser(@RequestBody AddUserRequest request) {
        return userService.addUser(request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(UUID.fromString(id));
    }
}
