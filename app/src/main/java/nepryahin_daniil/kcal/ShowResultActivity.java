package nepryahin_daniil.kcal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 Непряхин Д.

 Показываем пользователю посчитанный результат и даем небольшой комментарий
 */

public class ShowResultActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        // определяем текстовое поля, для доступа к нему
        final TextView txtv_ShowResult = (TextView)findViewById(R.id.txtvShowResult);
        // получаем переданное значение
        Float Result = getIntent().getFloatExtra("Result", 0);
        // переменная, где будет хранится строка для отображения
        String strResult;

        // Создаем строку с комментарием
        strResult = getString(R.string.strResult_begin, Result);
        // Все хорошо
        if (Result <= 400){
            strResult = strResult + getString(R.string.strResultGood);
        } else {
            // не очень хорошо
            if (Result <=800){
                strResult = strResult + getString(R.string.strResultBad);
            } else {
                // все плохо
                if (Result <= 2000) {
                    strResult = strResult + getString(R.string.strResultVerybad);
                } else {
                    // все ужасно
                    strResult = strResult + getString(R.string.strResultFatMan);
                }
            }
        }
        strResult = strResult + getString(R.string.strResult_end);
        // присваиваем строку нашей ТекстВьюшке
        txtv_ShowResult.setText(strResult);
    }
}
