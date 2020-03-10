package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Main extends Application {

    private static String L="", L_Profit, Lp="", Lp_Profit, C_Bilans;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("sample/sample.fxml"));

        loader.setControllerFactory(c -> new Controller(L, Lp, L_Profit, Lp_Profit ,C_Bilans ));

        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Do you win?");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {

            Vector<int []> my_Numers= new Vector<>();
        my_Numers.add(new int[] {4, 8 , 15 ,16 ,23 ,42} );
        my_Numers.add(new int[] {1 , 6 , 24,18 ,23 ,47} );
        my_Numers.add(new int[] {8 , 30, 27,18 ,29 ,20} );
        my_Numers.add(new int[] {2, 12, 33,17 ,18 ,19} );

        int profitL[]    = new int[] {24, 70 , 7000, 2000000, -3 };
        int profitPlus[] = new int[] {10, 100, 3500, 1000000, -1 };

        Content c1 = new Content("https://www.lotto.pl/lotto");
        Win_Number lotto = new Win_Number(c1.download_content());

        boolean change_Lotto=false , change_Plus=false;
        String dat[]= new String[5];
        dat[0]="x"; // data
        dat[1]="0"; // bilans L
        dat[2]="0"; // bilans P
        dat[3]="0"; // numery
        dat[4]="0"; // numeryPlus

        File c = new File(dat);
        c.first_read();

        dat[3]=lotto.find_number();; // numery
        Win_Number lottoPlus = new Win_Number(lotto);
        dat[4]=lottoPlus.find_number(); // numeryPlus

        Balance balanceL = new Balance(c,profitL);

        if ( balanceL.check_change(dat[3],3) )
            change_Lotto=true;

        L +="Najnowsze numery wygrywające dla Lotto : " + dat[3]+"\n\n";
        for (int my_Numer[] : my_Numers)
            L += balanceL.count_profit(my_Numer) +"\n";

        Balance balancePlus = new Balance(c,profitPlus);

        if ( balancePlus.check_change(dat[4],4) )
            change_Plus=true;

        Lp += "Najnowsze numery wygrywające dla Lotto Plus : " + dat[4]+"\n\n";
        for (int my_Numer[] : my_Numers)
            Lp += balancePlus.count_profit(my_Numer) +"\n";


        dat[0]=c.get_Dat()[0]; // data
        dat[1]=c.get_Dat()[1]; // bilans L
        dat[2]=c.get_Dat()[2]; // bilans P

        if(change_Lotto || change_Plus)
        {
            int Bl = 0;
            int Bp = 0;

            if(change_Lotto && change_Plus)
            {
                Bl = balanceL.getProfit() + Integer.parseInt(dat[1]);
                Bp = balancePlus.getProfit() + Integer.parseInt(dat[2]);
            }
            else
            {
                if (change_Lotto)
                {
                    Bl = balanceL.getProfit() + Integer.parseInt(dat[1]);
                    Bp = Integer.parseInt(dat[2]);
                }
                else
                {
                    Bl = Integer.parseInt(dat[1]);
                    Bp = balancePlus.getProfit() + Integer.parseInt(dat[2]);
                }
            }

            dat[1] = Integer.toString(Bl);
            dat[2] = Integer.toString(Bp);

            File zapis=new File(dat);
            zapis.save_new();
        }
        L_Profit = "\nTwój bilans gry w Lotto wynosi: " + dat[1] + "zł";
        Lp_Profit = "\nTwój bilans gry w Lotto wynosi: " + dat[2] + "zł";
        C_Bilans =  "Całkowity bilans to : " + (Integer.parseInt(dat[1])+Integer.parseInt(dat[2]) + " zł");

        launch(args);
    }
}