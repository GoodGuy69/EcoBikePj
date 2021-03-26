package ecobike.admin.dockstation;

import ecobike.abstracts.AListPane;
import ecobike.bean.Station;
import ecobike.database.SQLDatabase;
import ecobike.login.AdminPane;
import ecobike.login.HomePanel;
import ecobike.main.MainController;
import ecobike.user.station.list.StationTableData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminDockStation extends AListPane {

    private JButton logout = new JButton();
    private JButton deleteStation = new JButton();
    private JButton btnEdit = new JButton();
    private JButton btnAdd = new JButton();
//    IDatabaseSQL sql = new SQLDatabase();

    private String[] colTable = new String[]{"ID", "Name", "Address", "Total_dock"};

    public AdminDockStation(String title) {
        super(title);
    }

    public void init() {
        super.init();
        String query = "select * from stations";
        StationTableData tableData = new StationTableData(colTable, table);
        listPane.add(tableData.getjPanel5());
        listPaneController.getDataTable(table, query);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectRow = table.getSelectedRow();
                Station station = SQLDatabase.GetInstance().getAStation(model.getValueAt(selectRow, 0).toString());
            }
        });

        logout.setText("Log out");

        logout.setBounds(40, 605, 100, 40);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomePanel loginScreen = new HomePanel();
                loginScreen.init();
                MainController.GetInstance().navigate(loginScreen.homescreen);
            }

        });
        listPane.add(logout);

        deleteStation.setText("Delete");
        deleteStation.setBounds(160, 605, 100, 40);
        deleteStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int selectedRow = table.getSelectedRow();
                int selectedStationID = (int) table.getValueAt(selectedRow, 0);

                if (SQLDatabase.GetInstance().getAStation(String.valueOf(selectedStationID)).getNoDock() != 0) {

                    int countNoTwinBike = SQLDatabase.GetInstance().countBikeInStation("TwinBike", Integer.toString(selectedStationID));
                    int countNoEcoBike = SQLDatabase.GetInstance().countBikeInStation("EcoBike", Integer.toString(selectedStationID));
                    int countNoBike = SQLDatabase.GetInstance().countBikeInStation("NormalBike", Integer.toString(selectedStationID));

                    if ((countNoBike != 0) || (countNoEcoBike != 0) || (countNoTwinBike != 0)) {
                        JOptionPane.showMessageDialog(listPane, "Cannot delete, this station has bikes!");
                    } else {
                        SQLDatabase.GetInstance().deleteStation(selectedStationID);
                        JOptionPane.showMessageDialog(listPane, "Success to delete!");
                        AdminPane adminPane = new AdminPane();
                        adminPane.init();
                        adminPane.setShowIndexTabPanel(1);
                        MainController.GetInstance().navigate(adminPane.getPane());
                    }
                } else {
                    SQLDatabase.GetInstance().deleteStation(selectedStationID);
                    JOptionPane.showMessageDialog(listPane, "Success to delete!");
                    AdminPane adminPane = new AdminPane();
                    adminPane.init();
                    adminPane.setShowIndexTabPanel(1);
                    MainController.GetInstance().navigate(adminPane.getPane());
                }
            }
        });
        listPane.add(deleteStation);

        btnEdit.setText("Edit");
        btnEdit.setBounds(280, 605, 100, 40);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int selectedRow = table.getSelectedRow();
                    String stationId = table.getValueAt(selectedRow, 0).toString();

                    Station selectedStation = SQLDatabase.GetInstance().getAStation(stationId);

                    DockStationInforUpdate updatePane = new DockStationInforUpdate();
                    updatePane.init(listPaneController, selectedStation);
                    MainController.GetInstance().navigate(updatePane.getStationInfoPane());
                }
            }

        });
        listPane.add(btnEdit);

        btnAdd.setText("Add");
        btnAdd.setBounds(400, 605, 100, 40);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DockStationInforAdd addPane = new DockStationInforAdd();
                addPane.init(listPaneController);
                MainController.GetInstance().navigate(addPane.getStationInfoPane());
            }
        });
        listPane.add(btnAdd);

    }
}
