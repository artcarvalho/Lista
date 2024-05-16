package artur.renata.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import artur.renata.lista.R;
import artur.renata.lista.model.NewActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Método Construtor dos novos itens
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item); //definindo a tela

        NewActivityViewModel vm = new ViewModelProvider( this ).get( NewActivityViewModel.class );

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        if(selectPhotoLocation != null) {
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }


        ImageButton imgCI = findViewById(R.id.imbCI); //definindo o botão de escolher as imagens
        imgCI.setOnClickListener(new View.OnClickListener() { //declarando um objeto que espera ser selecionado
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); //intenção declarada para escolher a foto
                photoPickerIntent.setType("image/*"); //define a imagem
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); //começa a atividade enquanto esperar o intent retornar um valor
            }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem); //botão de adicionar item a lista
        btnAddItem.setOnClickListener(new View.OnClickListener() { //objeto que vÊ se o botão foi apertado
            @Override
            public void onClick(View v){ //função que acontece caso o click aconteça

                NewActivityViewModel vm = new ViewModelProvider( NewItemActivity.this ).get( NewActivityViewModel.class );

                Uri photoSelected = vm.getSelectPhotoLocation();
                if (photoSelected == null){ //condição que observa se a foto é valida
                    Toast.makeText(NewItemActivity.this, "È necessário Selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle); //declara o titulo
                String title = etTitle.getText().toString();

                if(title.isEmpty()){ //verifica se o titulo é válido
                    Toast.makeText(NewItemActivity.this, "É necessário Inserir um Título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc); //declara a descrição
                String desc = etDesc.getText().toString();

                if(desc.isEmpty()){  //verifica se a descrição é válida
                    Toast.makeText(NewItemActivity.this, "È necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(); //nova intent
                i.setData(photoSelected); //adiciona a foto na intent para ser enviada pra outra tela
                i.putExtra("title", title); //envia o titulo na intent
                i.putExtra("description", desc); //envia a descrição na intent
                setResult(Activity.RESULT_OK, i); //envia OK como resultado na intent
                finish(); //finaliza a tela
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST) {
            if(resultCode == Activity.RESULT_OK){
                Uri photoSelected = data.getData();
                ImageView imvFotoPreview = findViewById(R.id.imvPhotoPreview);
                imvFotoPreview.setImageURI(photoSelected);
                NewActivityViewModel vm = new ViewModelProvider(this).get(NewActivityViewModel.class);
                vm.setSelectPhotoLocation(photoSelected);
            }
        }
    }
}