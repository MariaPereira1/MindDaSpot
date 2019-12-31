package com.mindera.school.music.actions.album;

import com.mindera.school.music.services.AlbumService;
import com.mindera.school.music.ui.Action;
import com.mindera.school.music.ui.Request;

import java.sql.SQLException;

import static com.mindera.school.music.services.Services.*;

public class AddAlbumAction implements Action {
    private AlbumService albumService;

    public AddAlbumAction() {
        this.albumService = ALBUM_SERVICE;
    }

    @Override
    public void execute() throws SQLException {
        Request request = new Request();
        request.hasString("Name", "Insert the name of the album: ");
        request.hasInt("Year", "Insert the year of the album: ");
        request.hasString("Artist", "Insert the name of the artist: ");
        request.hasString("Producer", "Insert the name of the producer: ");
        request.hasString("Studio", "Insert the name of the studio: ");
        albumService.add(request.ask());
    }
}