package sample;

public class Win_Number
{
    private String content;
    private String results = "";
    private int position = 0;

    /**
     * szuka numerow wygrywajacych w pliku pobranym z internetu
     * @return aktualnie najnowsze numery wygrywajace
     * @throws IndexOutOfBoundsException
     */
    public String find_number() throws  IndexOutOfBoundsException
    {
        position=content.indexOf("result-item__balls-box",position); // slowo kluczowe po ktorym beda podane wygrywajace numery
        position=content.indexOf("\n",position);


       // position+=90;
        String tmp="";
        int ileZN;

        for (int i = 0; i < 6; i++) { // szukamy 6 liczb

            outerloop: //
            while(position < content.length()) // wyjscie z petli nastepuje gdy zostanie znaleziona liczba
            {

                for (int j = 1; j <= 9; j++)
                {
                    if ( content.charAt(position) == (char)(j+'0') )
                        break outerloop;
                }

                position++;

            }

            if (content.charAt(position + 1) == '\n')
                ileZN = 1;
            else
                ileZN = 2;

            tmp = content.substring(position, position + ileZN);


            position+=90; // omijanie niepotrzebnych linii

            if (i == 0)
                results = results + tmp;
            else
                results = results + " , " + tmp;
        }
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