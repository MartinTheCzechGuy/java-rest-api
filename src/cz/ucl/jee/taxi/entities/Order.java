package cz.ucl.jee.taxi.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Order {

    private Integer orderNumber;
    private String addressFrom;
    private String addressTo;
    private int orderPrice;
    private Integer taxiNumber;
    private LocalDateTime arrivalTime;


    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setTaxiNumber(Integer taxiNumber) {
        this.taxiNumber = taxiNumber;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setArrivalTime(String arrivalTime) {
        LocalDateTime requestedArrivaltime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(arrivalTime));
        LocalDateTime timeNow = LocalDateTime.now();
        timeNow = timeNow.withNano(0);
        timeNow = timeNow.withSecond(0);
        this.arrivalTime = requestedArrivaltime;
        if (requestedArrivaltime.equals(timeNow)){
            this.arrivalTime = this.arrivalTime.plusMinutes(10);
        }
    }

    public Integer getTaxiNumber() {
        return taxiNumber;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
}
