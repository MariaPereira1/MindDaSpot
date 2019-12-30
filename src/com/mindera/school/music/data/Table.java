package com.mindera.school.music.data;

import com.mindera.school.music.ui.SQLConnection;

import static com.mindera.school.music.services.Services.SQL_CONNECTION;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
    protected String table;
    protected SQLConnection sql;

    public Table(String table) {
        this.table = table;
        this.sql = SQL_CONNECTION;
    }

    public void removeById(int id) throws SQLException {
        sql.statement.executeUpdate("CALL delete_" + table + "(" + id + ");");
    }

    public void removeByName(String name) throws SQLException {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        ResultSet resultSet = sql.con.prepareCall("CALL get_" + table + "_id_by_name('" + name + "');").executeQuery();
        if (resultSet.next()) {
            sql.con.prepareCall("CALL delete_" + table + "(" + resultSet.getInt(1) + ");").execute();
        }
    }

    public int findIdByName(String name) throws SQLException {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        ResultSet resultSet = sql.con.prepareCall("CALL get_" + table + "_id_by_name('" + name + "');").executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(table + "_id");
        }
        return 0;
    }

    public boolean verifyIfExistsName(String name) throws SQLException {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        ResultSet resultSet = sql.con.prepareCall("CALL get_" + table + "_id_by_name('" + name + "');").executeQuery();

        return resultSet.next();
    }
}