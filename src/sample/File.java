package sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class File
{
    private String dat[]=new String[5];
    private String name_file;

    /**
     * pobiera dane o podanych numerach
     * @return wektor wlasnych numerow z pliku
     */
  public Vector<int []> read_numbers()
  {
      java.io.File data = new java.io.File(name_file);
      Vector<int []> my_Numers= new Vector<>(); // odczyt wlasnych numerow z pliku

      if (data.exists())
      {
          Scanner scanner= null;
          try { scanner = new Scanner(data); } catch (FileNotFoundException e) {System.exit(1); }

          int[] tab = new int[6] ;
          for(int i=0;scanner.hasNext();)
          {
              try { tab[i]=scanner.nextInt(); } catch (Exception e) {System.exit(1); }


              if(i==5)
              {

                  my_Numers.add(Arrays.copyOf(tab, tab.length ));
                  i=0;
                  continue;
              }
              i++;
          }
          return my_Numers;
      }
      System.exit(1);
      return my_Numers;
  }

    /**
     * formatowanie daty
     * @return data w formacie dd-MM-yyyy
     */
    private String date()
    {
        Date nowDate = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy"); // formatowanie daty

        return sd.format(nowDate);
    }

    /**
     * odczyt numerow wygrywajcaych i daty oraz tworzenie pliku jesli nie istnieje
     * @throws FileNotFoundException
     */
    public void first_read() throws FileNotFoundException
    {
        java.io.File data = new java.io.File(name_file); // odczyt numerow wygrywajcaych i daty
        if (data.exists())
        {
            Scanner scanner = new Scanner(data);

            for (int i = 0; i < 5; i++)
                dat[i] = scanner.nextLine();
        }
        else // tworzenie pliku jesli nie istnieje
        {
            dat[0]=date();
            save_new();
        }
    }

    /**
     * zapis balansu i numerow do pliku
     * @throws FileNotFoundException
     */
    public void save_new() throws FileNotFoundException // zapis balansu i numerow
    {
        PrintWriter save = new PrintWriter(name_file);

        for (int i = 0; i < 5; i++)
            save.println(dat[i]);

        save.close();
    }

    /**
     *
     * @return zapisana date pierwszego uruchomienia
     */
    public String[] get_Dat()
    {
        return dat;
    }

    public File( String dat[],String name_file)
    {
        System.arraycopy(dat, 0, this.dat, 0, 5);
        this.name_file=name_file;
    }

    public File(String name_file)
    {
        this.name_file=name_file;
    }
}