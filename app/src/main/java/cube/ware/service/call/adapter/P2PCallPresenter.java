package cube.ware.service.call.adapter;

import android.content.Context;


/**
 * Created by zzy on 2018/8/29.
 */

public class P2PCallPresenter extends P2PCallContract.Presenter  {

    /**
     * 构造方法
     *
     * @param context
     * @param view
     */
    public P2PCallPresenter(Context context, P2PCallContract.View view) {
        super(context, view);
    }

    @Override
    void getCallUser(String callId) {
      //n脸懵逼
    }
}
