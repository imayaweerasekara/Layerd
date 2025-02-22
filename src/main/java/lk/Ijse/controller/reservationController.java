package lk.Ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.Ijse.db.HourOfDay;
import lk.Ijse.db.RestaurantEventType;
import lk.Ijse.model.CustomerDto;
import lk.Ijse.model.EmployeeDTO;
import lk.Ijse.model.ReservationDTO;
import lk.Ijse.model.TableDTO;
import lk.Ijse.tm.ReservationTm;
import lk.Ijse.repository.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class reservationController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllreservation();
        setDate();
        loadNextReservatinId();
        getCurrentReservationId();
        getCustomerID();
        getEmployeeID();
        getTableID();
        ObservableList<RestaurantEventType> eventTypes = FXCollections.observableArrayList(RestaurantEventType.values());
        cmbEvent.setItems(eventTypes);
        ObservableList<HourOfDay> starthours = FXCollections.observableArrayList(HourOfDay.values());
        Starttime.setItems(starthours);
        ObservableList<HourOfDay> Endhours = FXCollections.observableArrayList(HourOfDay.values());
        EndTime.setItems(Endhours);
    }

    private void getEmployeeID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = EmployeeRepo.searchById();
            obList.addAll(codeList);
            cmbEmpID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = CustomerRepo.searchById();
            obList.addAll(codeList);
            cmbCusID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getTableID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = TableRepo.searchById();
            obList.addAll(codeList);
            cmbtablenumber.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllreservation() {
        ObservableList<ReservationTm> obList = FXCollections.observableArrayList();

        try {
            List<ReservationDTO> BookingList = ReservationRepo.getAll();
            for (ReservationDTO reservation : BookingList) {
                ReservationTm tm = new ReservationTm(
                        reservation.getReservationId(),
                        reservation.getDate_of_reservation(),
                        reservation.getReserved_date(),
                        reservation.getReserved_time(),
                        reservation.getEmployee_id(),
                        reservation.getTable_Number(),
                        reservation.getCustomer_id(),
                        reservation.getStart_time(),
                        reservation.getEnd_time(),
                        reservation.getEvent()
                );

                obList.add(tm);
            }

            tblreservation.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colReserveID.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colDoR.setCellValueFactory(new PropertyValueFactory<>("date_of_reservation"));
        colReserveDate.setCellValueFactory(new PropertyValueFactory<>("reserved_date"));
        colReserveTime.setCellValueFactory(new PropertyValueFactory<>("reserved_time"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colTNId.setCellValueFactory(new PropertyValueFactory<>("table_Number"));
        colcusId.setCellValueFactory(new PropertyValueFactory<>("Customer_id"));
        colStarttime.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        colEndtime.setCellValueFactory(new PropertyValueFactory<>("end_time"));
        colEvent.setCellValueFactory(new PropertyValueFactory<>("Event"));
    }

    @FXML
    private DatePicker DateOFR;

    @FXML
    private ComboBox<HourOfDay> EndTime;

    @FXML
    private ComboBox<HourOfDay> Starttime;


    @FXML
    private ComboBox<String> cmbCusID;

    @FXML
    private ComboBox<String> cmbEmpID;

    @FXML
    private ComboBox<RestaurantEventType> cmbEvent;

    @FXML
    private ComboBox<String> cmbtablenumber;

    @FXML
    private TableColumn<?, ?> colDoR;

    @FXML
    private TableColumn<?, ?> colEmpID;

    @FXML
    private TableColumn<?, ?> colEndtime;

    @FXML
    private TableColumn<?, ?> colEvent;

    @FXML
    private TableColumn<?, ?> colReserveDate;

    @FXML
    private TableColumn<?, ?> colReserveID;

    @FXML
    private TableColumn<?, ?> colReserveTime;

    @FXML
    private TableColumn<?, ?> colStarttime;

    @FXML
    private TableColumn<?, ?> colTNId;

    @FXML
    private TableColumn<?, ?> colcusId;

    @FXML
    private Label lblRID;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtSearchId;

    @FXML
    private TableView<ReservationTm> tblreservation;


    private void setDate() {
        LocalDate now = LocalDate.now();
    }


    private void loadNextReservatinId() {
        try {
            String currentId = ReservationRepo.getCurrentId();
            String nextId = nextId(currentId);

            lblRID.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("O");
//            System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
            int id = Integer.parseInt(split[1]);    //2
            return "O" + ++id;

        }
        return "O1";
    }

    private void getCurrentReservationId() {
        try {
            String currentId = ReservationRepo.getCurrentId();
            String nextOrderId = generateNextReservationId(currentId);
            lblRID.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextReservationId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("O");
            int idNum = Integer.parseInt(split[1]);
            return "O" + ++idNum;
        }
        return "O1";
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblRID.getText();

        try {
            boolean isDeleted = ReservationRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation deleted!").show();
                loadAllreservation();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnMakeOnAction(ActionEvent event) {
       String ReservationID = lblRID.getText();
       Date doR = Date.valueOf(lblDateofReservation.getText());
       Date Rdate = Date.valueOf(LocalDate.now());
       Time Rtime = Time.valueOf(LocalTime.now());
       String empID= String.valueOf(cmbEmpID.getValue());
       String tableID= String.valueOf(cmbtablenumber.getValue());
       String cusID= String.valueOf(cmbCusID.getValue());
        String start = String.valueOf(Starttime.getValue());
        String end = String.valueOf(EndTime.getValue());
       String Event= String.valueOf(cmbEvent.getValue());
//       Time start= Time.valueOf(Starttime.converterProperty().get().toString());
//       Time end= Time.valueOf(EndTime.converterProperty().get().toString());

        ReservationDTO reservation= new ReservationDTO(ReservationID,doR,Rdate,Rtime,empID,tableID,cusID,start,end,Event);

        try {
            boolean isSaved = ReservationRepo.save(reservation);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation saved!").show();
                loadAllreservation();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String ReservationID = lblRID.getText();
        Date doR = Date.valueOf(lblDateofReservation.getText());
        Date Rdate = Date.valueOf(LocalDate.now());
        Time Rtime = Time.valueOf(LocalTime.now());
        String empID= String.valueOf(cmbEmpID.getValue());
        String tableID= String.valueOf(cmbtablenumber.getValue());
        String cusID= String.valueOf(cmbCusID.getValue());
        String start = String.valueOf(Starttime.getValue());
        String end = String.valueOf(EndTime.getValue());
        String Event= String.valueOf(cmbEvent.getValue());
//       Time start= Time.valueOf(Starttime.converterProperty().get().toString());
//       Time end= Time.valueOf(EndTime.converterProperty().get().toString());

        ReservationDTO reservation= new ReservationDTO(ReservationID,doR,Rdate,Rtime,empID,tableID,cusID,start,end,Event);

        try {
            boolean isUpdated = ReservationRepo.update(reservation);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation updated!").show();
                loadAllreservation();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbCusOnAction(ActionEvent event) throws SQLException {
        String id = cmbCusID.getValue();
        // Lookup employee by ID and update UI
        CustomerDto customer = CustomerRepo.searchById(id);


    }

    @FXML
    void cmbEmpOnAction(ActionEvent event) throws SQLException {
        String id = cmbEmpID.getValue();
        // Lookup employee by ID and update UI
        EmployeeDTO employee = EmployeeRepo.searchById(id);
    }

    @FXML
    void cmbtableNumOnAction(ActionEvent event) throws SQLException {
        String id = cmbtablenumber.getValue();
        // Lookup employee by ID and update UI
        TableDTO table =TableRepo.searchById(id);
    }
    @FXML
    private Label lblDateofReservation;

    @FXML
    void datePickOnAction(ActionEvent event) {
     LocalDate localDate = LocalDate.now();
     localDate=DateOFR.getValue();
     lblDateofReservation.setText(localDate.toString());

    }

    public void cmbstartTimeOnAction(ActionEvent actionEvent) {
        Starttime.setOnAction(event -> {
            // Get the selected item from the ComboBox
            HourOfDay startHour = Starttime.getValue();

            // Perform actions based on the selected hour
            System.out.println("Selected hour: " + startHour.getLabel());
        });

        // Populate cmbstartTime ComboBox with enum values
        Starttime.getItems().addAll(HourOfDay.values());
    }

    public void cmbEndTimeOnAction(ActionEvent actionEvent) {
        EndTime.setOnAction(event -> {
            // Get the selected item from the ComboBox
            HourOfDay EndHour = EndTime.getValue();

            // Perform actions based on the selected hour
            System.out.println("Selected hour: " + EndHour.getLabel());
        });

        // Populate cmbstartTime ComboBox with enum values
        EndTime.getItems().addAll(HourOfDay.values());
    }

    public void cmbsearchIDOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtSearchId.getText(); // Use getText() to get the text value from a TextField, assuming txtSearchId is a TextField

        ReservationDTO reservation = ReservationRepo.searchById(id);
        if (reservation != null) {
            lblRID.setText(reservation.getReservationId());
            lblDateofReservation.setText(reservation.getDate_of_reservation().toString()); // Assuming dateOfReservation is a LocalDate
            cmbEmpID.setValue(reservation.getEmployee_id()); // Assuming cmbEmpID is a ComboBox<String>
            cmbEvent.getSelectionModel().select(RestaurantEventType.valueOf(reservation.getEvent())); // Assuming cmbEvent is a ComboBox<RestaurantEventType>
            cmbCusID.setValue(reservation.getCustomer_id()); // Assuming cmbCusID is a ComboBox<String>
            cmbtablenumber.setValue(reservation.getTable_Number()); // Assuming cmbtablenumber is a ComboBox<String>
            Starttime.getSelectionModel().select(HourOfDay.valueOf(reservation.getStart_time())); // Assuming Starttime is a ComboBox<Time>
            EndTime.getSelectionModel().select(HourOfDay.valueOf(reservation.getEnd_time())); // Assuming EndTime is a ComboBox<Time>
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Reservation not found!").show();
        }
    }

    public void cmbEventOnaction(ActionEvent actionEvent) {
         RestaurantEventType restaurantEventType = cmbEvent.getValue();
    }
}
