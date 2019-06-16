package com.example.patienthistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.example.patienthistory.room.dao.AllergiesDao;
import com.example.patienthistory.room.entities.Allergies;
import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.viewmodels.AllergiesViewModel;
import com.example.patienthistory.room.viewmodels.PatientViewModel;

import org.w3c.dom.Text;

import java.util.OptionalLong;

public class MainActivity extends AppCompatActivity {

    private PatientViewModel patientViewModel;
    private AllergiesViewModel allergiesViewModel;

    //public AllergiesDao allergiesDao;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.insert(new Patient("64 gamet el dowal", "A+", "complicated", 0));
        //Patient temp = new Patient("63  gamet el dowal", "A+", "single", 0);
        //temp.setId(1);
        //patientViewModel.update(temp);
        Allergies allergies = new Allergies(1, "Lactose");
        //allergiesDao.insert(allergies);
        allergiesViewModel = ViewModelProviders.of(this).get(AllergiesViewModel.class);
        allergiesViewModel.insert(allergies);

    }
}
