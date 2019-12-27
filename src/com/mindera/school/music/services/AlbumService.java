package com.mindera.school.music.services;

import com.mindera.school.music.ui.Mapper;
import com.mindera.school.music.data.rows.Album;
import com.mindera.school.music.data.tables.AlbumTable;
import com.mindera.school.music.ui.KeyValue;
import com.mindera.school.music.ui.Request;

import java.sql.SQLException;
import java.util.List;

import static com.mindera.school.music.data.tables.Tables.*;
import static com.mindera.school.music.services.Services.*;

public class AlbumService {
    AlbumTable albumTable;
    StudioService studioService;
    Mapper mapper;

    public AlbumService() {
        this.albumTable = ALBUM_TABLE;
        this.studioService = STUDIO_SERVICE;
        this.mapper = new Mapper();
    }

    public void add(List<KeyValue> keyValueList) throws SQLException {
        Album album = new Album();

        for (KeyValue keyValue : keyValueList) {
            if (keyValue.getName().equals("Name")) {
                if (albumTable.verifyIfExistsName(keyValue.getValue().toString())) {
                    System.out.println("This album already exits.");
                    return;
                }
                album.setName(keyValue.getValue().toString());
            }
            if (keyValue.getName().equals("Year")) {
                int year = (Integer) keyValue.getValue();
                while(year < 0 || year > 2020) {
                    Request request = new Request();
                    request.hasInt("Year", "Invalid year. Insert again: ");
                    List<KeyValue> list = request.ask();
                    year = (int) list.get(0).getValue();
                }
                album.setYear(year);
            }
            if (keyValue.getName().equals("Studio")) {
                album.setStudioId(mapper.getStudioIdByName(keyValue.getValue().toString()));
            }
        }

        albumTable.add(album);
    }

    public void removeById(int id) throws SQLException {
        albumTable.removeById(id);
    }

    public void removeByName(String name) throws SQLException {
        albumTable.removeByName(name);
    }

    public Album find(int id) throws SQLException {
        return albumTable.findById(id);
    }

    public List<Album> findAll() throws SQLException {
        return albumTable.findAll();
    }

    public void printAll() throws SQLException {
        List<Album> albumList = findAll();

        if (albumList.isEmpty()) {
            System.out.println("There is no albums.");
            return;
        }

        for (Album album : albumList) {
            System.out.println("Album id: " + album.getId());
            System.out.println("Name: " + album.getName());
            System.out.println("Number of Likes: " + album.getNrLikes() + '\n');
        }
    }

    public int findByName(String name) throws SQLException {
        return albumTable.findIdByName(name);
    }

    public void print(int id) throws SQLException {
        Album album = find(id);

        if (album == null) {
            System.out.println("There is no album with this id.");
            return;
        }

        System.out.println("Album id: " + album.getId());
        System.out.println("Name: " + album.getName());
        System.out.println("Year: " + album.getYear());
        System.out.println("Studio: " + studioService.find(album.getStudioId()).getName());
        System.out.println("Number of Likes: " + album.getNrLikes());
        System.out.println("Number of Searches: " + album.getNrSearch() + '\n');

//        System.out.println("Musics: ");
//
//        List<Music> musicList = album.getMusicList();
//        for (Music music : musicList) {
//            System.out.print("Id: " + music.getId() + " Name: " + music.getName() + '\n');
//        }
    }
}
