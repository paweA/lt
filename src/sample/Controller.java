package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {

   private String L, Lp, L_Profit, Lp_Profit, C_Bilans;

    @FXML
    private TextArea L_normal;
    @FXML
    private TextArea L_Plus;
    @FXML
    private TextArea L_Bilans;


    @FXML
    void initialize()
    {
        L_normal.setEditable(false);
        L_normal.setText(L + L_Profit);


        L_Plus.setEditable(false);
        L_Plus.setText(Lp + Lp_Profit);

        L_Bilans.setEditable(false);
        L_Bilans.setText(C_Bilans);
    }

    public Controller(String  L, String Lp, String L_Profit, String Lp_Profit, String C_Bilans)
    {
        this.L=L;
        this.Lp=Lp;
        this.L_Profit=L_Profit;
        this.Lp_Profit=Lp_Profit;
        this.C_Bilans=C_Bilans;
    }
}