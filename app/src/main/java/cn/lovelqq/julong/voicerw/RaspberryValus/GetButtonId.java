package cn.lovelqq.julong.voicerw.RaspberryValus;

import cn.lovelqq.julong.voicerw.R;

public class GetButtonId {

    private static int[] GPIOFunctionID = new int[28];
    private static int[] GPIOVluesID =  new int[28];

    /**
     * 初始化功能引脚
     */
    private static void InitGPIOFunctionID(){
        GPIOFunctionID[2] = R.id.GPIOInOrOut2;
        GPIOFunctionID[3] = R.id.GPIOInOrOut3;
        GPIOFunctionID[4] = R.id.GPIOInOrOut4;
        GPIOFunctionID[5] = R.id.GPIOInOrOut5;
        GPIOFunctionID[6] = R.id.GPIOInOrOut6;
        GPIOFunctionID[7] = R.id.GPIOInOrOut7;
        GPIOFunctionID[8] = R.id.GPIOInOrOut8;
        GPIOFunctionID[9] = R.id.GPIOInOrOut9;
        GPIOFunctionID[10] = R.id.GPIOInOrOut10;
        GPIOFunctionID[11] = R.id.GPIOInOrOut11;
        GPIOFunctionID[12] = R.id.GPIOInOrOut12;
        GPIOFunctionID[13] = R.id.GPIOInOrOut13;
        GPIOFunctionID[16] = R.id.GPIOInOrOut16;
        GPIOFunctionID[17] = R.id.GPIOInOrOut17;
        GPIOFunctionID[18] = R.id.GPIOInOrOut18;
        GPIOFunctionID[19] = R.id.GPIOInOrOut19;
        GPIOFunctionID[20] = R.id.GPIOInOrOut20;
        GPIOFunctionID[21] = R.id.GPIOInOrOut21;
        GPIOFunctionID[22] = R.id.GPIOInOrOut22;
        GPIOFunctionID[23] = R.id.GPIOInOrOut23;
        GPIOFunctionID[24] = R.id.GPIOInOrOut24;
        GPIOFunctionID[25] = R.id.GPIOInOrOut25;
        GPIOFunctionID[26] = R.id.GPIOInOrOut26;
        GPIOFunctionID[27] = R.id.GPIOInOrOut27;
    }
    private static void InitGPIOVluesID(){
        GPIOVluesID[2] = R.id.butPin3;
        GPIOVluesID[3] = R.id.butPin5;
        GPIOVluesID[4] = R.id.butPin7;
        GPIOVluesID[5] = R.id.butPin29;
        GPIOVluesID[6] = R.id.butPin31;
        GPIOVluesID[7] = R.id.butPin26;
        GPIOVluesID[8] = R.id.butPin24;
        GPIOVluesID[9] = R.id.butPin21;
        GPIOVluesID[10] = R.id.butPin19;
        GPIOVluesID[11] = R.id.butPin23;
        GPIOVluesID[12] = R.id.butPin32;
        GPIOVluesID[13] = R.id.butPin33;
        GPIOVluesID[16] = R.id.butPin36;
        GPIOVluesID[17] = R.id.butPin11;
        GPIOVluesID[18] = R.id.butPin12;
        GPIOVluesID[19] = R.id.butPin35;
        GPIOVluesID[20] = R.id.butPin38;
        GPIOVluesID[21] = R.id.butPin40;
        GPIOVluesID[22] = R.id.butPin15;
        GPIOVluesID[23] = R.id.butPin16;
        GPIOVluesID[24] = R.id.butPin18;
        GPIOVluesID[25] = R.id.butPin22;
        GPIOVluesID[26] = R.id.butPin37;
        GPIOVluesID[27] = R.id.butPin13;
    }
    /**
     * 传入功能引脚的索引，返回引脚的ID
     * @param index
     * @return
     */
    public static int getGPIOFunctionId(int index){
          InitGPIOFunctionID();
        return GPIOFunctionID[index];
    }
    /**
     * 传入开关引脚的索引，返回引脚的ID
     * @param index
     * @return
     */
    public static int getGPIOVluesId(int index){
        InitGPIOVluesID();
        return GPIOVluesID[index];
    }

    /**
     * 在GPIOFunctionID中是否存在
     * @param id
     * @return
     */
    public static boolean isInGPIOFunctionID( int id) {
        for(int i: GPIOFunctionID){
            if(i==id)
                return true;
        }
        return false;
    }

    /**
     * 在GPIOVluesID中是否存在
     * @param id
     * @return
     */
    public static boolean isInGPIOVluesID( int id) {
        for(int i: GPIOVluesID){
            if(i==id)
                return true;
        }
        return false;
    }
    /**
     * 根据传入的ID返回对应功能引脚下标
     * @param ID
     * @return
     */
    public static int getGPIOFunctionIndex(int ID){

        int index = 2;
        for (index=2 ;index<GPIOFunctionID.length;index++)
        {
            if (GPIOFunctionID[index] == ID)
            return index;
        }
        return index;
    }
    public   static int getGPIOVlueIndex(int ID){
        int index = 2;
        for (index=2 ;index<GPIOVluesID.length;index++)
        {
            if (GPIOVluesID[index] == ID)
                return index;
        }

        return index;
    }
}
