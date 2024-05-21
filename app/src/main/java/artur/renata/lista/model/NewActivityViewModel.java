package artur.renata.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewActivityViewModel extends ViewModel{

    Uri selectPhotoLocation = null; //aqui estamos guardando somente o endereço URI da foto que foi escolhida pelo usuário, garantindo que a imagem escolhida contibuará aparecendo mesmo que gire a tela enquanto newItemActivity estiver funcionando

    public Uri getSelectPhotoLocation(){
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
        // Há também um método getter para obter a lista de itens (linha 11) e um método de setter para setar o endereço URI dentro do ViewModel
    }
}
