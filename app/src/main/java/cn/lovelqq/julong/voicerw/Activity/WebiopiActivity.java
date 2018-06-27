package cn.lovelqq.julong.voicerw.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cn.lovelqq.julong.voicerw.R;
import cn.lovelqq.julong.voicerw.RaspberryValus.GetButtonId;
import cn.lovelqq.julong.voicerw.RaspberryValus.GetPinValus;
import netWork.okhttp.Okhttp;


public class WebiopiActivity extends Activity implements View.OnClickListener {
    private Button GPIOFunctionBut;
    private Button GPIOPinVlueBut;
    private GetPinValus getPinValus;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uiview);
        getPinValus= new GetPinValus();
        handler = new Handler();
        handler.post(new TimerWhiel());
    }
    private void upDateAndUI() throws Exception{
        Map<String,String[]> pinValusMap = new HashMap<String, String[]>();
        pinValusMap = getPinValus.getjsonPin();   //刷新GPIO引脚数据
        for (String key : pinValusMap.keySet()){  //每个GPIO引脚状态
            String [] GPIOS = pinValusMap.get(key);

            int  GPIOindex = Integer.valueOf(key);     //获取引脚的下标
            String GPIOfunction = GPIOS[0];            //gpio的状态
            int GPIOVlue =  Integer.valueOf(GPIOS[1]); //GPIO的值

            setGPIO(GPIOindex,GPIOfunction,GPIOVlue);
//            Log.i("GPIO","引脚下标:"+GPIOindex+", 状态"+ GPIOfunction+", 值"+GPIOVlue);
        }
    }

    /**
     * 传入GPIO的按钮ID
     * 根据当前状态判断设置输入还是输出,和引脚的高低选择相对应的背景
     * @param GPIOid
     */
    private void setGPIO(int GPIOid,String function,int vlue){
        int GPIOFunctionId =GetButtonId.getGPIOFunctionId(GPIOid); // 获取状态对应引脚的ID
        int GPIOVlusID = GetButtonId.getGPIOVluesId(GPIOid);       //获取GPIO值对应的ID

        GPIOFunctionBut = findViewById(GPIOFunctionId);
        GPIOPinVlueBut = findViewById(GPIOVlusID);
        //把状态在按钮上显示
        if ("IN".equals(function)){
            GPIOFunctionBut.setText("IN");
        }if ("OUT".equals(function)){
            GPIOFunctionBut.setText("OUT");
        }
        //如果引脚值为0，则是黑背景；如果是1则是黄背景
        if (1==vlue){
            GPIOPinVlueBut.setBackground(getResources().getDrawable(R.drawable.button_edge_yellow));
        }
        if (0==vlue){
            GPIOPinVlueBut.setBackground(getResources().getDrawable(R.drawable.button_edge_black));
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.GPIOInOrOut2:
                Toast.makeText(this,"GPIO2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.GPIOInOrOut3:
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
    private class TimerWhiel implements Runnable{
        @Override
        public void run() {
            try {
                upDateAndUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(this,100);//每个进行一次刷新
        }
    }
}
