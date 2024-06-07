package com.example.resumeweb.Data.dao.impl;

import com.example.resumeweb.Data.bean.Country;
import com.example.resumeweb.Data.bean.IdPassword;
import com.example.resumeweb.Data.bean.User;
import com.example.resumeweb.Data.dao.inter.AbstractDao;
import com.example.resumeweb.Data.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    public User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String ProfileDescription = rs.getString("profile_description");
        Date BirthDate = rs.getDate("birth_date");
        int birthPlaceID = rs.getInt("birth_place");
        int nationalityID = rs.getInt("nationality");
        String address = rs.getString("address");
        String birthPlaceName = rs.getString("name");
        String nationalityName = rs.getString("nationality_name");
        Country birthplace = new Country(birthPlaceID, birthPlaceName, null);
        Country nationality = new Country(nationalityID, null, nationalityName);
        User u = new User(id, firstname, lastname, email, phone, ProfileDescription, BirthDate, birthplace, nationality, address);
        return u;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        String sql = "SELECT\n" +
                "\t`user`.*,\n" +
                "\tc.`name`,\n" +
                "\tn.nationality AS nationality_name\n" +
                "FROM\n" +
                "\t`user`\n" +
                "\tLEFT JOIN country AS c ON c.id = `user`.birth_place\n" +
                "\tLEFT JOIN country AS n ON n.id = `user`.nationality ";
        try (Connection c = connection()) {
            Statement stmt = c.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<User> getByNameSurname(String firstname,String lastname) {
        List<User> result = new ArrayList<>();
        String sql = "SELECT\n" +
                "\t`user`.*,\n" +
                "\tc.`name`,\n" +
                "\tn.nationality AS nationality_name\n" +
                "FROM\n" +
                "\t`user`\n" +
                "\tLEFT JOIN country AS c ON c.id = `user`.birth_place\n" +
                "\tLEFT JOIN country AS n ON n.id = `user`.nationality " +
                "WHERE 1=1";
        try (Connection c = connection()) {
            if (firstname!=null && !firstname.trim().isEmpty())
                sql+=" and `user`.firstname=?";
            if (lastname!=null && !lastname.trim().isEmpty())
                sql+=" and `user`.lastname=?";
            PreparedStatement stmt = c.prepareStatement(sql);

            int i=1;
            if (firstname!=null && !firstname.trim().isEmpty()){
                stmt.setString(i,firstname);
                i++;
            }
            if (lastname!=null && !lastname.trim().isEmpty()){
                stmt.setString(i,lastname);
                i++;
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getbyId(int UserId) {
        User user = null;
        String sql = "SELECT\n" +
                "\t`user`.*,\n" +
                "\tc.`name`,\n" +
                "\tn.nationality AS nationality_name \n" +
                "FROM\n" +
                "\t`user`\n" +
                "\tLEFT JOIN country AS c ON c.id = `user`.birth_place\n" +
                "\tLEFT JOIN country AS n ON n.id = `user`.nationality \n" +
                "WHERE\n" +
                "\t`user`.id = ?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, UserId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                user = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public IdPassword getPasswordByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=?";
        String password = null;
        Integer id=null;
        try(Connection c = connection()){
            PreparedStatement stmt  = c.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                password = rs.getString("password");
                id=rs.getInt("id");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new IdPassword(password,id);
    }


    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO USER ( firstname, lastname, email, phone, profile_description, birth_date, birth_place, nationality, address )\n" +
                "VALUES\n" +
                "\t(?,?,?,?,?,?,?,?,?)";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getProfileDescription());
            stmt.setDate(6, user.getBirthDate());
            stmt.setInt(7, user.getbirthPlace().getId());
            stmt.setInt(8, user.getNationality().getId());
            stmt.setString(9, user.getAddress());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE `user` \n" +
                "SET firstname =?,\n" +
                "lastname =?,\n" +
                "email =?,\n" +
                "phone =?,\n" +
                "profile_description =?,\n" +
                "birth_date =?,\n" +
                "birth_place =?,\n" +
                "nationality =?,\n" +
                "address =? \n" +
                "WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getProfileDescription());
            stmt.setDate(6, user.getBirthDate());
            stmt.setInt(7, user.getbirthPlace().getId());
            stmt.setInt(8, user.getNationality().getId());
            stmt.setString(9, user.getAddress());
            stmt.setInt(10, user.getId());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(int UserId) {
        String sql = "DELETE FROM `user` WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, UserId);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
