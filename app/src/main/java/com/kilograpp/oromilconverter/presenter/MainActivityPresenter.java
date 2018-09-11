package com.kilograpp.oromilconverter.presenter;

import com.kilograpp.oromilconverter.view.activity.MainMvpView;

/**
 * Created by Oromil on 13.06.2017.
 */

public class MainActivityPresenter extends BasePresenter<MainMvpView> {

//    private ServerManager mServerManager;
//    private Realm mRealm;
//
//    public MainActivityPresenter() {
//        mServerManager = new ServerManager();
//    }
//
//    public void loadData() {
//        Log.d("Test", "loadData");
////        updateData().subscribe(o -> {},
//////                (Object throwable) -> {
//////                    ConverterFragment converterFragment = getmMvpView().getConverterFragment();
//////                    loadDataFromDataBase()
//////                            .subscribeOn(AndroidSchedulers.mainThread())
//////                            .observeOn(Schedulers.newThread())
//////                            .subscribe(allValutes -> converterFragment.updateList(allValutes));
//////                });
//
//        mServerManager.testRequest(new Subscriber<List<Valute>>() {
//            @Override
//            public void onCompleted() {
//                mMvpView.showProgress(false);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mMvpView.showProgress(false);
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onNext(List<Valute> valutes) {
//                mMvpView.getConverterFragment().updateList(valutes);
//            }
//        });
//    }
//
////    public Observable updateData() {
////        Log.d("Test", "updateData");
////        return Observable.create(subscriber -> mServerManager.requestData(new Subscriber<Courses>() {
////            @Override
////            public void onCompleted() {
////
////                subscriber.onNext(null);
////            }
////
////            @Override
////            public void onError(Throwable e) {
////                Log.d("RX updateData Error", e + "");
////                subscriber.onError(e);
////            }
////
////            @Override
////            public void onNext(Courses o) {
////                ArrayList<ValuteOld> valuteOlds = o.getRates().getValutesList();
////
////                ConverterFragment converterFragment = getmMvpView().getConverterFragment();
////                converterFragment.updateList(valuteOlds);
////
////                saveDataInDataBase(valuteOlds)
////                        .subscribeOn(AndroidSchedulers.mainThread())
////                        .observeOn(Schedulers.newThread())
////                        .subscribe(o1 -> {
////                                },
////                                throwable -> Log.d("DataSaving Error", throwable + ""));
////            }
////        }));
////    }
//
//    private Observable saveDataInDataBase(ArrayList valutes) {
//        mRealm = Realm.getDefaultInstance();
//        mRealm.executeTransaction(mRealm1 -> mRealm1.insertOrUpdate(valutes));
//        mRealm = null;
//        return Observable.just("");
//    }
//
//    private Observable<ArrayList<ValuteOld>> loadDataFromDataBase() {
//        return Observable.create(subscriber -> {
//            ArrayList<ValuteOld> valuteOlds = new ArrayList<>();
//            mRealm = Realm.getDefaultInstance();
//            mRealm.executeTransaction(realm -> valuteOlds.addAll(realm.where(ValuteOld.class).findAll()));
//            mRealm = null;
//            subscriber.onNext(valuteOlds);
//        });
//    }
}
