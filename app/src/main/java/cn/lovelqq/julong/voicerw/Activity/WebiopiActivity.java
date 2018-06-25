package cn.lovelqq.julong.voicerw.Activity;

import android.app.Activity;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import cn.lovelqq.julong.voicerw.R;
import netWork.okhttp.Okhttp;


public class WebiopiActivity extends Activity implements View.OnClickListener {
    private Button GPIO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uiview);
    }

    /**
     * 传入GPIO的按钮ID
     * 根据当前状态判断设置输入还是输出
     * @param GPIOid
     */
    private void setINOrOut(int GPIOid){
        GPIO=findViewById(GPIOid);
        String GPIOstate = GPIO.getText().toString();//获取GPIO状态是IN还是OUT
        if ("IN".equals(GPIOstate)){
        GPIO.setText("OUT");
        }else {
            GPIO.setText("IN");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.GPIOInOrOut2:
                setINOrOut(R.id.GPIOInOrOut2);
                Toast.makeText(this,"GPIO2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut3:
                Okhttp.okHttpGet();
                Toast.makeText(this,"GPIO3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut4:
                Toast.makeText(this,"GPIO4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut17:
                Toast.makeText(this,"GPIO17",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut27:
                Toast.makeText(this,"GPIO27",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut22:
                Toast.makeText(this,"GPIO22",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut10:
                Toast.makeText(this,"GPIO10",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut9:
                Toast.makeText(this,"GPIO9",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut11:
                Toast.makeText(this,"GPIO11",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut5:
                Toast.makeText(this,"GPIO5",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut6:
                Toast.makeText(this,"GPIO6",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut13:
                Toast.makeText(this,"GPIO13",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut19:
                Toast.makeText(this,"GPIO19",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut26:
                Toast.makeText(this,"GPIO26",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut18:
                Toast.makeText(this,"GPIO18",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut23:
                Toast.makeText(this,"GPIO23",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut24:
                Toast.makeText(this,"GPIO24",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut25:
                Toast.makeText(this,"GPIO25",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut8:
                Toast.makeText(this,"GPIO8",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut7:
                Toast.makeText(this,"GPIO7",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut12:
                Toast.makeText(this,"GPIO12",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut16:
                Toast.makeText(this,"GPIO16",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut20:
                Toast.makeText(this,"GPIO20",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut21:
                Toast.makeText(this,"GPIO21",Toast.LENGTH_SHORT).show();
                break;

                /******************下面为引脚的点击事件*************************/
            case R.id.butPin1:
                Toast.makeText(this,"PIN1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin2:
                Toast.makeText(this,"PIN2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin3:
                Toast.makeText(this,"PIN3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin4:
                Toast.makeText(this,"PIN4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin5:
                Toast.makeText(this,"PIN5",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin6:
                Toast.makeText(this,"PIN6",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin7:
                Toast.makeText(this,"PIN7",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin8:
                Toast.makeText(this,"PIN8",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin9:
                Toast.makeText(this,"PIN9",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin10:
                Toast.makeText(this,"PIN10",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin11:
                Toast.makeText(this,"PIN11",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin12:
                Toast.makeText(this,"PIN12",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin13:
                Toast.makeText(this,"PIN13",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin14:
                Toast.makeText(this,"PIN14",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin15:
                Toast.makeText(this,"PIN15",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin16:
                Toast.makeText(this,"PIN16",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin17:
                Toast.makeText(this,"PIN17",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin18:
                Toast.makeText(this,"PIN18",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin19:
                Toast.makeText(this,"PIN19",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin20:
                Toast.makeText(this,"PIN20",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin21:
                Toast.makeText(this,"PIN21",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin22:
                Toast.makeText(this,"PIN22",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin23:
                Toast.makeText(this,"PIN23",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin24:
                Toast.makeText(this,"PIN24",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin25:
                Toast.makeText(this,"PIN25",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin26:
                Toast.makeText(this,"PIN26",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin27:
                Toast.makeText(this,"PIN27",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin28:
                Toast.makeText(this,"PIN28",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin29:
                Toast.makeText(this,"PIN29",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin30:
                Toast.makeText(this,"PIN30",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin31:
                Toast.makeText(this,"PIN31",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin32:
                Toast.makeText(this,"PIN32",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin33:
                Toast.makeText(this,"PIN33",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin34:
                Toast.makeText(this,"PIN34",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin35:
                Toast.makeText(this,"PIN35",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin36:
                Toast.makeText(this,"PIN36",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin37:
                Toast.makeText(this,"PIN37",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin38:
                Toast.makeText(this,"PIN38",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin39:
                Toast.makeText(this,"PIN39",Toast.LENGTH_SHORT).show();
                break;
            case R.id.butPin40:
                Toast.makeText(this,"PIN40",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
