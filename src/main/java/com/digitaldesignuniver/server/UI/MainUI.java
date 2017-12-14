package com.digitaldesignuniver.server.UI;

import com.digitaldesignuniver.server.UI.view.*;
import com.digitaldesignuniver.server.UI.view.authentication.BasicAccessControl;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.HashMap;
import java.util.Map;

@SpringUI
@Push
@Theme("myTheme")
public class MainUI extends UI {
    private BasicAccessControl basicAccessControl;
    private CssLayout menu;
    private CssLayout menuItemsLayout;
    private Map<String, Button> viewButtons = new HashMap<String, Button>();
    private MainViewDisplay mainContent;


    @Autowired
    public MainUI(MainViewDisplay mainContent, BasicAccessControl basicAccessControl) {
        setStyleName(ValoTheme.UI_WITH_MENU);
        this.mainContent = mainContent;
        this.basicAccessControl = basicAccessControl;
    }

    @Override
    protected void init(VaadinRequest request) {
        showMainView();
    }

    /**
     * Create main view
     */
    private void showMainView(){
        menu = new CssLayout();
        menu.setHeight("100%");
        menuItemsLayout = new CssLayout();
        menu.setStyleName(ValoTheme.MENU_PART);
        menu.addComponent(menuItemsLayout);
        createNavigationBar();
        logout();
        mainContent.setSizeFull();
        mainContent.setHeight("100%");

        MHorizontalLayout horizontalLayout = new MHorizontalLayout();
        horizontalLayout.add(menu);
        horizontalLayout.expand(mainContent);
        horizontalLayout.withFullHeight();
        horizontalLayout.setStyleName(ValoTheme.UI_WITH_MENU);
        menu.addStyleName(ValoTheme.MENU_PART_LARGE_ICONS);
        setContent(horizontalLayout);
        if(!basicAccessControl.isUserSignedIn()) {
            menu.setVisible(false);
            getNavigator().navigateTo(LoginView.class.getSimpleName().replaceAll("View", "").toLowerCase());
        }else {
            viewButtons.get("Новый заказ").click();
        }
    }


    /**
     * After authorization go to the order window
     */
    public void loginSuccessful(){
        menu.setVisible(true);
        viewButtons.get("Новый заказ").click();
    }

    /**
     * Create logout button
     */
    private void logout(){
        Button button = new Button("Выход");
        button.setIcon(FontAwesome.SIGN_OUT);
        button.addClickListener((Button.ClickListener) clickEvent -> {
            VaadinSession.getCurrent().getSession().invalidate();
            Page.getCurrent().reload();
        });
        button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        menuItemsLayout.addComponent(button);
        viewButtons.put("Выход", button);
    }

    /**
     * Add navigation buttons to the navigation bar
     */
    private void createNavigationBar() {
        createNavButton("Новый заказ", CreateView.class, FontAwesome.ROAD);
        createNavButton("Список заказов", ListView.class,FontAwesome.LIST);
        createNavButton("Найти заказ", SearchView.class,FontAwesome.SEARCH);

    }

    /**
     * Create navigation button
     * @param name name of button
     * @param aClass class of page
     * @param icon icon of button
     */
    private void createNavButton(String name, Class<? extends View> aClass, Resource icon) {
        Button button = new Button(name);
        button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        button.setIcon(icon);
        button.addClickListener(e->getNavigator().navigateTo(aClass.getSimpleName().replaceAll("View", "").toLowerCase()));
        menuItemsLayout.addComponent(button);
        viewButtons.put(name, button);
    }
}
