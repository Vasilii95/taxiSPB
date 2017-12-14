package com.digitaldesignuniver.server.UI.view;

import com.digitaldesignuniver.server.UI.MainUI;
import com.digitaldesignuniver.server.app.RequestList;
import com.digitaldesignuniver.server.app.googleMap.GoogleDirection;
import com.digitaldesignuniver.server.backend.model.Request;
import com.digitaldesignuniver.server.backend.model.Tariff;
import com.digitaldesignuniver.server.backend.service.TaxiServiceImpl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringView
public class SearchView extends VerticalLayout implements View {
    private MainUI mainUI;
    private HorizontalLayout form;
    private NativeSelect<String> tariffSelect;
    private DateTimeField dateTimeField;
    private TextField toAdress;
    private TextField fromAdress;
    private TextField requestId;
    private Window subWindow;
    private Request request;
    private Tariff tariff;
    private GoogleDirection googleDirection;
    private TaxiServiceImpl taxiService;
    private RequestList requestList;
    private Button delete;
    private Button save;

    @Autowired
    public SearchView(Request request, Tariff tariff, GoogleDirection googleDirection, TaxiServiceImpl taxiService, RequestList requestList, MainUI mainUI) {
        this.request = request;
        this.tariff = tariff;
        this.googleDirection = googleDirection;
        this.taxiService = taxiService;
        this.requestList = requestList;
        this.mainUI = mainUI;
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
        VerticalLayout verticalLayout = new VerticalLayout();
        requestId = new TextField();
        requestId.setPlaceholder("Номер заявки");
        requestId.setWidth("500px");
        verticalLayout.addComponent(requestId);
        form.addComponent(verticalLayout);
        searchOrderButton();
    }

    /**
     * Create search order button
     */
    private void searchOrderButton() {
        Button createOrder = new Button("Найти");
        addComponent(createOrder);
        setComponentAlignment(createOrder,Alignment.MIDDLE_CENTER);
        createOrder.setWidth("500px");
        createOrder.addClickListener((Button.ClickListener) clickEvent -> {
            request = new Request();
            request = requestList.findById(new Long(requestId.getValue()));
            if(request!=null){
                toAdress.setValue(request.getAddressTo());
                fromAdress.setValue(request.getAddressFrom());
                dateTimeField.setValue(request.getTime());
                tariffSelect.setValue(request.getTariff().getTariffName());
                if(request.getStatus()) {
                    toAdress.setReadOnly(true);
                    fromAdress.setReadOnly(true);
                    dateTimeField.setReadOnly(true);
                    tariffSelect.setReadOnly(true);
                    save.setVisible(false);
                    if(taxiService.findByRequest(request).getStatus()){
                        delete.setVisible(false);
                    }else {
                        delete.setVisible(true);
                    }
                }else{
                    toAdress.setReadOnly(false);
                    fromAdress.setReadOnly(false);
                    dateTimeField.setReadOnly(false);
                    tariffSelect.setReadOnly(false);
                    save.setVisible(true);
                    delete.setVisible(false);

                }
                mainUI.addWindow(subWindow);
            }
        });
    }

    /**
     * Add top of creating order page
     */
    private void createTopLabel() {
        Label title = new Label();
        title.setHeight("100px");
        title.setValue("Поиск заявок");
        addComponent(title);
        setComponentAlignment(title, Alignment.MIDDLE_CENTER);
    }


    /**
     * Add information order window
     */
    private void initWindow() {
        subWindow = new Window("Заявка");
        VerticalLayout subContent = new VerticalLayout();
        subWindow.setContent(subContent);

        fromAdress = new TextField();
        fromAdress.setPlaceholder("Откуда..");
        fromAdress.setWidth("500px");
        subContent.addComponent(fromAdress);

        toAdress = new TextField();
        toAdress.setPlaceholder("Куда..");
        toAdress.setWidth("500px");
        subContent.addComponent(toAdress);

        dateTimeField = new DateTimeField();
        dateTimeField.setValue(LocalDateTime.now());
        dateTimeField.setWidth("500px");
        subContent.addComponent(dateTimeField);

        String s[] = {"Эконом","Комфорт","Бизнес"};
        List<String> classCar = Arrays.asList(s);
        tariffSelect = new NativeSelect<>("Класс автомобиля",classCar);
        tariffSelect.setEmptySelectionAllowed(false);
        tariffSelect.setSelectedItem(classCar.get(0));
        subContent.addComponent(tariffSelect);

        delete = new Button("Отменить заказ");
        delete.addClickListener((Button.ClickListener)  clickEvent ->{
            if(taxiService.findByRequest(request).getStatus()) {
                showNotification(new Notification("Отменить заказ невозможно",
                        "он уже выполнен",
                        Notification.Type.HUMANIZED_MESSAGE));
            }else {
                requestList.cancelRequest(request);
            }
            subWindow.close();
        });

        save = new Button("Сохранить");
        save.addClickListener((Button.ClickListener)  clickEvent ->{
            Request newRequest = new Request();
            try {
                if(!requestList.findById(new Long(requestId.getValue())).getStatus()){
                    tariff = taxiService.getTariffByTariffName(tariffSelect.getValue());
                    System.out.println(tariff.getTariffName());
                    newRequest = googleDirection.getOrderInfo(dateTimeField.getValue(),request.getComment(),
                            request.getCustomerName(),request.getCustomerPhoneNumber(), fromAdress.getValue(),
                            toAdress.getValue(), tariff);
                    newRequest.setId(request.getId());
                    requestList.addRequest(newRequest);
                }else {
                    showNotification(new Notification("Сохранить изменения невозможно",
                            "на основе этой заявки уже был создан заказ",
                            Notification.Type.HUMANIZED_MESSAGE));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            subWindow.close();
        });
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(delete,save);
        subContent.addComponent(horizontalLayout);
        subWindow.center();
        subWindow.setModal(true);
    }

    /**
     * Error message, if authorization is impossible
     * @param notification
     */
    private void showNotification(Notification notification) {
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }
}
