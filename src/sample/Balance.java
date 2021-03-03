package sample;

public class Balance
{
    private String dat[];
    private int dataArr[]=new int[6];
    private int profit_Type[];
    private int profit=0;


    /**
     * sparwdza czy numery wygrywajace sie od siebie roznia
     * @param s
     * @param i
     * @return
     */
    public boolean check_change(String s,int i)
    {
        if(!dat[i].equals(s)) // sparwdzanie czy numery wygrywajace sie od siebie roznia
        {
            dat[i]=s;
            to_int_arr(i);

            return true;
        }

        to_int_arr(i);
        return false;
    }


    /**
     *  konwertuje dane o wielkosci n do tablicy int-ow
     * @param n
     */

    private void to_int_arr(int n)
    {
        String dat_tmp=dat[n];
        String integerStrings[] = dat_tmp.split(" ");

        for (int i=0;i<6;i++)
        {
            dataArr[i]=Integer.parseInt(integerStrings[i*2]);
        }
    }

    /**
     * sprawdzanie trafien
     * @param my_numer1
     * @return napisy o wygranej
     */
    public String count_profit(int my_numer1[])
    {
        byte wins=0;
        for (int element : dataArr)
        {
            for (int element2 : my_numer1) // sprawdzanie trafien
            {
                if (element == element2)
                {
                    wins++;
                }
            }
        }

        String tmp;
        tmp="Wyniki podawane dla numerów : ";
        for (int elem: my_numer1)
            tmp += Integer.toString(elem) + " ";

        if(wins>=3) // zysk jest dodawany tylko wtedy gdy trafimy wiecej niz 3
        {
            tmp+="\nTrafiłes "+ wins + "\n";
            profit += profit_Type[wins-3];
        }
        else
        {
            tmp+="\nNic nie trafiłeś. Jak zwykle\n";
        }
        profit += profit_Type[profit_Type.length - 1];

        return tmp;
    }

    /**
     *
     * @return zwraca zysk
     */
    public int getProfit()
    {
        return profit;
    }

    public Balance(File c,int profit_Type[])
    {
        this.dat=c.get_Dat();
        this.profit_Type=profit_Type;
    }
}