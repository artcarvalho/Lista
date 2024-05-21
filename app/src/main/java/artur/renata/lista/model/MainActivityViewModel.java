package artur.renata.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel  extends ViewModel { // Um ViewModel basicamente é um container que guarda os dados de uma Activity
    List<MyItem> itens = new ArrayList<>();//mainactivityViewModel guarda a lista de itens cadastrados

    public List<MyItem> getItens() {
        return itens;
    } //Método getter para obter a lista de itens

}
