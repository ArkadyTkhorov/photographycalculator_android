package info.androidhive.materialtabs.fragments;


/**
 * Created by Greg.
 */

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import info.androidhive.materialtabs.R;


public class RegularCalculator extends Fragment {

    EditText editText;

    Button button0, button1, button2, button3,
            button4, button5, button6, button7, button8, button9, buttonPlus, buttonMinus, buttonMultiply,
            buttonDivide, buttonEqual, buttonPoint, buttonOct, buttonReset, buttonHex, buttonCubed,
            button_sin, button_cos, button_tan, button_squared_2, button_root, button_del, button_dec,
            button_bin, button_pi, buttonExit;

    String sum = "", one, oneAgain = "", two, twoAgain = "", three, threeAgain = "", four, fourAgain = "", five, fiveAgain = "",
            six, sixAgain, seven, sevenAgain = "", eight, eightAgain = "", nine, nineAgain = "",
            zero, plus, minus, multiply, divide, equal, point, del, reset,
            dec_string = "", hex_string = "", oct_string = "", pi = "3.1416";

    Integer countOne = 0, dec_num, unicode_value;

    Float result = 0f, result_mul = 1f, result_div = 1f;

    int pressCount = 1, sumZero, dec_flag = 0, c, i;

    char press;

    String EditTextMsg, bin_num, hex_num, oct_num;

    Float floatEditTextMsg;

    Double doubleEditTextMsg, afterSin, after_cos, after_tan, toRadian_doubleEditTextMsg, after_squared_2, after_root, after_qube;

    Vibrator vibrator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.calculators_regularcalculator, container, false);
        editText = (EditText) android.findViewById(R.id.editText1);

        button0 = (Button) android.findViewById(R.id.button0);
        button1 = (Button) android.findViewById(R.id.button1);
        button2 = (Button) android.findViewById(R.id.button2);
        button3 = (Button) android.findViewById(R.id.button3);
        button4 = (Button) android.findViewById(R.id.button4);

        button5 = (Button) android.findViewById(R.id.button5);
        button6 = (Button) android.findViewById(R.id.button6);
        button7 = (Button) android.findViewById(R.id.button7);
        button8 = (Button) android.findViewById(R.id.button8);
        button9 = (Button) android.findViewById(R.id.button9);
        setupAllNumberButtons();


        buttonPlus = (Button) android.findViewById(R.id.buttonPlus);
        buttonMinus = (Button) android.findViewById(R.id.buttonMinus);
        buttonMultiply = (Button) android.findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) android.findViewById(R.id.buttonDivide);
        buttonPoint = (Button) android.findViewById(R.id.buttonPoint);

        buttonEqual = (Button) android.findViewById(R.id.buttonEqual);
        buttonReset = (Button) android.findViewById(R.id.buttonReset);
        buttonExit = (Button) android.findViewById(R.id.buttonExit);
        button_pi = (Button) android.findViewById(R.id.button_pi);
        buttonOct = (Button) android.findViewById(R.id.button_oct);
        buttonHex = (Button) android.findViewById(R.id.button_hex);
        buttonCubed = (Button) android.findViewById(R.id.button_qube);
        setupAllBasicFunctionButtons();

        button_sin = (Button) android.findViewById(R.id.button_sin);
        button_cos = (Button) android.findViewById(R.id.button_cos);
        button_tan = (Button) android.findViewById(R.id.button_tan);
        button_root = (Button) android.findViewById(R.id.button_root);
        button_squared_2 = (Button) android.findViewById(R.id.button_squared_2);

        button_del = (Button) android.findViewById(R.id.button_del);
        button_dec = (Button) android.findViewById(R.id.button_dec);
        button_bin = (Button) android.findViewById(R.id.button_bin);
        setUpAllAdvancedFunctionButtons();
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        //button_del=(Button)findViewById(R.id.button_del);

        editText.setText(result.toString());
        return android;
    }

    private void setUpAllAdvancedFunctionButtons() {
        button_sin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_sin();
            }
        });
        button_cos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_cos();
            }
        });
        button_tan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_tan();
            }
        });
        button_root.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_root();
            }
        });
        button_squared_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_squared_2();
            }
        });
        button_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_del();
            }
        });
        button_dec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_dec();
            }
        });
        button_bin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_bin();
            }
        });
    }

    private void setupAllBasicFunctionButtons() {
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerPlus();
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerMinus();
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerDivide();
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerMultiply();
            }
        });
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerPoint();
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerEqual();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerReset();
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListenerExit();
            }
        });
        buttonCubed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_qube();
            }
        });
        buttonHex.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_hex();
            }
        });
        buttonOct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_oct();
            }
        });
        button_pi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener_pi();
            }
        });
    }

    private void setupAllNumberButtons() {
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener0();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener2();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener3();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener4();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener5();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener6();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener7();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener8();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickListener9();
            }
        });
    }

    public void onClickListener0() {
        vibrator.vibrate(30);
        if (press == '=') {
            onClickListenerReset();
        }

        if (sum != "") {
            zero = (String) button0.getText();

            sum = sum + zero;
            editText.setText(sum);
        } else {
            sum = "0";
            editText.setText("0");
        }
    }

    public void onClickListener1() {
        vibrator.vibrate(30);

        if (press == '=') {
            onClickListenerReset();
        }

        one = (String) button1.getText();
        sum = sum + one;

        editText.setText(sum);
    }

    public void onClickListener2() {
        vibrator.vibrate(30);

        if (press == '=') {
            onClickListenerReset();
        }

        if (press == '=') {
            onClickListenerEqual();
        }

        two = (String) button2.getText();
        sum = sum + two;

        editText.setText(sum);
    }

    public void onClickListener3() {
        vibrator.vibrate(30);
        if (press == '=') {
            onClickListenerReset();
        }
        three = (String) button3.getText();
        sum = sum + three;

        editText.setText(sum);
    }

    public void onClickListener4() {
        vibrator.vibrate(30);

        if (press == '=') {
            onClickListenerReset();
        }

        four = (String) button4.getText();
        sum = sum + four;

        editText.setText(sum);
    }

    public void onClickListener5() {
        vibrator.vibrate(30);
        if (press == '=') {
            onClickListenerReset();
        }

        five = (String) button5.getText();
        sum = sum + five;

        editText.setText(sum);
    }

    public void onClickListener6() {
        vibrator.vibrate(30);
        if (press == '=') {
            onClickListenerReset();
        }

        six = (String) button6.getText();
        sum = sum + six;

        editText.setText(sum);
    }

    public void onClickListener7() {
        vibrator.vibrate(30);
        if (press == '=') {
            onClickListenerReset();
        }

        seven = (String) button7.getText();
        sum = sum + seven;

        editText.setText(sum);
    }

    public void onClickListener8() {
        vibrator.vibrate(30);
        if (press == '=') {
            onClickListenerReset();
        }

        eight = (String) button8.getText();
        sum = sum + eight;

        editText.setText(sum);
    }

    public void onClickListener9() {
        vibrator.vibrate(30);
        if (press == '=') {
            onClickListenerReset();
        }

        nine = (String) button9.getText();
        sum = sum + nine;

        editText.setText(sum);
    }

    public void onClickListenerPlus() {
        vibrator.vibrate(30);

        if (press == '-') {
            onClickListenerEqual();
        } else if (press == '*') {
            onClickListenerEqual();
        } else if (press == '/') {
            onClickListenerEqual();
        }


        press = '+';

        if (sum != "") {
            result = result + Float.parseFloat(editText.getText().toString());

            editText.setText(result.toString());

            result_mul = result;

            result_div = result;

            sum = "";
        } else {
            editText.setText(result.toString());

            result_mul = result;

            result_div = result;

            sum = "";
        }

    }

    public void onClickListenerMinus() {
        vibrator.vibrate(30);
        if (press == '+') {
            onClickListenerEqual();
        } else if (press == '*') {
            onClickListenerEqual();
        } else if (press == '/') {
            onClickListenerEqual();
        }


        press = '-';

        EditTextMsg = editText.getText().toString();
        floatEditTextMsg = Float.parseFloat(EditTextMsg);

        if (sum == "" && result == 0) {
            sum = sum + '-';
            //Log.d("sum=","minus press");
        } else if (sum != "") {
            if (result == 0) {
                result = Float.parseFloat(sum) - result;

                editText.setText(result.toString());

                result_mul = result;

                result_div = result;

                sum = "";
            } else {
                result = result - Float.parseFloat(sum);

                editText.setText(result.toString());

                result_mul = result;

                result_div = result;

                sum = "";
            }
        }

    }

    public void onClickListenerMultiply() {
        vibrator.vibrate(30);
        if (press == '/') {
            onClickListenerEqual();
        } else if (press == '+') {
            onClickListenerEqual();
        } else if (press == '-') {
            onClickListenerEqual();
        }


        press = '*';

        EditTextMsg = editText.getText().toString();
        floatEditTextMsg = Float.parseFloat(EditTextMsg);

        if (sum != "") {
            result_mul = result_mul * floatEditTextMsg;

            result = result_mul;

            result_div = result_mul;

            editText.setText(result_mul.toString());

            sum = "";
        } else {
            editText.setText(EditTextMsg);

            //result_mul=result_mul * Float.parseFloat(sum);

            //result=result_mul;

            sum = "";
        }

    }


    public void onClickListenerDivide() {
        vibrator.vibrate(30);
        if (press == '+') {
            onClickListenerEqual();
        } else if (press == '-') {
            onClickListenerEqual();
        } else if (press == '*') {
            onClickListenerEqual();
        }

        press = '/';

        EditTextMsg = editText.getText().toString();
        floatEditTextMsg = Float.parseFloat(EditTextMsg);

        if (sum != "" && result_div == 1) {
            //int c=0;

            if (c == 0) {
                result_div = floatEditTextMsg / result_div;
                Log.d("if if result_div=", result_div.toString());
                c++;
            } else {
                result_div = result_div / floatEditTextMsg;
                Log.d("if else result_div=", result_div.toString());
            }


            result = result_div;
            result_mul = result_div;

            editText.setText(result_div.toString());

            sum = "";
        } else if (sum != "" && result_div != 1) {
            result_div = result_div / floatEditTextMsg;

            Log.d("else if result_div=", result_div.toString());

            result = result_div;

            result_mul = result_div;

            editText.setText(result_div.toString());

            sum = "";
        } else {
            editText.setText(EditTextMsg);

            sum = "";
        }
    }


    public void onClickListenerPoint() {
        vibrator.vibrate(30);

        int error = 0;

        if (sum != null) {
            for (int i = 0; i < sum.length(); i++) {
                if (sum.charAt(i) == '.') {
                    error = 1;
                    break;
                }
            }

        }

        if (error == 0) {
            if (sum == null) {
                sum = sum + "0.";
            } else {
                sum = sum + ".";
            }
        }

        editText.setText(sum);
    }

    public void onClickListenerEqual() {
        vibrator.vibrate(30);

        if (press == '+') {
            onClickListenerPlus();
            //msg1= editText.getText().toString();
            //floatMsg=Float.parseFloat(msg1);
        } else if (press == '-') {
            onClickListenerMinus();
        } else if (press == '*') {
            onClickListenerMultiply();
        } else if (press == '/') {
            onClickListenerDivide();
        }

        press = '=';


    }

    public void onClickListenerExit() {
        vibrator.vibrate(30);
        getActivity().finish();
    }

    public void onClickListenerReset() {
        vibrator.vibrate(30);

        sum = "";
        countOne = 0;//result=0;

        result = 0f;
        result_mul = 1f;
        result_div = 1f;
        press = ' ';
        c = 0;

        editText.setText(result.toString());
    }


    public void onClickListener_sin() {
        vibrator.vibrate(30);

        EditTextMsg = editText.getText().toString();
        doubleEditTextMsg = Double.parseDouble(EditTextMsg);//degree

        toRadian_doubleEditTextMsg = Math.toRadians(doubleEditTextMsg);

        afterSin = Math.sin(toRadian_doubleEditTextMsg);

        editText.setText(afterSin.toString());

        EditTextMsg = editText.getText().toString();
        result = Float.parseFloat(EditTextMsg);

        result_mul = Float.parseFloat(EditTextMsg);

        result_div = Float.parseFloat(EditTextMsg);


        sum = "";


    }

    public void onClickListener_cos() {
        vibrator.vibrate(30);
        EditTextMsg = editText.getText().toString();
        doubleEditTextMsg = Double.parseDouble(EditTextMsg);//degree

        toRadian_doubleEditTextMsg = Math.toRadians(doubleEditTextMsg);

        after_cos = Math.cos(toRadian_doubleEditTextMsg);

        editText.setText(after_cos.toString());

        EditTextMsg = editText.getText().toString();
        result = Float.parseFloat(EditTextMsg);

        result_mul = Float.parseFloat(EditTextMsg);

        result_div = Float.parseFloat(EditTextMsg);


        sum = "";


    }

    public void onClickListener_tan() {
        vibrator.vibrate(30);

        EditTextMsg = editText.getText().toString();
        doubleEditTextMsg = Double.parseDouble(EditTextMsg);//degree

        toRadian_doubleEditTextMsg = Math.toRadians(doubleEditTextMsg);

        after_tan = Math.tan(toRadian_doubleEditTextMsg);

        editText.setText(after_tan.toString());

        EditTextMsg = editText.getText().toString();
        result = Float.parseFloat(EditTextMsg);

        result_mul = Float.parseFloat(EditTextMsg);

        result_div = Float.parseFloat(EditTextMsg);


        sum = "";
    }


    public void onClickListener_squared_2() {
        vibrator.vibrate(30);

        EditTextMsg = editText.getText().toString();
        doubleEditTextMsg = Double.parseDouble(EditTextMsg);//degree


        after_squared_2 = Math.pow(doubleEditTextMsg, 2);

        editText.setText(after_squared_2.toString());

        EditTextMsg = editText.getText().toString();
        result = Float.parseFloat(EditTextMsg);

        result_mul = Float.parseFloat(EditTextMsg);

        result_div = Float.parseFloat(EditTextMsg);


        sum = "";


    }

    public void onClickListener_qube() {
        vibrator.vibrate(30);

        EditTextMsg = editText.getText().toString();
        doubleEditTextMsg = Double.parseDouble(EditTextMsg);//degree


        after_qube = Math.pow(doubleEditTextMsg, 3);

        editText.setText(after_qube.toString());

        EditTextMsg = editText.getText().toString();
        result = Float.parseFloat(EditTextMsg);

        result_mul = Float.parseFloat(EditTextMsg);

        result_div = Float.parseFloat(EditTextMsg);


        sum = "";


    }

    public void onClickListener_root() {
        vibrator.vibrate(30);
        EditTextMsg = editText.getText().toString();
        doubleEditTextMsg = Double.parseDouble(EditTextMsg);//degree

        after_root = Math.sqrt(doubleEditTextMsg);

        editText.setText(after_root.toString());

        EditTextMsg = editText.getText().toString();
        result = Float.parseFloat(EditTextMsg);

        result_mul = Float.parseFloat(EditTextMsg);

        result_div = Float.parseFloat(EditTextMsg);


        sum = "";

    }

    public void onClickListener_pi() {
        vibrator.vibrate(30);

        if (press == '=') {
            onClickListenerReset();
        }

        sum = pi;

        editText.setText(pi);

    }

    public void onClickListener_del() {
        vibrator.vibrate(30);
        if (sum != "") {
            StringBuilder stringBuilder = new StringBuilder(80);

            stringBuilder.append(sum);

            sum = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();

            editText.setText(sum);
        }

    }

    public void onClickListener_dec() {
        vibrator.vibrate(30);

        EditTextMsg = editText.getText().toString();

        for (int i = 0; i <= EditTextMsg.length() - 1; i++) {
            unicode_value = EditTextMsg.codePointAt(i);
            if (unicode_value > 49 || unicode_value < 48) {
                dec_flag = 1;
                Log.d("uni", unicode_value.toString());
                break;
            }


        }


        if (dec_flag == 0) {
            dec_num = Integer.parseInt(EditTextMsg, 2);


            editText.setText(dec_num.toString());

            EditTextMsg = editText.getText().toString();

            sum = "";
        } else {

            editText.setText("input error");

            sum = "";
        }

    }

    public void onClickListener_bin() {
        vibrator.vibrate(30);
        //button_bin.setBackgroundColor(Color.BLUE);
        EditTextMsg = editText.getText().toString();

        for (i = 0; i < EditTextMsg.length(); i++) {
            if (EditTextMsg.charAt(i) == '.') {
                break;
            } else {
                dec_string = dec_string + EditTextMsg.charAt(i);
            }
        }
        dec_num = Integer.parseInt(dec_string);

        Log.d("dec_num=", dec_num.toString());

        bin_num = Integer.toBinaryString(dec_num);

        editText.setText(bin_num);

        dec_string = "";
        EditTextMsg = "";
        bin_num = "";
        sum = "";

    }

    public void onClickListener_hex() {
        vibrator.vibrate(30);

        EditTextMsg = editText.getText().toString();

        for (i = 0; i < EditTextMsg.length(); i++) {
            if (EditTextMsg.charAt(i) == '.') {
                break;
            } else {
                hex_string = hex_string + EditTextMsg.charAt(i);
            }
        }
        dec_num = Integer.parseInt(hex_string);

        Log.d("dec_num=", dec_num.toString());

        hex_num = Integer.toHexString(dec_num);

        editText.setText(hex_num);

        dec_string = "";
        hex_string = "";

        EditTextMsg = "";

        bin_num = "";
        hex_num = "";

        sum = "";

    }

    public void onClickListener_oct() {
        vibrator.vibrate(30);

        EditTextMsg = editText.getText().toString();

        for (i = 0; i < EditTextMsg.length(); i++) {
            if (EditTextMsg.charAt(i) == '.') {
                break;
            } else {
                oct_string = oct_string + EditTextMsg.charAt(i);
            }
        }
        dec_num = Integer.parseInt(oct_string);

        Log.d("dec_num=", dec_num.toString());

        oct_num = Integer.toOctalString(dec_num);

        editText.setText(oct_num);

        dec_string = "";
        hex_string = "";
        oct_string = "";

        EditTextMsg = "";

        bin_num = "";
        hex_num = "";
        oct_num = "";

        sum = "";

    }


}
