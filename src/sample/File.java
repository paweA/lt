package sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class File
{
    private String dat[]=new String[5];

    private String date()
    {
        Date nowDate = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

        return sd.format(nowDate);
    }

    public void first_read() throws FileNotFoundException
    {
        java.io.File data = new java.io.File("data.txt");
        if (data.exists())
        {
            Scanner scanner = new Scanner(data);

            for (int i = 0; i < 5; i++)
                dat[i] = scanner.nextLine();
        }
        else
        {
            dat[0]=date();
            save_new();
        }
    }

    public void save_new() throws FileNotFoundException
    {
        PrintWriter save = new PrintWriter("data.txt");

        for (int i = 0; i < 5; i++)
            save.println(dat[i]);

        save.close();
    }

    public String[] get_Dat()
    {
        return dat;
    }

    File( String dat[]) throws FileNotFoundException
    {
        System.arraycopy(dat, 0, this.dat, 0, 5);
    }
}