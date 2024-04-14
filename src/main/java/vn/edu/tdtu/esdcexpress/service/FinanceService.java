package vn.edu.tdtu.esdcexpress.service;

import vn.edu.tdtu.esdcexpress.model.Finance;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;

public interface FinanceService {
    public Iterable<Finance> getFinancesByUsername(User user);
//    public Iterable<Finance> getFinancesByOrderID(Order order);
    public Finance findById(Long id);
    public Finance save(Finance finance);
}
