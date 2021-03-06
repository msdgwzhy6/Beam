package com.jude.beamdemo.presenter;

import android.os.Bundle;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.beamdemo.model.QuestionModel;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.model.bean.QuestionResult;
import com.jude.beamdemo.view.QuestionActivity;

import java.util.List;

import rx.functions.Func1;

/**
 * Created by zhuchenxi on 15/6/7.
 */
    public class QuestionPresenter extends BeamListActivityPresenter<QuestionActivity,Question> {
        @Override
        protected void onCreate(QuestionActivity view, Bundle savedState) {
            super.onCreate(view, savedState);
            onRefresh();
        }


        @Override
        public void onLoadMore() {
            QuestionModel.getInstance().getQuestions(getCurPage()).map(new Func1<QuestionResult, List<Question>>() {
                @Override
                public List<Question> call(QuestionResult questionResult) {
                    return questionResult.getQuestions();
                }
            }).unsafeSubscribe(getMoreSubscriber());
        }

        @Override
        public void onRefresh() {
            QuestionModel.getInstance().getQuestions(0).map(new Func1<QuestionResult, List<Question>>() {
                @Override
                public List<Question> call(QuestionResult questionResult) {
                    return questionResult.getQuestions();
                }
            }).unsafeSubscribe(getRefreshSubscriber());
        }
    }
