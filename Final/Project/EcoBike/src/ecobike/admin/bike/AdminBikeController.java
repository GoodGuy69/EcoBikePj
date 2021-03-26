package ecobike.admin.bike;

import ecobike.abstracts.AListPaneController;
import ecobike.bean.Bike;
import ecobike.database.SQLDatabase;
import ecobike.login.AdminPane;
import ecobike.login.HomePanel;
import ecobike.main.MainController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AdminBikeController extends AListPaneController {

    protected AdminBike bikePane;
    public BikeInforAdd bikeAddPane;
    public BikeInforUpdate bikeUpdatePane;

    private AdminBikeInputValidator validator;

    public AdminBikeController(String title) {
        super();

        bikePane = new AdminBike(title);
        bikePane.setListPaneController(this);

        validator = new AdminBikeInputValidator();
    }

    public AdminBike getBikePane() {
        return bikePane;
    }

    public JTable getTable() {
        return bikePane.getTable();
    }

    @Override
    public void getDataTable(JTable table) {
        String query = "SELECT * FROM bikes";
        ArrayList<Bike> bikes = SQLDatabase.GetInstance().getAllBike(query);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < bikes.size(); i++) {
            row[0] = bikes.get(i).getBikeID();
            row[1] = bikes.get(i).getClass().getSimpleName();
            row[2] = bikes.get(i).getLicensePlate();
            row[3] = bikes.get(i).getProducer();
            model.addRow(row);
        }
    }

    public void getBackToLoginScreen() {
        HomePanel loginScreen = new HomePanel();
        loginScreen.init();
        MainController.GetInstance().navigate(loginScreen.homescreen);
    }

    public void getBackToAdminScreen() {
        AdminPane adminPane = new AdminPane();
        adminPane.init();
        adminPane.switchToBikeTab();
        MainController.GetInstance().navigate(adminPane.getPane());
    }

    public void deletebike(JTable table) {

        if (!table.getSelectionModel().isSelectionEmpty()) {
            int selectedRow = table.getSelectedRow();
            int selectedBikeID = (int) table.getValueAt(selectedRow, 0);
            if (validator.ifBikeDeletionInvalid( selectedBikeID)) {
                JOptionPane.showMessageDialog(bikePane.getListPane(), "Cannot delete, this bike is currently rented!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                SQLDatabase.GetInstance().deleteBike(selectedBikeID);
                this.getDataTable(table);
            }
        }
    }

    public void createUpdateBikePane(JTable table) {
        if (!table.getSelectionModel().isSelectionEmpty()) {
            int selectedRow = table.getSelectedRow();
            int bikeId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

            Bike selectedBike = SQLDatabase.GetInstance().getBikeById(bikeId);

            bikeUpdatePane = new BikeInforUpdate();
            bikeUpdatePane.setBikeController(this);
            bikeUpdatePane.init(selectedBike);
            MainController.GetInstance().navigate(bikeUpdatePane.getBikeInfoPane());
        }
    }

    public Boolean updateBike(Bike bike) {
        Boolean updateSuccess = true;

        try {
            Bike updatedBike = bikeUpdatePane.returnBike();

            if (validator.ifNameNotUniqueAfterEdit( bike.getBikeName(), updatedBike.getBikeName())) {
                JOptionPane.showMessageDialog(bikeUpdatePane.getBikeInfoPane(), "Bike with same name already existed", "Error", JOptionPane.ERROR_MESSAGE);
                updateSuccess = false;
            }

            if (validator.ifLicenseNotUniqueAfterEdit(bike.getLicensePlate(), updatedBike.getLicensePlate())) {
                JOptionPane.showMessageDialog(bikeUpdatePane.getBikeInfoPane(), "Bike with same license plate already existed", "Error", JOptionPane.ERROR_MESSAGE);
                updateSuccess = false;
            }

            if (updateSuccess) {
                SQLDatabase.GetInstance().updateBike(updatedBike);
                this.getBackToAdminScreen();
            }

        } catch (NullPointerException | NumberFormatException e) {
            updateSuccess = false;
            JOptionPane.showMessageDialog(bikeUpdatePane.getBikeInfoPane(), "Invalid input, try again!", "Can't update bike", JOptionPane.ERROR_MESSAGE);
        }

        return updateSuccess;
    }

    public void createAddBikePane() {
        bikeAddPane = new BikeInforAdd();
        bikeAddPane.setBikeController(this);
        bikeAddPane.init();
        MainController.GetInstance().navigate(bikeAddPane.getBikeInfoPane());
    }

    public void addBike() {
        Boolean addSuccess = true;

        try {
            Bike newBike = bikeAddPane.returnBike();
            if (validator.ifNewNameUnique(newBike.getBikeName())) {
                JOptionPane.showMessageDialog(bikeAddPane.getBikeInfoPane(), "Bike with same name already existed", "Error", JOptionPane.ERROR_MESSAGE);
                addSuccess = false;
            }
            if (validator.ifNewLicenseUnique(newBike.getLicensePlate())) {
                JOptionPane.showMessageDialog(bikeAddPane.getBikeInfoPane(), "Bike with same license plate already existed", "Error", JOptionPane.ERROR_MESSAGE);
                addSuccess = false;
            }

            if (addSuccess) {
                SQLDatabase.GetInstance().addBike(newBike);
                this.getBackToAdminScreen();
            }
        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(bikeAddPane.getBikeInfoPane(), "Invalid input, try again!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
