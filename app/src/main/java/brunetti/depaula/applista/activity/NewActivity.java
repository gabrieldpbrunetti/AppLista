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

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(photoSelected == null){
                    Toast.makeText(NewActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(NewActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()){
                    Toast.makeText(NewActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
        });

        ImageButton imgCI = findViewById(R.id.imgCI);
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if (requestCode == PHOTO_PICKER_REQUEST) {
                if (resultCode == Activity.RESULT_OK) {
                    photoSelected = data.getData();
                    ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
                    imvfotoPreview.setImageURI(photoSelected);
                }
            }
        }
    }


    }

