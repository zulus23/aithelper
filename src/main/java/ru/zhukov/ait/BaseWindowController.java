package ru.zhukov.ait;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import ru.zhukov.ait.dao.ApplicationDataService;
import ru.zhukov.ait.dao.ApplicationService;
import ru.zhukov.ait.domain.Enterprise;
import ru.zhukov.ait.domain.Order;
import ru.zhukov.ait.domain.TypeOrder;
import ru.zhukov.ait.domain.TypeVacation;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class BaseWindowController implements Initializable {

    private ApplicationService  applicationService;
    private ApplicationDataService applicationDataService;

    @FXML
    private TableView<Order> prOrderView;
    @FXML
    private ComboBox<Enterprise> aitEnterprise;
    @FXML
    private ComboBox<TypeOrder> aitOrderType;

    @FXML
    private DatePicker aitOrderDateBegin;

    @FXML
    private TableColumn<Order,LocalDate> prDateOrder;
    @FXML
    private TableColumn<Order,LocalDate> prNumber;
    @FXML
    private TableColumn<Order,String> prEmployee;
    @FXML
    private TableColumn<Order,Boolean> prStatus;
    @FXML
    private TableColumn<Order,LocalDate> prDateBegin;
    @FXML
    private TableColumn<Order,LocalDate> prDateEnd;
    @FXML
    private TableColumn<Order,String> prTypeVacation;

    @FXML
    private Button bChangeStatusCalculate;



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
        aitOrderType.setConverter(new StringConverter<TypeOrder>() {
            @Override
            public String toString(TypeOrder object) {
                return object.getName().trim();
            }

            @Override
            public TypeOrder fromString(String orderName) {
                return aitOrderType.getItems().filtered(o-> o.getName().equals(orderName)).get(0);
            }
        });


        aitEnterprise.getSelectionModel().selectedItemProperty().addListener(this::enterpriseChanged);
        aitOrderType.getSelectionModel().selectedItemProperty().addListener(this::orderTypeChanged);

        prNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        prDateOrder.setCellValueFactory(new PropertyValueFactory<>("date"));
        prDateBegin.setCellValueFactory(new PropertyValueFactory<>("dateBegin"));
        prDateEnd.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        prEmployee.setCellValueFactory(param -> {
            return  new ReadOnlyStringWrapper(param.getValue().getEmployee().getFullName());
        });
        aitOrderDateBegin.setValue(LocalDate.now());
        aitOrderDateBegin.setOnAction(event -> {
            LocalDate localDateBegin = aitOrderDateBegin.getValue().with(TemporalAdjusters.firstDayOfMonth());
            LocalDate localDateEnd = localDateBegin.with(TemporalAdjusters.lastDayOfMonth());
            fillOrderTableView(aitOrderType.getSelectionModel().getSelectedItem(), localDateBegin, localDateEnd);
        });
        prStatus.setCellValueFactory(param -> {
            Optional<Order> order = Optional.ofNullable(param.getValue());
            return  new ReadOnlyBooleanWrapper(order.map(o->o.getStatusCalculate()).orElse(false) );
        });
        prStatus.setCellFactory(CheckBoxTableCell.<Order>forTableColumn(prStatus));
        prTypeVacation.setCellValueFactory(param -> {
            Optional<TypeVacation> optional = Optional.ofNullable(param.getValue().getTypeVacation());
            return new ReadOnlyStringWrapper(optional.map(t -> t.getDescription()).orElse(""));
        });
        bChangeStatusCalculate.setOnAction(event -> {
            Order order = prOrderView.getSelectionModel().getSelectedItem();
           Optional.ofNullable(applicationDataService.changeMarkCalculate(order))
                   .ifPresent(o -> {
                     order.setStatusCalculate(o.getStatusCalculate());
            });
            prOrderView.refresh();

        });

    }

    private void orderTypeChanged(ObservableValue<? extends TypeOrder> observable,TypeOrder oldType, TypeOrder newType) {
        LocalDate localDateBegin = aitOrderDateBegin.getValue().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate localDateEnd = localDateBegin.with(TemporalAdjusters.lastDayOfMonth());

        fillOrderTableView(newType, localDateBegin, localDateEnd);

    }

    private void fillOrderTableView(TypeOrder newType, LocalDate localDateBegin, LocalDate localDateEnd) {
        CompletableFuture.supplyAsync(()->applicationDataService.listOrderByTypeAndDateBeginBetween(newType,localDateBegin,localDateEnd))
                         .thenAccept((order)-> {
                             Platform.runLater(()-> {
                                 prOrderView.getItems().clear();
                                 prOrderView.getItems().addAll(order);
                             });

                         });
    }


    private void enterpriseChanged(ObservableValue<? extends  Enterprise> observable,Enterprise oldEnterprise,Enterprise newEnterprise) {
         //TODO Need create new source data for had selected enterprise instance
         applicationService.createDataService(newEnterprise)
                           .whenComplete((ctx,err)-> {
                             applicationDataService = ctx;
                             Platform.runLater(()-> {
                                 aitOrderType.getItems().clear();
                             });
                           }).thenApplyAsync(ApplicationDataService::listTypeOrder)
                            .whenComplete((l,error)->{
                              if(Objects.nonNull(error )){
                                  Platform.runLater(()->{
                                      Alert alert = new Alert(Alert.AlertType.ERROR, error.toString());
                                      alert.setTitle("Error");
                                      alert.show();
                                  });
                              } else{
                              Platform.runLater(()->{
                                  aitOrderType.getItems().addAll(l);
                                  aitOrderType.getSelectionModel().select(0);
                              });
                            }
                                    });




       // System.out.println(applicationDataService.listTypeOrder());
    }
}
