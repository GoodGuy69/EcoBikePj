package ecobike.abstracts;

import javax.swing.*;

public abstract class AListPane {

    protected JPanel listPane = new JPanel(null);
    private String title;
    protected AListPaneController listPaneController;
    protected JTable table = new JTable();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public AListPaneController getListPaneController() {
        return listPaneController;
    }

    public void setListPaneController(AListPaneController listPaneController) {
        this.listPaneController = listPaneController;
    }

    public AListPane(String title) {
        this.title = title;
    }

    public void init() {

        JPanel jpTitle = new JPanel();
        JLabel lbTitle = new JLabel();
        JPanel jPanel3 = new JPanel();
        JTextField tfSearch = new JTextField();

        jpTitle.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpTitle.setLayout(new java.awt.GridLayout());

        lbTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setText(this.title);
        jpTitle.add(lbTitle);
        jpTitle.setBounds(0, 0, 540, 50);
        listPane.add(jpTitle);

        jPanel3.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel3.setLayout(new java.awt.GridLayout());
        
        tfSearch.setBounds(230, 70, 300, 30);
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listPaneController.getSearchParam(tfSearch.getText(), table);
            }
        });
        listPane.add(tfSearch);

    }

    public JPanel getListPane() {
        return listPane;
    }

    public void setListPane(JPanel listPane) {
        this.listPane = listPane;
    }

}
