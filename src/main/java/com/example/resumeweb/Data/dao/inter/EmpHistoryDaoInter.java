package com.example.resumeweb.Data.dao.inter;

import com.example.resumeweb.Data.bean.EmpHistory;

import java.util.List;

public interface EmpHistoryDaoInter {
    List<EmpHistory> getAll();

    EmpHistory getById(int id);

    boolean addEmpHistory(EmpHistory emp);

    boolean updateEmpHistory(EmpHistory emp);

    boolean deleteEmpHistory(int id);
}
