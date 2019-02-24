package CirnoMod.Generic;

public class Keyword
{
    public String[] NAMES;
    public String PROPERNAME;
    public String DESCRIPTION;

    public String toString(){
        String res = "Proper Name: ";
        res += PROPERNAME;
        res += "; Names: ";
        for(int i = 0; i < NAMES.length; ++i)
        {
            res += NAMES[i];
            res += "; ";
        }
        res += "Desc: ";
        res += DESCRIPTION;
        return res;
    }
};