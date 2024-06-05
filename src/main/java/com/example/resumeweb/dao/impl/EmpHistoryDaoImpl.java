package com.example.resumeweb.dao.impl;

import com.example.resumeweb.bean.EmpHistory;
import com.example.resumeweb.bean.User;
import com.example.resumeweb.dao.inter.AbstractDao;
import com.example.resumeweb.dao.inter.EmpHistoryDaoInter;
import com.example.resumeweb.dao.inter.UserDaoInter;
import com.example.resumeweb.main.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpHistoryDaoImpl extends AbstractDao implements EmpHistoryDaoInter {
    public EmpHistory getEmpHistory(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_description");
        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getbyId(rs.getInt("user_id"));

        return new EmpHistory(id, header, beginDate, endDate, jobDescription, u);
    }

    @Override
    public List<EmpHistory> getAll() {
        List<EmpHistory> empHistoryList = new ArrayList<>();
        String sql = "SELECT * FROM employment_history";
        try (Connection c = connection()) {
            Statement stmt = c.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                empHistoryList.add(getEmpHistory(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return empHistoryList;
    }

    @Override
    public EmpHistory getById(int id) {
        EmpHistory empHistory = null;
        String sql = "SELECT * FROM employment_history WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                empHistory = getEmpHistory(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return empHistory;
    }

    @Override
    public boolean addEmpHistory(EmpHistory emp) {
        String sql = "INSERT employment_history  (header , job_description , begin_date  , end_date, user_id ) VALUES (? , ? , ? , ? , ?)";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, emp.getHeader());
            stmt.setString(2, emp.getJobDescription());
            stmt.setDate(3, emp.getBeginDate());
            stmt.setDate(4, emp.getEndDate());
            stmt.setInt(5, emp.getUser().getId());
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmpHistory(EmpHistory emp) {
        String sql = "UPDATE employment_history SET header=? , job_description=? , begin_date = ? , end_date = ?, user_id = ? WHERE id= ?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, emp.getHeader());
            stmt.setString(2, emp.getJobDescription());
            stmt.setDate(3, emp.getBeginDate());
            stmt.setDate(4, emp.getEndDate());
            stmt.setInt(5, emp.getUser().getId());
            stmt.setInt(6, emp.getId());
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmpHistory(int id) {
        String sql = "DELETE FROM employment_history WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
