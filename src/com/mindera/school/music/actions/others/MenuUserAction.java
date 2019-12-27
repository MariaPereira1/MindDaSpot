package com.mindera.school.music.actions.others;

import com.mindera.school.music.actions.rows.AlbumUserMenu;
import com.mindera.school.music.actions.rows.MusicUserMenu;
import com.mindera.school.music.ui.Action;
import com.mindera.school.music.ui.Menu;
import com.mindera.school.music.ui.Option;

import java.sql.SQLException;

public class MenuUserAction implements Action {
    private Menu menu;

    public MenuUserAction() {
        this.menu = new Menu();
        menu.add(new Option("Back", new LogoutAction()), true);
        menu.add(new Option("Music", new MusicUserMenu()));
        menu.add(new Option("Album", new AlbumUserMenu()));
        menu.add(new Option("Exit", new ExitOption()));
    }

    @Override
    public void execute() throws SQLException {
        menu.render();
    }
}