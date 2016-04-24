package nepryahin_daniil.kcal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
    Непряхин Д.
    Стартовая активность
    Тут представлены обработчики нажатия кнопок, переход
    на новую активность и отправка письма.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
        Метод отправки отзыва на почту разработчика по нажатию на кнопку
     */
    public void onClick_SendMessage(View view) {

        final Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(getString(R.string.intent_SendMail)));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.intent_SubjectMail));
        /*
            Чтобы email прикреплялся, строку нужно передавать как массив
         */
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString((R.string.intent_ToMail))});
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }


    }

    /*
        Переход на Activity, где будет рассказано, как пользоваться приложением
     */
    public void onClick_HowTo(View view) {
        final Intent toHowToActivity = new Intent(MainActivity.this, HowToActivity.class);
        startActivity(toHowToActivity);
    }

    /*
        Переход на Activity, где будет рассказано о приложении
     */
    public void onClick_About(View view) {
        final Intent toAboutActivity = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(toAboutActivity);
    }

    /*
        Переход на Activity, где будет представлен список продуктов и поля для
        ввода пользователем количества съеденного
     */
    public void onClick_CalculateKcal(View view) {
        final Intent toCalculateProductsActivity = new Intent(MainActivity.this, CalculateProductsActivity.class);
        startActivity(toCalculateProductsActivity);
    }
}
