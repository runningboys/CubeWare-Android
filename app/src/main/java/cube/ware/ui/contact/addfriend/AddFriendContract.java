package cube.ware.ui.contact.addfriend;

import android.content.Context;

import com.common.mvp.base.BasePresenter;
import com.common.mvp.base.BaseView;

import cube.service.group.model.Group;

public interface AddFriendContract {

    interface View extends BaseView {
        void searchGroup(Group group);
    }

    abstract class Presenter extends BasePresenter<AddFriendContract.View> {

        /**
         * 构造方法
         *
         * @param context
         * @param view
         */
        public Presenter(Context context, AddFriendContract.View view) {
            super(context, view);
        }

        abstract void getGroupDate(String groupId);
    }
}
