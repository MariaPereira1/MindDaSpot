package com.mindera.school.music.services;

import com.mindera.school.music.Mapper;
import com.mindera.school.music.data.rows.Album;
import com.mindera.school.music.data.tables.AlbumTable;
import com.mindera.school.music.ui.KeyValue;

import java.util.List;

public class AlbumService {
    AlbumTable albumTable;

    public AlbumService(AlbumTable albumTable) {
        this.albumTable = albumTable;
    }

    public void add(List<KeyValue> keyValueList) {
        Album album = new Album();

        album.setId(albumTable.getNewId());

        for (KeyValue keyValue : keyValueList) {
            if (keyValue.getName().equals("Name")) {
                album.setName(keyValue.getValue().toString());
            }
            if (keyValue.getName().equals("Name")) {
                album.setName(keyValue.getValue().toString());
            }
            if (keyValue.getName().equals("Name")) {
                album.setName(keyValue.getValue().toString());
            }
            if (keyValue.getName().equals("Name")) {
                album.setName(keyValue.getValue().toString());
            }
            if (keyValue.getName().equals("Name")) {
                album.setName(keyValue.getValue().toString());
            }
        }

        albumTable.add(album);
    }

    public void removeGenre(int id) {
        genreTable.remove(id);
    }

    public Genre findGenre(int id) {
        return genreTable.findById(id);
    }

    public List<Genre> findAllGenres() {
        return genreTable.findAll();
    }

    public void printAllStudios() {
        List<Genre> genreList = findAllGenres();

        if(genreList.isEmpty()) {
            System.out.println("There is no genre.");
            return;
        }

        for (Genre genre : genreList) {
            System.out.println("Genre id: " + genre.getId());
            System.out.println("Name: " + genre.getName() + '\n');
        }
    }

    public void printGenre(int id) {
        Genre genre = findGenre(id);
        if(genre == null) {
            System.out.println("There is no genre with this id.");
            return;
        }

        System.out.println("Genre id: " + genre.getId());
        System.out.println("Name: " + genre.getName() + '\n');
    }
}
