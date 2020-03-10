package sample;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Content
{

    private String out;
    private String url;

    public String download_content()
    {
        try
                (
                        InputStream openStream = new URL(url).openStream();
                        Scanner scanner = new Scanner(openStream, "UTF-8");
                )
        {
            out = scanner.useDelimiter("\\A").next();
        }
        catch (IOException ignored)
        {
        }
        return out;
    }

    public String get_content()
    {
        return out;
    }

    public Content (String url)
    {
        this.url=url;
    }
}