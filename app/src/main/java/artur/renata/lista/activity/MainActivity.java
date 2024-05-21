package artur.renata.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import artur.renata.lista.R;
import artur.renata.lista.adapter.MyAdapter;
import artur.renata.lista.model.MainActivityViewModel;
import artur.renata.lista.model.MyItem;
import artur.renata.lista.util.Util;

public class MainActivity extends AppCompatActivity {


    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>(); //criando uma lista do tipo MyItem chamada itens


    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddNewItem = findViewById(R.id.fabAddNewItem); //criando um botão para adicionar novos itens
        fabAddNewItem.setOnClickListener(new View.OnClickListener() { //escutador do botão
            @Override
            public void onClick(View v) { //função chamada quando o botão flutuante é apertado
                Intent i = new Intent(MainActivity.this, NewItemActivity.class); //criando nova intent
                startActivityForResult(i, NEW_ITEM_REQUEST); //espera a intent acima retornar o resultado
            }
        });

        RecyclerView rvItens = findViewById(R.id.rvItens); //define um conjunto visual de lista de itens

        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItens();
        // O Viewmodel referente a mainActvityViewModel é obtido. Em seguida, a lista de itens é obtida a parir do Viewmodel e repassada para o Adapter

        myAdapter = new MyAdapter(this, itens); //cria um objeto da classe MyAdapter
        rvItens.setAdapter(myAdapter); //configurando display da lista
        rvItens.setHasFixedSize(true); //definindo o valor da lista como fixo

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST){ //verifica se o resultado é 1
            if(resultCode == Activity.RESULT_OK){ //verifica se está tudo ok


                MyItem myItem = new MyItem(); //cria novo objeto da classe MyItem
                myItem.title = data.getStringExtra("title"); // define o titulo
                myItem.description = data.getStringExtra("description"); //define a descrição
                Uri selectedPhotoUri = data.getData();

                try{
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoUri, 100, 100);
                    myItem.photo = photo;
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                List<MyItem> itens = vm.getItens();
                //obtemos em seguida a lista de itens que o viewModel guarda e guardamos o novo item dentro dessa lista. dessa forma, os itens não estão mais guardados em mainActivity, mas sim em mainActivityViewModel

                itens.add(myItem); // adiciona o item com todos seus valores definidos À tela principal
                myAdapter.notifyItemInserted(itens.size()-1); //notifica que o item foi adicionado para que seja redesenhado no display
            }
        }
    }
}
