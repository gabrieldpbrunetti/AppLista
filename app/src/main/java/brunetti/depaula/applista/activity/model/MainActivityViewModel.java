package brunetti.depaula.applista.activity.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import brunetti.depaula.applista.activity.MyItem;

public class MainActivityViewModel extends ViewModel {

    List<MyItem> itens = new ArrayList<>();

    public List<MyItem> getItens(){
        return itens;
    }
}
