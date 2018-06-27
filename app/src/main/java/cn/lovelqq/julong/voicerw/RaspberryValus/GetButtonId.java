package cn.lovelqq.julong.voicerw.RaspberryValus;

import android.widget.Button;

import cn.lovelqq.julong.voicerw.R;

public class GetButtonId {

    public static int getGPIOFunctionId(int id){
        int[] GPIObut= new int[28];
        GPIObut[2] = R.id.GPIOInOrOut2;
        GPIObut[3] = R.id.GPIOInOrOut3;
        GPIObut[4] = R.id.GPIOInOrOut4;
        GPIObut[5] = R.id.GPIOInOrOut5;
        GPIObut[6] = R.id.GPIOInOrOut6;
        GPIObut[7] = R.id.GPIOInOrOut7;
        GPIObut[8] = R.id.GPIOInOrOut8;
        GPIObut[9] = R.id.GPIOInOrOut9;
        GPIObut[10] = R.id.GPIOInOrOut10;
        GPIObut[11] = R.id.GPIOInOrOut11;
        GPIObut[12] = R.id.GPIOInOrOut12;
        GPIObut[13] = R.id.GPIOInOrOut13;
        GPIObut[16] = R.id.GPIOInOrOut16;
        GPIObut[17] = R.id.GPIOInOrOut17;
        GPIObut[18] = R.id.GPIOInOrOut18;
        GPIObut[19] = R.id.GPIOInOrOut19;
        GPIObut[20] = R.id.GPIOInOrOut20;
        GPIObut[21] = R.id.GPIOInOrOut21;
        GPIObut[22] = R.id.GPIOInOrOut22;
        GPIObut[23] = R.id.GPIOInOrOut23;
        GPIObut[24] = R.id.GPIOInOrOut24;
        GPIObut[25] = R.id.GPIOInOrOut25;
        GPIObut[26] = R.id.GPIOInOrOut26;
        GPIObut[27] = R.id.GPIOInOrOut27;
        return GPIObut[id];
    }
    public static int getGPIOVluesId(int id){
        int[] GPIObut= new int[28];
        GPIObut[2] = R.id.butPin3;
        GPIObut[3] = R.id.butPin5;
        GPIObut[4] = R.id.butPin7;
        GPIObut[5] = R.id.butPin29;
        GPIObut[6] = R.id.butPin31;
        GPIObut[7] = R.id.butPin26;
        GPIObut[8] = R.id.butPin24;
        GPIObut[9] = R.id.butPin21;
        GPIObut[10] = R.id.butPin19;
        GPIObut[11] = R.id.butPin23;
        GPIObut[12] = R.id.butPin32;
        GPIObut[13] = R.id.butPin33;
        GPIObut[16] = R.id.butPin36;
        GPIObut[17] = R.id.butPin11;
        GPIObut[18] = R.id.butPin12;
        GPIObut[19] = R.id.butPin35;
        GPIObut[20] = R.id.butPin38;
        GPIObut[21] = R.id.butPin40;
        GPIObut[22] = R.id.butPin15;
        GPIObut[23] = R.id.butPin16;
        GPIObut[24] = R.id.butPin18;
        GPIObut[25] = R.id.butPin22;
        GPIObut[26] = R.id.butPin37;
        GPIObut[27] = R.id.butPin13;
        return GPIObut[id];
    }
}
