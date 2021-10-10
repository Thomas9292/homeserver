package com.thomaswesselink.homeserver.dao;

import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TodoRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSX")
                    .parse(resultSet.getString("dateCreated"));
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
            date = new Date();
        }
        return new Todo(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("title"),
                resultSet.getString("description"),
                Boolean.parseBoolean(resultSet.getString("isDone")),
                date
        );
    }
}
