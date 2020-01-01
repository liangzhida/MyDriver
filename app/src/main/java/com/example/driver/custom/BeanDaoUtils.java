package com.example.driver.custom;

import android.content.Context;
import android.util.Log;

import com.example.driver.Bean.Bean;
import com.example.driver.Bean.BeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class BeanDaoUtils {
    private static final String TAG = BeanDaoUtils.class.getSimpleName();

    private DaoManager mManager;


    public BeanDaoUtils(Context context){

        mManager = DaoManager.getInstance();

        mManager.init(context);

    }


    /**

     * 完成Bean记录的插入，如果表未创建，先创建Bean表

     * @param bean

     * @return

     */

    public boolean insertBean(Bean bean){

        boolean flag = false;

        flag = mManager.getDaoSession().getBeanDao().insert(bean) == -1 ? false : true;

        Log.i(TAG, "insert Bean :" + flag + "-->" + bean.toString());

        return flag;

    }


    /**

     * 插入多条数据，在子线程操作

     * @param beanList

     * @return

     */

    public boolean insertMultBean(final List<Bean> beanList) {

        boolean flag = false;

        try {

            mManager.getDaoSession().runInTx(new Runnable() {

                @Override

                public void run() {

                    for (Bean bean : beanList) {

                        mManager.getDaoSession().insertOrReplace(bean);

                    }

                }

            });

            flag = true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return flag;

    }


    /**

     * 修改一条数据

     * @param bean

     * @return

     */

    public boolean updateBean(Bean bean){

        boolean flag = false;

        try {

            mManager.getDaoSession().update(bean);

            flag = true;

        }catch (Exception e){

            e.printStackTrace();

        }

        return flag;

    }


    /**

     * 删除单条记录

     * @param bean

     * @return

     */

    public boolean deleteBean(Bean bean){

        boolean flag = false;

        try {

//按照id删除

            mManager.getDaoSession().delete(bean);

            flag = true;

        }catch (Exception e){

            e.printStackTrace();

        }

        return flag;

    }


    /**

     * 删除所有记录

     * @return

     */

    public boolean deleteAll(){

        boolean flag = false;

        try {

//按照id删除

            mManager.getDaoSession().deleteAll(Bean.class);

            flag = true;

        }catch (Exception e){

            e.printStackTrace();

        }

        return flag;

    }


    /**

     * 查询所有记录

     * @return

     */

    public List<Bean> queryAllBean(){

        return mManager.getDaoSession().loadAll(Bean.class);

    }



    /**

     * 根据主键id查询记录

     * @param key

     * @return

     */

    public Bean queryBeanById(long key){

        return mManager.getDaoSession().load(Bean.class, key);

    }


    /**

     * 使用native sql进行查询操作

     */

    public List<Bean> queryBeanByNativeSql(String sql, String[] conditions){

        return mManager.getDaoSession().queryRaw(Bean.class, sql, conditions);

    }


    /**

     * 使用queryBuilder进行查询

     * @return

     */

    public List<Bean> queryBeanByQueryBuilder(long id){

        QueryBuilder<Bean> queryBuilder = mManager.getDaoSession().queryBuilder(Bean.class);

        return queryBuilder.where(BeanDao.Properties._id.eq(id)).list();

    }
}
