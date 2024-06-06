package com.example.resumeweb.Data.dao.impl;

import com.example.resumeweb.Data.bean.EmpHistory;
import com.example.resumeweb.Data.bean.User;
import com.example.resumeweb.Data.dao.inter.AbstractDao;
import com.example.resumeweb.Data.dao.inter.UserDaoInter;
import com.example.resumeweb.Data.dao.inter.UserEmpHistoryDaoInter;
import com.example.resumeweb.Data.main.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserEmpHistoryDaoImpl extends AbstractDao implements UserEmpHistoryDaoInter {
    public EmpHistory getEmpHistory(ResultSet rs) throws Exception {
        int id = rs.getInt("eh_id");
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_description");
        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getbyId(rs.getInt("id"));
        EmpHistory empHistory = new EmpHistory(id, header, beginDate, endDate, jobDescription, u);
        return empHistory;
    }

    @Override
    public List<EmpHistory> getAll() {
        List<EmpHistory> empHistoryList = new ArrayList<>();
        String sql = "SELECT\n" +
                "\t`user`.*,\n" +
                "\teh.id AS eh_id,\n" +
                "\teh.header,\n" +
                "\teh.begin_date,\n" +
                "\teh.end_date,\n" +
                "\teh.job_description \n" +
                "FROM\n" +
                "\t`user`\n" +
                "\tLEFT JOIN employment_history AS eh ON eh.user_id = `user`.id";
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
    public EmpHistory getEmpHistoryByUserId(int userId) {
        EmpHistory empHistory = null;
        String sql = "SELECT\n" +
                "\t`user`.*,\n" +
                "\teh.id AS eh_id,\n" +
                "\teh.header,\n" +
                "\teh.begin_date,\n" +
                "\teh.end_date,\n" +
                "\teh.job_description \n" +
                "FROM\n" +
                "\t`user`\n" +
                "\tLEFT JOIN employment_history AS eh ON eh.user_id = `user`.id \n" +
                "WHERE\n" +
                "\t`user`.id = ?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, userId);
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
}
