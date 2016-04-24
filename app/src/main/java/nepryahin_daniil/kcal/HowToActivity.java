package nepryahin_daniil.kcal;

import android.app.Activity;
import android.os.Bundle;

/**
    Непряхин Д.
    Активность, в которой говориться, как пользоваться приложением
 */
public class HowToActivity extends Activity {
    /*
    Просто создаем активнось, на ней ничего не происходит
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
    }
}
