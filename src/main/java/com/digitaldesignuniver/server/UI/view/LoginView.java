package com.digitaldesignuniver.server.UI.view;

import com.digitaldesignuniver.server.UI.MainUI;
import com.digitaldesignuniver.server.UI.view.authentication.AccessControl;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView
public class LoginView extends VerticalLayout implements View {

    private TextField username;
    private PasswordField password;
    private Button login;
    private MainUI mainUI;
    private AccessControl accessControl;

    @Autowired
    public LoginView(MainUI mainUI, AccessControl accessControl) {
        this.mainUI = mainUI;
        this.accessControl = accessControl;
    }


    @PostConstruct
    void init() {
        setStyleName("login-screen");
        FormLayout loginForm = buildLoginForm();
        setHeight("100%");
        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName("centeringLayout");
        centeringLayout.addComponent(loginForm);
        centeringLayout.setHeight("200px");
        centeringLayout.setWidth("400px");
        centeringLayout.setComponentAlignment(loginForm,
                Alignment.MIDDLE_CENTER);
        addComponent(centeringLayout);
        setComponentAlignment(centeringLayout,
                Alignment.MIDDLE_CENTER);
    }

    /**
     * Build login form
     * @return created login form
     */
    private FormLayout buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.setSizeUndefined();
        loginForm.setMargin(false);

        loginForm.addComponent(username = new TextField("Логин"));
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField("Пароль"));
        password.setWidth(15, Unit.EM);

        CssLayout buttons = new CssLayout();
        loginForm.addComponent(buttons);
        buttons.addComponent(login = new Button("Войти"));
        login.setDisableOnClick(true);
        login.addClickListener((Button.ClickListener) event -> {
            try {
                login();
            } finally {
                login.setEnabled(true);
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        return loginForm;
    }

    /**
     * Authorization, and if authorization is impossible output an error message
     */
    private void login() {
        if (accessControl.signIn(username.getValue(), password.getValue())) {
            mainUI.loginSuccessful();
        } else {
            showNotification(new Notification("Вход невозможен",
                    "Проверьте правильность логина/пароля",
                    Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
        }
    }

    /**
     * Error message, if authorization is impossible
     * @param notification
     */
    private void showNotification(Notification notification) {
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public Button getLogin() {
        return login;
    }

    public void setLogin(Button login) {
        this.login = login;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
