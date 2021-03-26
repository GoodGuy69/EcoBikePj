package ecobike.user.returns;

import ecobike.abstracts.AListPane;

import javax.swing.*;

public class ReturnBike extends AListPane {

    private String[] colTable = new String[]{"ID", "Name", "Address", "No Empty Dock"};
    private ReturnBikeController returnBikeController;
    private JButton btnBack = new JButton();
    private JButton btnPayment = new JButton();

    public ReturnBike(String title) {
        super(title);
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    public JButton getBtnPayment() {
        return btnPayment;
    }

    public void setBtnPayment(JButton btnPayment) {
        this.btnPayment = btnPayment;
    }

    public void init() {
        super.init();
        returnBikeController = new ReturnBikeController(this);
        ReturnBikeData tableData = new ReturnBikeData(colTable, table);
        listPane.add(tableData.getjPanel5());
        listPaneController.getDataTable(table, "select * from stations");

        btnBack.setText("Back");
        btnBack.setBounds(100, 620, 100, 40);
        listPane.add(btnBack);
        returnBikeController.btnBack();

        btnPayment.setText("Payment");
        btnPayment.setBounds(320, 620, 100, 40);
        listPane.add(btnPayment);
        returnBikeController.selectStation();

    }
}
