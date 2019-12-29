package com.mindera.school.music.actions.add;

import com.mindera.school.music.services.UserService;
import com.mindera.school.music.ui.Action;
import com.mindera.school.music.ui.Request;

import java.sql.SQLException;

import static com.mindera.school.music.services.Services.*;

public class AddUserAction implements Action {
    UserService userService;

    public AddUserAction() {
        this.userService = USER_SERVICE;
    }

    @Override
    public void execute() throws SQLException {
        System.out.println();
        Request request = new Request();
        request.hasString("Name", "What is your name? ");
        request.hasString("Birthdate", "What is your birthdate? [YYYY/MM/DD]: ");
        request.hasChar("Gender", "What is your gender? [F/M]: ");
        request.hasString("Country", "What is your country? ");
        request.hasString("Email", "What is your email? ");
        request.hasString("Password", "Set your password: ");
        userService.add(request.ask());
        System.out.println();
    }
}
