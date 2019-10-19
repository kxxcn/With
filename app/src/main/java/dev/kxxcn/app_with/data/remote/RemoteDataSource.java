package dev.kxxcn.app_with.data.remote;

import java.util.List;

import dev.kxxcn.app_with.data.DataSource;
import dev.kxxcn.app_with.data.model.decimal.DecimalDay;
import dev.kxxcn.app_with.data.model.diary.Diary;
import dev.kxxcn.app_with.data.model.diary.Profile;
import dev.kxxcn.app_with.data.model.entry.Entry;
import dev.kxxcn.app_with.data.model.event.Event;
import dev.kxxcn.app_with.data.model.geocode.v2.Results;
import dev.kxxcn.app_with.data.model.mode.ResponseMode;
import dev.kxxcn.app_with.data.model.nickname.Nickname;
import dev.kxxcn.app_with.data.model.nickname.ResponseNickname;
import dev.kxxcn.app_with.data.model.notice.Notice;
import dev.kxxcn.app_with.data.model.pairing.ResponsePairing;
import dev.kxxcn.app_with.data.model.plan.Plan;
import dev.kxxcn.app_with.data.model.result.ResponseResult;
import dev.kxxcn.app_with.data.model.setting.ResponseSetting;
import dev.kxxcn.app_with.util.Constants;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by kxxcn on 2018-08-20.
 */
public class RemoteDataSource extends DataSource {

    private static RemoteDataSource remoteDataSource;

    private APIService service;

    private OpenAPIService openAPIService;

    private RemoteDataSource() {
        this.service = APIService.Factory.create();
        this.openAPIService = OpenAPIService.Factory.create();
    }

    public static synchronized RemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }

    @Override
    public Single<ResponsePairing> createPairingKey(String uniqueIdentifier, String token, int gender) {
        return service.createPairingKey(uniqueIdentifier, token, gender)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> signUp(String uniqueIdentifier, int gender, String token) {
        return service.signUp(uniqueIdentifier, gender, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> signOut(String uniqueIdentifier) {
        return service.signOut(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> authenticate(String uniqueIdentifier, String key, int gender, String token) {
        return service.authenticateKey(uniqueIdentifier, key, gender, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> isRegisteredUser(String uniqueIdentifier) {
        return service.isRegisteredUser(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Diary>> getDiary(int flag, String uniqueIdentifier) {
        return service.getDiary(flag, uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> registerDiary(Diary diary) {
        return service.registerDiary(diary)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> updateDiary(Diary diary) {
        return service.updateDiary(diary)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> registerPlan(Plan plan) {
        return service.registerPlan(plan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> updatePlan(Plan plan) {
        return service.updatePlan(plan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Plan>> getPlan(String uniqueIdentifier) {
        return service.getPlan(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> uploadImage(MultipartBody.Part body) {
        return service.uploadImage(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public synchronized Single<ResponseBody> getImage(String fileName) {
        return service.getImage(fileName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> removeDiary(int id) {
        return service.removeDiary(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Single<ResponseResult> removePlan(int id) {
        return service.removePlan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseSetting> getNotificationInformation(String uniqueIdentifier) {
        return service.getNotificationInformation(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable updateReceiveNotification(String uniqueIdentifier, Constants.NotificationFilter which, boolean isOn) {
        return service.updateReceiveNotification(uniqueIdentifier, which, isOn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Results> convertCoordToAddress(String query) {
        return openAPIService.convertCoordToAddress(query, "admcode", "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> updateToken(String uniqueIdentifier, String newToken) {
        return service.updateToken(uniqueIdentifier, newToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseNickname> getTitle(String uniqueIdentifier) {
        return service.getTitle(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> registerNickname(Nickname nickname) {
        return service.registerNickname(nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseMode> checkMode(String uniqueIdentifier) {
        return service.checkMode(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> checkNewNotice(String uniqueIdentifier) {
        return service.checkNewNotice(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Notice>> getNotice(String uniqueIdentifier, String country) {
        return service.getNotice(uniqueIdentifier, country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> isLockedUser(String uniqueIdentifier) {
        return service.isLockedUser(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> registerLock(String uniqueIdentifier, String password) {
        return service.registerLock(uniqueIdentifier, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> unregisterLock(String uniqueIdentifier) {
        return service.unregisterLock(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> sync(String uniqueIdentifier, String key) {
        return service.sync(uniqueIdentifier, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<String>> subscribeIds(String uniqueIdentifier) {
        return service.subscribeIds(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Event>> fetchEvents() {
        return service.fetchEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> registerEntry(Entry entry) {
        return service.registerEntry(entry)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Profile> getProfile(String uniqueIdentifier) {
        return service.getProfile(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> uploadProfile(MultipartBody.Part body, RequestBody identifier) {
        return service.uploadProfile(body, identifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> registerDay(DecimalDay day) {
        return service.registerDay(day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<DecimalDay>> getDay(String uniqueIdentifier) {
        return service.getDay(uniqueIdentifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> removeDay(int id) {
        return service.removeDay(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ResponseResult> updateDay(DecimalDay day) {
        return service.updateDay(day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
