package sample;

public class Win_Number
{
    private String content;
    private String results = "";
    private int position = 0;

    public String find_number()
    {
        position=content.indexOf("resultnumber",position);

        String tmp="";
        int ileZN;

        for (int i = 0; i < 6; i++) {

            outerloop:
            while(position < content.length())
            {
                for (int j = 1; j <= 9; j++)
                {
                    if ( content.charAt(position) == (char)(j+'0') )
                        break outerloop;
                }
                position++;
            }

            if (content.charAt(position + 1) == ' ')
                ileZN = 1;
            else
                ileZN = 2;

            tmp = content.substring(position, position + ileZN);

            position+=2;
            if (i == 0)
                results = results + tmp;
            else
                results = results + " , " + tmp;
        }
        return results;
    }

    public String get_Results()
    {
        return results;
    }

    public Win_Number(String content)
    {
        this.content=content;
    }

    public Win_Number(Win_Number lott)
    {
        this.content=lott.content;
        this.position=lott.position;
    }
}