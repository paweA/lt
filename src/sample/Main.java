package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.Vector;

/**
 * @author Paweł Audycki
 */

public class Main extends Application {

    private static String L="", L_Profit, Lp="", Lp_Profit, C_Bilans;

    /**
     * pobiera pola do ustawiania tekstu
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("sample/sample.fxml"));

        loader.setControllerFactory(c -> new Controller(L, Lp, L_Profit, Lp_Profit ,C_Bilans )); // pobieranie pol do ustawiania tekstu

        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Do you win?");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * pobiera dane, oblicza banals wybranej i zapisuje do pliku
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        File numbers = new File("numery.txt");
        Vector<int []> my_Numers=numbers.read_numbers(); // wektor naszych liczb zapisanych w pliku


        int profitL[]    = new int[] {24, 70 , 7000, 2000000, -3 }; // wygrana dla trafienia 3, 4, 5, 6, i koszt jednego losu
        int profitPlus[] = new int[] {10, 100, 3500, 1000000, -1 };

        Content c1 = new Content("https://www.lotto.pl/lotto");
        Win_Number lotto = new Win_Number(c1.download_content()); // pobranie zawartosci strony

        boolean change_Lotto=false , change_Plus=false;
        String dat[]= new String[5];
        dat[0]="x"; // data
        dat[1]="0"; // bilans L
        dat[2]="0"; // bilans P
        dat[3]="0"; // numery
        dat[4]="0"; // numeryPlus

        File c = new File(dat,"data.txt");
        c.first_read(); // odczyt z pliku

        try {
            dat[3]=lotto.find_number();; // numery
            Win_Number lottoPlus = new Win_Number(lotto);
            dat[4]=lottoPlus.find_number(); // numeryPlus  // najnowsze numery wygrywajace wyszukane w zawartosci strony

        }
        catch (Exception e){System.exit(1); }


        Balance balanceL = new Balance(c,profitL);

        if ( balanceL.check_change(dat[3],3) )  // sparwdzanie czy bylo inne losowanie, numery beda sie do siebie roznic
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
        dat[2]=c.get_Dat()[2]; // bilans P // wpisywanie balansu

        if(change_Lotto || change_Plus) // nowy wynik bedzie uwzgledniony tylko wtedy gdy numery wygrywajace pobrane ze strony roznia sie od tych zapisanych do pliku
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

            File zapis=new File(dat,"data.txt"); // zapis balansu i najnowyszych numerow wygrywajacych do pliku
            zapis.save_new();
        }
        L_Profit = "\nTwój bilans gry w Lotto wynosi: " + dat[1] + "zł";
        Lp_Profit = "\nTwój bilans gry w Lotto wynosi: " + dat[2] + "zł";
        C_Bilans =  "Od dnia: " + dat[0] +"\nCałkowity bilans to : " + (Integer.parseInt(dat[1])+Integer.parseInt(dat[2]) + " zł"); // wypisywanie na ekran wynikow

        launch(args);
    }
}