package cube.ware.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import com.common.utils.utils.log.LogUtil;
import cube.service.CubeEngine;
import cube.service.common.model.CubeConfig;
import cube.service.user.UserState;
import cube.ware.api.CubeUI;
import cube.ware.service.message.MessageHandle;
import cube.ware.service.user.UserHandle;

/**
 * CubeWare组件接口实现类
 *
 * @author LiuFeng
 * @data 2020/1/23 11:23
 */
public final class UIRoot extends CubeUI {
    private Context mContext;

    @Override
    public void init(Context context, String appId, String appKey, String licensePath, String appResourcePath) {
        this.mContext = context.getApplicationContext();

        // 初始化引擎配置信息
        this.initCubeConfig(appId, appKey, licensePath, appResourcePath);
    }

    /**
     * 初始化引擎配置信息
     *
     * @param appId
     * @param appKey
     * @param appResourcePath
     */
    public void initCubeConfig(String appId, String appKey, String licensePath, String appResourcePath) {
        // 配置引擎相关参数
        CubeConfig config = new CubeConfig();
        config.setVideoCodec("VP8");                                // 设置视频编解码格式
        config.setVideoWidth(640);
        config.setVideoHeight(480);
        config.setResourceDir(appResourcePath);                     // 设置资源存放目录
        config.setAppId(appId);
        config.setAppKey(appKey);
        config.setLicenseServer(licensePath);
        config.setDebug(LogUtil.isLoggable());                     //是否打开引擎的日志记录系统
        CubeEngine.getInstance().setCubeConfig(config);
    }

    @Override
    public void startup(Context context) {
        if (!isStarted() && CubeEngine.getInstance().startup(context)) {
            // 注册启动监听
            startListener();
        }
    }

    /**
     * 启动引擎各服务的监听
     */
    private void startListener() {
        //CubeEngineHandle.getInstance().start();
        UserHandle.getInstance().start();
        MessageHandle.getInstance().start();
        //CallHandle.getInstance().start();
        //ConferenceHandle.getInstance().start();
        //FileHandle.getInstance().start();
        //GroupHandle.getInstance().start();
        //ShareDesktopHandle.getInstance().start();
        //WhiteBoardHandle.getInstance().start();
        //SettingHandle.getInstance().start();
    }

    /**
     * 登录引擎
     *
     * @param cubeId
     * @param cubeToken
     * @param displayName
     */
    @Override
    public void login(@NonNull String cubeId, @NonNull String cubeToken, String displayName) {
        CubeEngine.getInstance().getUserService().login(cubeId, cubeToken, displayName);
    }

    /**
     * 注销引擎
     */
    @Override
    public void logout() {
        CubeEngine.getInstance().getUserService().logout();
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 引擎是否已启动
     *
     * @return
     */
    @Override
    public boolean isStarted() {
        return CubeEngine.getInstance().isStarted();
    }

    @Override
    public UserState getAccountState() {
        return CubeEngine.getInstance().getSession().userState;
    }

    /**
     * 判断当前是否正在通话中
     *
     * @return
     */
    @Override
    public boolean isCalling() {
        return CubeEngine.getInstance().getSession().isCalling();
    }

    /**
     * 判断当前是否正在多人音视频
     *
     * @return
     */
    @Override
    public boolean isConference() {
        return CubeEngine.getInstance().getSession().isConference();
    }
}