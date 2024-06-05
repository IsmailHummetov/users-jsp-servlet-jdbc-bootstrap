package com.example.resumeweb.dao.inter;

import com.example.resumeweb.bean.EmpHistory;

import java.util.List;

public interface UserEmpHistoryDaoInter {
    public List<EmpHistory> getAll();

    public EmpHistory getEmpHistoryByUserId(int userId);
}
