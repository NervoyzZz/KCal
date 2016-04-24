package nepryahin_daniil.kcal;

import android.app.Activity;
import android.os.Bundle;

/**
     Непряхин Д.
     Активность в которой говорится о приложении и разработчике
 */
public class AboutActivity extends Activity{
    /*
        Просто создаем активнось, на ней ничего не происходит
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
