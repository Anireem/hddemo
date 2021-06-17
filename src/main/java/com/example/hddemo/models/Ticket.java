package com.example.hddemo.models;

import com.example.hddemo.dao.CustomerDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name, date;
    @Builder.Default
    private Boolean completed = false;
    private Long customerId;

    public String getCustomerById(Long id) {

        try {
            Class.forName("org.postgresql.Driver");
            String sqlUrl = "jdbc:postgresql://localhost:5432/hd";
            String sqlUser = "postgres";
            String sqlPassword = "postgres";
            Connection connectionDb = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);
            Statement statement = connectionDb.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("select name from customers where id = " + String.valueOf(id));

            resultSet.next();
            return resultSet.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
