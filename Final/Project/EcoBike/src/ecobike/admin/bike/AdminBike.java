package ecobike.admin.bike;

import ecobike.abstracts.AListPane;
import ecobike.user.bike.detail.BikeTableData;

import javax.swing.*;

public class AdminBike extends AListPane {

    private JButton logout = new JButton();
    private JButton deleteBike = new JButton();
    private JButton btnEdit = new JButton();
    private JButton btnAdd = new JButton();

    private String[] colTable = new String[]{"ID", "Type", "License Plate", "Producer"};

    public AdminBike(String title) {
        super(title);
    }

    public void init() {
        super.init();
        BikeTableData tableData = new BikeTableData(colTable, table);
        listPane.add(tableData.getjPanel5());
        listPaneController.getDataTable(table);

        logout.setText("Log out");

        logout.setBounds(40, 605, 100, 40);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ((AdminBikeController) listPaneController).getBackToLoginScreen();
            }

        });

        deleteBike.setText("Delete");

        deleteBike.setBounds(160, 605, 100, 40);
        deleteBike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ((AdminBikeController) listPaneController).deletebike(table);
            }

        });
        listPane.add(deleteBike);

        btnEdit.setText("Edit");

        btnEdit.setBounds(280, 605, 100, 40);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ((AdminBikeController) listPaneController).createUpdateBikePane(table);
            }

        });
        listPane.add(btnEdit);

        btnAdd.setText("Add New");
        btnAdd.setBounds(400, 605, 100, 40);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ((AdminBikeController) listPaneController).createAddBikePane();
            }
        });
        listPane.add(btnAdd);

        listPane.add(logout);
    }
}
