package mx.com.intercam.databaseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import mx.com.intercam.databaseexample.dao.DocumentDao;
import mx.com.intercam.databaseexample.dao.UserDao;
import mx.com.intercam.databaseexample.database.AppDatabase;
import mx.com.intercam.databaseexample.entity.DocumentEntity;
import mx.com.intercam.databaseexample.entity.UserEntity;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.sharedInstance();

        UserEntity user = new UserEntity("Juan", "Santos");

        //Se obtiene la "implementación" del UserDao
        UserDao userDao = db.getUserDao();
        DocumentDao documentDao = db.getDocumentDao();

        userDao.insert(user);

        List<UserEntity> consulted = userDao.getAll();

        for (UserEntity u : consulted) {

            Log.d(TAG, u.toString());

            //Para cada usuario generamos 3 documentos
            for (int i = 0; i < 3; i++) {

                DocumentEntity document =
                        new DocumentEntity(
                                "Path " + i,
                                "Archivo " + i,
                                u.getId());

                documentDao.insert(document);

            }

        }


    }
}
