package cz.ucl.jee.taxi.dao;

import cz.ucl.jee.taxi.entities.Order;
import org.omg.CORBA.INTERNAL;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class Dao {

    private List<Order> orders;
    private Integer numOfRequests;

    public List<Order> getOrders() {
        return orders;
    }

    public int getNumOfRequests() {
        return numOfRequests;
    }

    @PostConstruct
    public void init(){
        orders = new LinkedList<>();
        numOfRequests = 1;
    }

    public boolean saveOrder(Order order){
        if (order.getOrderNumber() == null) {
            order.setOrderNumber(numOfRequests);
        }
        orders.add(order);
        numOfRequests++;
        return true;
    }

    public boolean deleteOrder(Integer orderId){
        Order toBeDeleted = this.findOrderById(orderId);

        if (toBeDeleted == null) {
            return false;
        }
        orders.remove(toBeDeleted);
        return true;
    }

    public Order findOrderById(Integer orderId) {
        for (Order order : orders){
            if (order.getOrderNumber() == orderId){
                return order;
            }
        }
        return null;
    }


}
