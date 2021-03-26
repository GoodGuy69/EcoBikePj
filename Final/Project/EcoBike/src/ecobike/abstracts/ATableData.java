package ecobike.abstracts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public abstract class ATableData {

    public JPanel jPanel5 = new JPanel();

    public JPanel getjPanel5() {
        return jPanel5;
    }

    public void setjPanel5(JPanel jPanel5) {
        this.jPanel5 = jPanel5;
    }
    private JScrollPane scrollTable = new JScrollPane();

    public ATableData(String[] col, JTable table) {

        @SuppressWarnings("serial")
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        table.setModel(tableModel);

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(scrollTable, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );
        jPanel5.setBounds(0, 120, 540, 480);
        scrollTable.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(0).setMaxWidth(30);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setMaxWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(260);
        }
    }
}
