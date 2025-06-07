package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hms.entity.Specialist;

public class SpecialistDAO {

    private Connection conn;

    public SpecialistDAO(Connection conn) {
        this.conn = conn;
    }

    // Method to add a specialist
    public boolean addSpecialist(String sp) {
        boolean success = false;

        try {
            String sql = "INSERT INTO specialist (specialist_name) VALUES (?)";
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, sp);
            pstmt.executeUpdate();
            success = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    // Method to fetch all specialists
    public List<Specialist> getAllSpecialist() {
        List<Specialist> spList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM specialist";
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Specialist specialistObj = new Specialist();
                specialistObj.setId(rs.getInt(1));                // id
                specialistObj.setSpecialistName(rs.getString(2)); // specialist_name
                spList.add(specialistObj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return spList;
    }
}
