package com.example.destiny.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.destiny.R;
import com.example.destiny.domain.area.Guild;

public class HireAdventurerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hire_adventurer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.submitButton);


        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hireAdventurer(v);
                    }
                }
        );
    }

    private void hireAdventurer(View view) {
        // get adventurer name from text box
        EditText adventurerNameInput = findViewById(R.id.adventurerNameTextBox);

        // get selected radio button
        RadioGroup classTypeRadioGroup = findViewById(R.id.radioGroup);
        RadioButton classTypeRadio = findViewById(classTypeRadioGroup.getCheckedRadioButtonId());
        classTypeRadio.getText();

        if(TextUtils.isEmpty(adventurerNameInput.getText()))
        {
            adventurerNameInput.setError("Adventurer name is required");
        }
        else
        {
            Guild.getInstance().createAdventurer(adventurerNameInput.getText().toString(), classTypeRadio.getText().toString());
            finish();
        }

    }
}