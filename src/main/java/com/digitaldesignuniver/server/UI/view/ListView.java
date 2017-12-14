
package com.digitaldesignuniver.server.UI.view;


import com.digitaldesignuniver.server.backend.model.Order;
import com.digitaldesignuniver.server.backend.service.TaxiService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@SpringView
public class ListView extends VerticalLayout implements View {

    private TaxiService taxiService;
    private Grid<Order> grid = new Grid<>();

    @Autowired
    public ListView(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @PostConstruct
    void init() {
        addGrid();
        addComponent(grid);
        setHeight("90%");
        setComponentAlignment(grid, Alignment.TOP_CENTER);
    }

    /**
     * Add grid of orders
     */
    private void addGrid() {
        grid.setWidth("90%");
        grid.setHeight("90%");
        grid.setItems(taxiService.getOrders());
        grid.addColumn(Order::getId).setCaption("Id");
        grid.addColumn(Order::getAddressFrom).setCaption("Откуда");
        grid.addColumn(Order::getAddressTo).setCaption("Куда");
        grid.addColumn(Order::getTariffName).setCaption("Тариф");
        grid.addColumn(Order::getDriverName).setCaption("Водитель");
        grid.clearSortOrder();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
