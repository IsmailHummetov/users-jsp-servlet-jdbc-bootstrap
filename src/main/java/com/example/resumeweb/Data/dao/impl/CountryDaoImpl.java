package com.example.resumeweb.Data.dao.impl;

import com.example.resumeweb.Data.bean.Country;
import com.example.resumeweb.Data.dao.inter.CountryDaoInter;
import com.example.resumeweb.Data.dao.inter.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {
    public Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");
        return new Country(id, name, nationality);
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM country";
        try (Connection c = connection()) {
            Statement stmt = c.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                countries.add(getCountry(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countries;
    }

    @Override
    public Country getById(int CountryId) {
        String sql = "SELECT * FROM country WHERE id=?";
        Country country = null;
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, CountryId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                country = getCountry(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean addCountry(Country country) {
        String sql = "INSERT INTO country ( NAME, nationality )\n" +
                "VALUES\n" +
                "\t(?,?)";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, country.getBirthPlace());
            stmt.setString(2, country.getNationality());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCountry(Country country) {
        String sql = "UPDATE country SET name=?,nationality=? WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, country.getBirthPlace());
            stmt.setString(2, country.getNationality());
            stmt.setInt(3, country.getId());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCountry(int CountryId) {
        String sql = "DELETE FROM country WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, CountryId);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
