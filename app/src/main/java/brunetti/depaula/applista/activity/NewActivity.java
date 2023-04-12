package brunetti.depaula.applista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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


import brunetti.depaula.applista.R;

public class NewActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button btnAddItem = findViewById(R.id.btnAddItem);//capturando o botao de adicionar item

        //adicionando o evento de clique ao botao de adicionar
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Verifica se existe uma foto selecionado, senão mostra um aviso na tela do usuário*/
                if(photoSelected == null){
                    Toast.makeText(NewActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);//captura o editText referente ao titulo
                String title = etTitle.getText().toString();//captura a string do titulo

                /*Verifica se o titulo está vazio, se estiver mostra um aviso na tela do usuário*/
                if(title.isEmpty()) {
                    Toast.makeText(NewActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);//captura o editText referente a descrição
                String description = etDesc.getText().toString();//captura a descricao da descricao

                /*Verifica se a descricao está vazia, se estiver mostra um avisao ao usuario*/
                if (description.isEmpty()){
                    Toast.makeText(NewActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();//cria uma intent
                i.setData(photoSelected);//adiciona a intent a foto selecionada
                i.putExtra("title", title);//adiciona a string title aos dados da intent
                i.putExtra("description", description);//adiciona a string description
                setResult(Activity.RESULT_OK, i);//com a activity iniciada pelo método setActivityForResult, este método retorna o resultado desta activity e passa a intent como segundo parametro
                finish();//finaliza a activity

            }
        });

        ImageButton imgCI = findViewById(R.id.imgCI);//capturando a botao de imagem
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//Inicializando uma intent que tem como acao abrir um documento
                photoPickerIntent.setType("image/*");//define que a intent ira abrir um documento do tipo imagem
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);//inicia a intent esperando um resultado, passa um id de identificação de activity
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            //verifica se o codigo de requisicao é igual ao codigo definido para esta activity
            if (requestCode == PHOTO_PICKER_REQUEST) {
                //verifica se a activity gerou o resultado certo
                if (resultCode == Activity.RESULT_OK) {
                    photoSelected = data.getData();//define que a foto selecionada e igual a da intent passada por parâmetro
                    ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);//captura a ImageView referente a preview da imagem
                    imvfotoPreview.setImageURI(photoSelected);//define que a imagem exbida e a imagem caputarada da intent
                }
            }
        }
    }


    }

