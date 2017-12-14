package com.digitaldesignuniver.server.UI.view;

import com.digitaldesignuniver.server.UI.MainUI;
import com.digitaldesignuniver.server.app.DriversOnline;
import com.digitaldesignuniver.server.app.RequestList;
import com.digitaldesignuniver.server.app.googleMap.GoogleDirection;
import com.digitaldesignuniver.server.backend.model.Request;
import com.digitaldesignuniver.server.backend.model.Tariff;
import com.digitaldesignuniver.server.backend.service.TaxiServiceImpl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringView
public class CreateView extends VerticalLayout implements View {
    private final DriversOnline driversOnline;
    private VerticalLayout rightPart = new VerticalLayout();
    private VerticalLayout leftPart = new VerticalLayout();
    private NativeSelect<String> tariffSelect;
    private DateTimeField dateTimeField;
    private TextField toAdress;
    private TextField fromAdress;
    private  MainUI mainUI;
    private HorizontalLayout form;
    private VerticalLayout contentLayout;
    private TextField name;
    private TextField phone;
    private TextArea comment;
    private Window subWindow;
    private Request request;
    private Tariff tariff;
    private GoogleDirection googleDirection;
    private TaxiServiceImpl taxiService;
    private RequestList requestList;



    @Autowired
    public CreateView(Request request, Tariff tariff, GoogleDirection googleDirection, TaxiServiceImpl taxiService, RequestList requestList, MainUI mainUI, DriversOnline driversOnline) {
        this.request = request;
        this.tariff = tariff;
        this.googleDirection = googleDirection;
        this.taxiService = taxiService;
        this.requestList = requestList;
        this.mainUI = mainUI;
        this.driversOnline = driversOnline;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }



    @PostConstruct
    void init() {
        createTopLabel();
        form = new HorizontalLayout();
        addComponent(form);
        setComponentAlignment(form,Alignment.MIDDLE_CENTER);
        initWindow();
        initLeftPart();
        initRightPart();
        form.addComponent(leftPart);
        form.addComponent(rightPart);
        createOrderButton();
    }

    /**
     * Add create order button
     */
    private void createOrderButton() {
        Button createOrder = new Button("Создать заказ");
        addComponent(createOrder);
        setComponentAlignment(createOrder,Alignment.MIDDLE_CENTER);
        createOrder.setWidth("500px");
        createOrder.addClickListener((Button.ClickListener) clickEvent -> {
            contentLayout.setSpacing(false);
            contentLayout.removeAllComponents();
            try {
                tariff = taxiService.getTariffByTariffName(tariffSelect.getValue());
                request = new Request();
                request = googleDirection.getOrderInfo(dateTimeField.getValue(),comment.getValue(),
                        name.getValue(),phone.getValue(), fromAdress.getValue(),
                        toAdress.getValue(), tariff);
                contentLayout.addComponent(new Label("Информация по заказу:"));
                contentLayout.addComponent(new Label("Время подачи автомобиля: "+request.getTime()));
                contentLayout.addComponent(new Label("Имя: "+request.getCustomerName()));
                contentLayout.addComponent(new Label("Телефон: "+request.getCustomerPhoneNumber()));
                contentLayout.addComponent(new Label("Откуда: "+request.getAddressFrom()));
                contentLayout.addComponent(new Label("Куда: "+request.getAddressTo()));
                contentLayout.addComponent(new Label("Расстояние: "+request.getDistance()));
                contentLayout.addComponent(new Label("Стоимость: "+request.getPrice()));
                contentLayout.addComponent(new Label("Длительность: "+request.getDuration()));
                contentLayout.addComponent(new Label("Количество сободных водителей: "+driversOnline.freeDrivers()));
                mainUI.addWindow(subWindow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Add right part of creating order page
     */
    private void initRightPart(){
        fromAdress = new TextField();
        fromAdress.setPlaceholder("Откуда..");
        fromAdress.setWidth("500px");
        rightPart.addComponent(fromAdress);

        toAdress = new TextField();
        toAdress.setPlaceholder("Куда..");
        toAdress.setWidth("500px");
        rightPart.addComponent(toAdress);

        dateTimeField = new DateTimeField();
        dateTimeField.setValue(LocalDateTime.now());
        dateTimeField.setRangeStart(LocalDateTime.now());
        dateTimeField.setWidth("500px");
        rightPart.addComponent(dateTimeField);

        String s[] = {"Эконом","Комфорт","Бизнес"};
        List<String> classCar = Arrays.asList(s);
        tariffSelect = new NativeSelect<>("Класс автомобиля",classCar);
        tariffSelect.setEmptySelectionAllowed(false);
        tariffSelect.setSelectedItem(classCar.get(0));
        rightPart.addComponent(tariffSelect);
    }

    /**
     * Add left part of creating order page
     */
    private void initLeftPart(){
        name = new TextField();
        name.setPlaceholder("Имя..");
        name.setWidth("300px");
        leftPart.addComponent(name);

        phone = new TextField();
        phone.setPlaceholder("Телефон..");
        phone.setWidth("300px");
        leftPart.addComponent(phone);

        comment = new TextArea();
        comment.setPlaceholder("Коментарий к заказу..");
        comment.setRows(4);
        comment.setWidth("300px");
        leftPart.addComponent(comment);
    }

    /**
     * Add top of creating order page
     */
    private void createTopLabel() {
        Label title = new Label();
        title.setHeight("100px");
        title.setCaption("Создать новый заказ");
        title.setValue("Введите необходимую информацию");
        addComponent(title);
        setComponentAlignment(title, Alignment.MIDDLE_CENTER);
    }

    /**
     * Add information request window
     */
    private void initWindow() {
        subWindow = new Window("Новый заказ");
        VerticalLayout subContent = new VerticalLayout();
        subWindow.setContent(subContent);
        contentLayout = new VerticalLayout();
        subContent.addComponent(contentLayout);
        Button confirm = new Button("Подтвердить");
        confirm.addClickListener((Button.ClickListener)  clickEvent ->{
            subWindow.close();
            requestList.addRequest(request);
        });
        subContent.addComponent(confirm);
        subWindow.center();
        subWindow.setModal(true);
    }
}
