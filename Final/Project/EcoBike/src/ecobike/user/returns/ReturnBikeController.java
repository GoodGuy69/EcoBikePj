package ecobike.user.returns;

import ecobike.abstracts.AListPaneController;
import ecobike.bean.Station;
import ecobike.database.SQLDatabase;
import ecobike.main.MainController;
import ecobike.user.payment.PaymentController;
import ecobike.user.rent.RentBike;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ReturnBikeController extends AListPaneController {

    private ReturnBike returnBike;

    public ReturnBikeController() {
        super();
    }

    public ReturnBikeController(ReturnBike returnBike) {
        this.returnBike = returnBike;
    }

    @Override
    public void getDataTable(JTable table, String query) {
        ArrayList<Station> station = SQLDatabase.GetInstance().getStation(query);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < station.size(); i++) {
            row[0] = station.get(i).getStationID();
            row[1] = station.get(i).getStationName();
            row[2] = station.get(i).getStationAddress();
            row[3] = getNoDock(station.get(i));
            model.addRow(row);
        }
    }

    public int getNoDock(Station station) {
        String id = Integer.toString(station.getStationID());
        int countNoTwinBike = SQLDatabase.GetInstance().countBikeInStation("TwinBike", id);
        int countNoEcoBike = SQLDatabase.GetInstance().countBikeInStation("EcoBike", id);
        int countNoBike = SQLDatabase.GetInstance().countBikeInStation("NormalBike", id);
        return station.getNoDock() - countNoBike - countNoEcoBike - countNoTwinBike;

    }

    public void selectStation() {
        JTable table = returnBike.getTable();
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectRow = table.getSelectedRow();
                int emptyDock = Integer.parseInt(model.getValueAt(selectRow, 3).toString());
                if (emptyDock != 0) {
                    returnBike.getBtnPayment().addActionListener(e -> {
                        PaymentController paymentPane = new PaymentController(Integer.parseInt(model.getValueAt(selectRow, 0).toString()));
                        paymentPane.initialize();
                        MainController.GetInstance().navigate(paymentPane.getPaymentPane());
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Station is full", "ERROR: ", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    public void btnBack() {
        returnBike.getBtnBack().addActionListener(e -> {
            RentBike rentBike = new RentBike(0);
            rentBike.init();
            MainController.GetInstance().navigate(rentBike.getStationInfor());

        });
    }
}
