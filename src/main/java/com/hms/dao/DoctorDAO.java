package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hms.entity.Doctor;

public class DoctorDAO {

    private Connection conn;

    public DoctorDAO(Connection conn) {
        if (conn == null) {
            System.out.println("❌ DoctorDAO received a null connection.");
        } else {
            System.out.println("✅ DoctorDAO connected.");
        }
        this.conn = conn;
    }

    public boolean registerDoctor(Doctor doctor) {
        boolean f = false;
        String sql = "INSERT INTO doctor(fullName, dateOfBirth, qualification, specialist, email, phone, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doctor.getFullName());
            pstmt.setString(2, doctor.getDateOfBirth());
            pstmt.setString(3, doctor.getQualification());
            pstmt.setString(4, doctor.getSpecialist());
            pstmt.setString(5, doctor.getEmail());
            pstmt.setString(6, doctor.getPhone());
            pstmt.setString(7, doctor.getPassword());

            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public List<Doctor> getAllDoctor() {
        List<Doctor> docList = new ArrayList<>();
        String sql = "SELECT * FROM doctor ORDER BY id DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setFullName(rs.getString("fullName"));
                doctor.setDateOfBirth(rs.getString("dateOfBirth"));
                doctor.setQualification(rs.getString("qualification"));
                doctor.setSpecialist(rs.getString("specialist"));
                doctor.setEmail(rs.getString("email"));
                doctor.setPhone(rs.getString("phone"));
                doctor.setPassword(rs.getString("password"));
                docList.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return docList;
    }

    public Doctor getDoctorById(int id) {
        Doctor doctor = null;
        String sql = "SELECT * FROM doctor WHERE id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    doctor = new Doctor();
                    doctor.setId(rs.getInt("id"));
                    doctor.setFullName(rs.getString("fullName"));
                    doctor.setDateOfBirth(rs.getString("dateOfBirth"));
                    doctor.setQualification(rs.getString("qualification"));
                    doctor.setSpecialist(rs.getString("specialist"));
                    doctor.setEmail(rs.getString("email"));
                    doctor.setPhone(rs.getString("phone"));
                    doctor.setPassword(rs.getString("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctor;
    }

    public boolean updateDoctor(Doctor doctor) {
        boolean f = false;
        String sql = "UPDATE doctor SET fullName=?, dateOfBirth=?, qualification=?, specialist=?, email=?, phone=?, password=? WHERE id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doctor.getFullName());
            pstmt.setString(2, doctor.getDateOfBirth());
            pstmt.setString(3, doctor.getQualification());
            pstmt.setString(4, doctor.getSpecialist());
            pstmt.setString(5, doctor.getEmail());
            pstmt.setString(6, doctor.getPhone());
            pstmt.setString(7, doctor.getPassword());
            pstmt.setInt(8, doctor.getId());

            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public boolean deleteDoctorById(int id) {
        boolean f = false;
        String sql = "DELETE FROM doctor WHERE id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public Doctor loginDoctor(String email, String password) {
        Doctor doctor = null;
        String sql = "SELECT * FROM doctor WHERE email=? AND password=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    doctor = new Doctor();
                    doctor.setId(rs.getInt("id"));
                    doctor.setFullName(rs.getString("fullName"));
                    doctor.setDateOfBirth(rs.getString("dateOfBirth"));
                    doctor.setQualification(rs.getString("qualification"));
                    doctor.setSpecialist(rs.getString("specialist"));
                    doctor.setEmail(rs.getString("email"));
                    doctor.setPhone(rs.getString("phone"));
                    doctor.setPassword(rs.getString("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctor;
    }

    public int countTotalDoctor() {
        return countRows("SELECT COUNT(*) FROM doctor");
    }

    public int countTotalAppointment() {
        return countRows("SELECT COUNT(*) FROM appointment");
    }

    public int countTotalAppointmentByDoctorId(int doctorId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM appointment WHERE doctorId=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public int countTotalUser() {
        return countRows("SELECT COUNT(*) FROM user_details");
    }

    public int countTotalSpecialist() {
        return countRows("SELECT COUNT(*) FROM specialist");
    }

    private int countRows(String sql) {
        int count = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean checkOldPassword(int doctorId, String oldPassword) {
        boolean f = false;
        String sql = "SELECT 1 FROM doctor WHERE id=? AND password=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            pstmt.setString(2, oldPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                f = rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public boolean changePassword(int doctorId, String newPassword) {
        boolean f = false;
        String sql = "UPDATE doctor SET password=? WHERE id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, doctorId);
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public boolean editDoctorProfile(Doctor doctor) {
        boolean f = false;
        String sql = "UPDATE doctor SET fullName=?, dateOfBirth=?, qualification=?, specialist=?, email=?, phone=? WHERE id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doctor.getFullName());
            pstmt.setString(2, doctor.getDateOfBirth());
            pstmt.setString(3, doctor.getQualification());
            pstmt.setString(4, doctor.getSpecialist());
            pstmt.setString(5, doctor.getEmail());
            pstmt.setString(6, doctor.getPhone());
            pstmt.setInt(7, doctor.getId());

            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}
