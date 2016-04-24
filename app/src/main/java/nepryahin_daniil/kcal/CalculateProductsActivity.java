package nepryahin_daniil.kcal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


/**
    Непряхин Д.
    На этой Activity представлен список продуктов и поля, куда пользователь будет вводить
    количество съеденного. Сдесь же будет просчитываться количество полученных калорий.
 */

public class CalculateProductsActivity extends AppCompatActivity{

    /*
    создаем массив, в котором будут лежать калории продуктов
    по порядку в котором они перечислены в layout'е
    */
    // Но вначале нам нужна константа, отвечающая за размер массива, она нам пригодится позже
    final int array_Products_length = 11;
    // главное не забывать, что нумерация массива с 0
    // калорийность приведена на 100 г.
    final int [] array_Product_Calory = {
            //Картофель
            83,
            //Огурец
            15,
            //Рыба
            95,
            //Хлеб
            214,
            //Греча
            326,
            //Апельсин
            38,
            //Яблоко
            46,
            //Шоколад
            547,
            //Кефир
            // но если стоит чекбокс с 1%, то у кефира калорийность 39
            // Получается нам повезло и не при подсчете будет достаточно вычесть 20
            59,
            //Свинина
            355,
            //Курица
            180 };


    /*
        Так же нам нужен массив EditText'ов, чтобы мы могли считывать от туда информацию.
        Инициальзация в методе onCreate
        Пытался инициализировать - приложение вылетало с ошибкой.
     */
    EditText [] editxt_array_Product_Quantity;

    // чекбокс для кифирчика
    CheckBox chkbx_Kefir;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_products);

        /*
            Массив EditText'ов в которые пользователь вводит значения
            Выглядит ужасно.
            Но зато... Работает!
            А серьезно, еще не придумал, как сделать это более эффективно
         */
        editxt_array_Product_Quantity = new EditText[array_Products_length];
        editxt_array_Product_Quantity[0] = (EditText)findViewById(R.id.editxtPotato);
        editxt_array_Product_Quantity[1] = (EditText)findViewById(R.id.editxtCucumber);
        editxt_array_Product_Quantity[2] = (EditText)findViewById(R.id.editxtFish);
        editxt_array_Product_Quantity[3] = (EditText)findViewById(R.id.editxtBread);
        editxt_array_Product_Quantity[4] = (EditText)findViewById(R.id.editxtBuckwheat);
        editxt_array_Product_Quantity[5] = (EditText)findViewById(R.id.editxtOrange);
        editxt_array_Product_Quantity[6] = (EditText)findViewById(R.id.editxtApple);
        editxt_array_Product_Quantity[7] = (EditText)findViewById(R.id.editxtChocolate);
        editxt_array_Product_Quantity[8] = (EditText)findViewById(R.id.editxtKefir);
        editxt_array_Product_Quantity[9] = (EditText)findViewById(R.id.editxtPork);
        editxt_array_Product_Quantity[10] = (EditText)findViewById(R.id.editxtKokoko);

        /*
        Еще создадим переменную для чекбокса рядом с кефиром, чтобы его отслеживать
        */
        chkbx_Kefir = (CheckBox)findViewById(R.id.chlbxKefir);
    }




    /*
    Метод для сбрасывания значений, введенных в текстовые поля
     */
    public void onClick_Nulledtxt(View view) {
        int i;
        for (i = 0; i <= array_Products_length - 1; i = i + 1 ) {
            // не вижу смысла загонять пустую строку в файл strings.xml
            editxt_array_Product_Quantity[i].setText("");
        }
        // еще я считаю нужным снять галочку с чекбокса
        chkbx_Kefir.setChecked(false);
    }

    /*
    Данный метод будет считать калории, полагаясь на значения,
    введенные пользователем, и перекидывать на диалоговое активити,
    где будет показан результат и комментарий.
    Алгоритм: в цикле проходим массив EditText'ов и, если строка
    не пустая, то вычисляем количество калорий, опираясь на массив со значениями
    на 100 г. Так же еще надо учесть нажатый чекбокс для кефира.
     */
    public void onClick_Calc_Result(View view) {
        //счетчик цикла
        int i;
        //результат вычислений
        float Result = 0;
        //строка количества введенного
        String strCurrentQuantity;
        //текущая калорийность (с переменной удобнее обрабатывать
        //особый случай с кефиром
        int CurrentCal;
        for (i = 0; i <= array_Products_length - 1; i = i + 1) {
            //выносим значение из Вью в переменную для удобства
            strCurrentQuantity = editxt_array_Product_Quantity[i].getText().toString();
            //если пользователь что-то ввел
            if (!(strCurrentQuantity.equals(""))){
                // так мы присваиваем числовое значение из строки
                Float CurrentQuantity = Float.valueOf(strCurrentQuantity);
                CurrentCal = array_Product_Calory[i];
                // проверка на кефир
                if (i == 8) {
                    // смотрим чекбокс
                    if (chkbx_Kefir.isChecked()) {
                        //т. к. 1% кефир на 20 калорий меньше
                        CurrentCal = CurrentCal - 20;
                    }
                }
                //считаем
                Result = Result + CurrentCal * CurrentQuantity / 100;
            }
        }
        //осталось перейти на другое активити и передать наш результат
        // но вначале проверка на нулевой результат
        if (Result == 0) {
            // Выведем сообщение об этом
            Toast toast = Toast.makeText(getApplicationContext(),getText(R.string.strToast_Nothing), Toast.LENGTH_LONG);
            toast.show();
        } else {
            final Intent toShowResultActivity = new Intent(CalculateProductsActivity.this, ShowResultActivity.class);
            //запихиваем в intent значение переменной Result с меткой Result
            //в другой активности по этой метке мы сможем взять значение переменной
            // тут "Result" пользователю не показывается, и является по сути своей - переменной
            // так что заносить его в strings.xml не нужно
            // (это я на всякий случай, чтобы при беглом просмотре кода не захотелось придраться)
            toShowResultActivity.putExtra("Result", Result);
            startActivity(toShowResultActivity);
        }
    }
}
