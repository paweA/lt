package sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Balance
{
    private String dat[];
    private int dataArr[]=new int[6];
    private int profit_Type[];
    private int profit=0;


    public boolean check_change(String s,int i)
    {
        if(!dat[i].equals(s))
        {
            dat[i]=s;
            to_int_arr(i);

            return true;
        }

        to_int_arr(i);
        return false;
    }


    private void to_int_arr(int n)
    {
        String dat_tmp=dat[n];
        String integerStrings[] = dat_tmp.split(" ");

        for (int i=0;i<6;i++)
        {
            dataArr[i]=Integer.parseInt(integerStrings[i*2]);
        }
    }

    public String count_profit(int my_numer1[])
    {
        byte wins=0;
        for (int element : dataArr)
        {
            for (int element2 : my_numer1)
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

        if(wins>=3)
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