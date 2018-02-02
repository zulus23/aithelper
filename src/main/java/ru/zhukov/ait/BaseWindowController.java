package ru.zhukov.ait;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import ru.zhukov.ait.dao.ApplicationDataService;
import ru.zhukov.ait.dao.ApplicationService;
import ru.zhukov.ait.domain.Enterprise;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseWindowController implements Initializable {

    private ApplicationService  applicationService;
    private ApplicationDataService applicationDataService;

    @FXML
    private ComboBox<Enterprise> aitEnterprise;

    public BaseWindowController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(applicationService.listEnterprise());
        aitEnterprise.getItems().addAll(applicationService.listEnterprise());
        aitEnterprise.setConverter(new StringConverter<Enterprise>() {
            @Override
            public String toString(Enterprise enterprise) {
                return enterprise.getNameEnterprise();
            }

            @Override
            public Enterprise fromString(String enterpriseName) {
                return aitEnterprise.getItems().filtered(e-> e.getNameEnterprise().equals(enterpriseName)).get(0);
            }
        });
        aitEnterprise.getSelectionModel().selectedItemProperty().addListener(this::enterpriseChanged);

    }



    private void enterpriseChanged(ObservableValue<? extends  Enterprise> observable,Enterprise oldEnterprise,Enterprise newEnterprise) {
         //TODO Need create new source data for had selected enterprise instance
         applicationDataService =  applicationService.createDataService(newEnterprise);
    }
}
