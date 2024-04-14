package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.Finance;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.FinanceRepository;

@Service
public class FinanceServiceImpl implements FinanceService{
    @Autowired
    FinanceRepository financeRepository;
    @Override
    public Iterable<Finance> getFinancesByUsername(User user) {
        return financeRepository.findByUserUsername(user.getUsername());
    }

    /*@Override
    public Iterable<Finance> getFinancesByOrderID(Order order) {
        return financeRepository.findByOrderOrderID(order.getId());
    }*/

    @Override
    public Finance findById(Long id) {
        return financeRepository.findById(id).orElse(null);
    }

    @Override
    public Finance save(Finance finance) {
        return financeRepository.save(finance);
    }
}
