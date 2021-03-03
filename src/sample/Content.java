package sample;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class Content
{

    private String out;
    private String url;

    /**
     * pobiera plik .html ze strony
     * @return tekst ze strony internetowej
     */
    public String download_content()
    {
        try
                (
                        InputStream openStream = new URL(url).openStream();
                        Scanner scanner = new Scanner(openStream, "UTF-8"); // pobieranie zawartosci strony z internetu
                )
        {
            out = scanner.useDelimiter("\\A").next();
        }
        catch (Exception e)
        {
            System.exit(1);
        }
        return out;
    }


    public Content (String url)
    {
        this.url=url;
    }
}