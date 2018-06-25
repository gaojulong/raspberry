package netWork;

/**
 * Created by julong on 2018/3/31.
 */

public interface HttpCallbackListener {
     void OnSucceed(String response);
     void OnError(Exception e);
}
