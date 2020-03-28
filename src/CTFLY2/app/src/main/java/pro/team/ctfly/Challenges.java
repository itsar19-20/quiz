package pro.team.ctfly;

import java.util.ArrayList;
import java.util.List;

import Model.ChallengeAppSide;

public class Challenges {
    private List<ChallengeAppSide> list=new ArrayList<>();
    public Challenges(){
        for (String [] arr:data){
            list.add(new ChallengeAppSide(arr[0]));
        }

    }
    public  List<ChallengeAppSide> getList(){
        return  list;
    }
    private static  final String [][] data={
            {"Basic injection"},
            {"Programming"},

    };

}
